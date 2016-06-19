package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.model.Cargo;
import br.com.empresa.rh.service.EventoService;
import br.com.empresa.rh.model.Evento;
import br.com.empresa.rh.model.FolhaCalculada;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.request.CalculoFolhaRequest;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.model.response.EventoTesteResponse;
import br.com.empresa.rh.model.response.MensagemResponse;
import br.com.empresa.rh.response.CountResponse;
import br.com.empresa.rh.service.CargoService;
import br.com.empresa.rh.service.FolhaCalculadaService;
import br.com.empresa.rh.service.FuncionarioCargoService;
import br.com.empresa.rh.service.folha.CalculoFolha;
import br.com.empresa.rh.service.folha.EventoCollection;
import br.com.empresa.rh.service.folha.EventoScript;
import br.com.empresa.rh.service.folha.IEvento;
import br.com.empresa.rh.service.folha.TipoCalculo;
import br.com.empresa.rh.util.ApiException;
import br.com.empresa.rh.websocket.FolhaHub;
import br.com.empresa.rh.websocket.FolhaHubResolve;
import br.com.empresa.rh.websocket.ReportProgressHub;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/evento")
@RolesAllowed(NivelAcesso.RH)
public class EventoResource {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private CalculoFolha calculoFolha;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private FuncionarioCargoService funcionarioCargoService;

    @Autowired
    private br.com.empresa.rh.util.Utilitarios utilitarios;
    @Autowired
    private FolhaCalculadaService folhaCalculadaService;
    @Context
    protected UriInfo info;

    public EventoService getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoService marcaService) {
        this.eventoService = marcaService;
    }

    public EventoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(eventoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Evento> m = eventoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Evento findById(@PathParam("id") int id) {
        Evento m = eventoService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Evento m) {
//        eventoService.insert(m);
//        eventoService.persistDependencias2(m);
        eventoService.salvar(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, Evento entity) {
//        eventoService.update(entity);
//        eventoService.persistDependencias2(entity);
        eventoService.salvar(entity);

    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        eventoService.delete(id);
    }

    @POST
    @Path("test/{funcionario}/{mes}/{ano}/{tipo}")
    @Consumes({MediaType.APPLICATION_JSON})
    public EventoTesteResponse testar(Evento m, @PathParam("funcionario") int funcionarioCargo, @PathParam("mes") int mes, @PathParam("ano") int ano, @PathParam("tipo") int tipo) {
//        EventoCollection c = new EventoCollection(Arrays.asList(m));
        Date data = utilitarios.dataPeriodo(mes, ano);

        EventoCollection c = eventoService.todosEventosFuncionario(funcionarioCargo, data);

        boolean added = false;
        if (m.getId() > 0) {
            for (IEvento evento : c.getEventos()) {
                if (evento.getEvento().getId() == m.getId()) {
                    evento.getEvento().setScript(m.getScript());
                    evento.getEvento().setNome(m.getNome());
                    added = true;
                    break;
                }
            }
        }
        if (!added) {
            c.addAll(Arrays.asList(m));
        }

        FuncionarioCargo f = funcionarioCargoService.findById(funcionarioCargo);
        calculoFolha.setEventos(c);
        calculoFolha.calcula(f, data);

        EventoTesteResponse e = new EventoTesteResponse();
        e.setEventos(c);
        e.setLogs(calculoFolha.getLog());
        return e;
    }

    @POST
    @Path("calcular/mes/verificar")
    @Consumes({MediaType.APPLICATION_JSON})
    public MensagemResponse verificarFolhaMes(CalculoFolhaRequest request) {
        TipoCalculo t = TipoCalculo.parse(request.getTipo());

        List<FuncionarioCargo> funcionariosCalculo = funcionariosRequest(request);
        //Exclui os antigos e verifica se pode excluir
        List<String> nomes = new ArrayList<>();
        for (FuncionarioCargo funcionariosCalculo1 : funcionariosCalculo) {
            FolhaCalculada calculada = folhaCalculadaService.folhaCalculadaMes(request.getMes(), request.getAno(), t, funcionariosCalculo1);
            if (calculada != null) {
                nomes.add(funcionariosCalculo1.getFuncionario().getPessoa().getNome());
            }
        }
        String mensagme = String.join(",", nomes) + " possui(em) folha já calculada";
        MensagemResponse r = new MensagemResponse(nomes.isEmpty(), mensagme);
        return r;

    }

    private List<FuncionarioCargo> funcionariosRequest(CalculoFolhaRequest request) {
        List<FuncionarioCargo> funcionariosCalculo;
        Date data = utilitarios.dataPeriodo(request.getMes(), request.getAno());
        TipoCalculo t = TipoCalculo.parse(request.getTipo());
        if (request.isTodosEmpresa()) {
            List<Cargo> cargos = cargoService.daEmpresa(request.getEmpresa().getId(),data);
            funcionariosCalculo = new ArrayList<>();

            for (Cargo cargo : cargos) {
                funcionariosCalculo.addAll(cargo.getFuncionarioCargos());
            }
        } else {
            if (request.getFuncionarios() == null || request.getFuncionarios().isEmpty()) {
                throw new ApiException("Funcionário é obrigatório", null);
            }
            funcionariosCalculo = request.getFuncionarios();
        }
        return funcionariosCalculo;
    }

    @GET
    @Path("asd")
    public void asd() {
        try {
            //        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                for (int i = 0; i < 100; i++) {
//                    System.out.println("Requisição ainda andando com consulta" + i);
//                    eventoService.eventosDecimo(12);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(EventoResource.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        }).start();
            FolhaHub.sendToAll("hu3");
        } catch (IOException ex) {
            Logger.getLogger(EventoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @POST
    @Path("calcular/mes")
    @Consumes({MediaType.APPLICATION_JSON})
    public void calcular(final CalculoFolhaRequest request, @Context final SecurityContext securityContext) {

        final TipoCalculo t = TipoCalculo.parse(request.getTipo());

        final List<FuncionarioCargo> funcionariosCalculo = funcionariosRequest(request);
        //Exclui os antigos e verifica se pode excluir

        if (t == TipoCalculo.complementar) {
            EventoCollection collection = new EventoCollection();
            for (EventoScript evento : request.getEventos()) {
                Evento newEvento = eventoService.findById(evento.getEvento().getId());
                EventoScript newEventoScript = new EventoScript(newEvento);
                newEventoScript.setReferencia(evento.getReferencia());
                collection.getEventos().add(newEventoScript);
            }
            calculoFolha.setEventos(collection);
        } else {
            folhaCalculadaService.excluirFolhasAntigas(funcionariosCalculo, request.getMes(), request.getAno(), t, securityContext.isUserInRole(NivelAcesso.ADMIN));
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    ReportProgressHub hub = new ReportProgressHub("Folha " + t.toString() + " da competência " + request.getMes() + "/" + request.getAno());
                    FolhaHub.folhaHub.add(hub);
                    calculoFolha.setReportProgress(hub);
                    calculoFolha.calcularTodos(funcionariosCalculo, request.getMes(), request.getAno(), t);
                }catch(Exception ex){
                    Logger.getLogger(EventoResource.class.getName()).log(Level.SEVERE, null,ex);
                }
            }
        }).start();

//        return new MensagemResponse(true, "Folha de pagamento calculada com sucesso");
    }
}
