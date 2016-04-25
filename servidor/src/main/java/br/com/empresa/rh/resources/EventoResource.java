package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.EventoService;
import br.com.empresa.rh.model.Evento;
import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.Parametro;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.model.response.EventoTesteResponse;
import br.com.empresa.rh.response.CountResponse;
import br.com.empresa.rh.service.FuncionarioCargoService;
import br.com.empresa.rh.service.FuncionarioService;
import br.com.empresa.rh.service.folha.CalculoFolha;
import br.com.empresa.rh.service.folha.EventoCollection;
import br.com.empresa.rh.service.folha.IEvento;
import br.com.empresa.rh.service.folha.Parametros;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.ws.Provider;
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
    private FuncionarioCargoService funcionarioCargoService;

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
        eventoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, Evento entity) {
        eventoService.update(entity);

    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        eventoService.delete(id);
    }

    @POST
    @Path("test/{funcionario}")
    @Consumes({MediaType.APPLICATION_JSON})
    public EventoTesteResponse testar(Evento m, @PathParam("funcionario") int funcionarioCargo) {
//        EventoCollection c = new EventoCollection(Arrays.asList(m));
        Date periodo = new Date();
        EventoCollection c = eventoService.todosEventosFuncionario(funcionarioCargo, periodo);

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
        if(!added ){
            c.addAll(Arrays.asList(m));
        }

        FuncionarioCargo f = funcionarioCargoService.findById(funcionarioCargo);
        
        calculoFolha.setEventos(c);
        calculoFolha.calcula(f, periodo);

        EventoTesteResponse e = new EventoTesteResponse();
        e.setEventos(c);
        e.setLogs(calculoFolha.getLog());
        return e;
    }
}
