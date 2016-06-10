package br.com.empresa.rh.resources;
import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.model.Candidato;
import br.com.empresa.rh.model.Competencia;
import br.com.empresa.rh.model.Entrevista;
import br.com.empresa.rh.model.Usuario;
import br.com.empresa.rh.service.VagasService;
import br.com.empresa.rh.model.Vagas;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.model.view.Recrutamento;
import br.com.empresa.rh.response.CountResponse;
import br.com.empresa.rh.service.CargoService;
import br.com.empresa.rh.service.CompetenciaService;
import br.com.empresa.rh.service.EntrevistaService;
import br.com.empresa.rh.service.UsuarioService;
import br.com.empresa.rh.util.Utilitarios;
import com.fasterxml.jackson.annotation.JsonView;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import static org.mozilla.javascript.TopLevel.Builtins.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/vagas")
@RolesAllowed(NivelAcesso.NENHUM)
public class VagasResource {

    @Autowired
    private VagasService vagasService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CompetenciaService competenciaService;

    @Autowired
    private EntrevistaService entrevistaService;

    @Autowired
    private Utilitarios utilitarios;

    @Context
    protected UriInfo info;

    public VagasService getVagasService() {
        return vagasService;
    }

    public void setVagasService(VagasService marcaService) {
        this.vagasService = marcaService;
    }

    public VagasResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(vagasService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Recrutamento.VagasView.class)
    public Response findAll(@Context SecurityContext securityContext) {
        utilitarios.setSecutiryContext(securityContext);
        Usuario u = new Usuario();
        try{
            u = usuarioService.findById(utilitarios.usuario());
        } catch(Exception e) {
            u.setNivel(0);
        }
        TableRequest request = TableRequest.build(info);
        
        System.out.println(u.getNivel());
        
        //0 1 (tipos) 2(sigiloso) 3(finalizado)
        
        int[] tipo = new int[3];
        
        switch (u.getNivel()) {
            case 0:
                tipo[0] = 1;
                tipo[1] = 3;
                tipo[2] = 0;
                break;
            case 1:
                tipo[0] = 1;
                tipo[1] = 3;
                tipo[2] = 1;
                break;
            case 2:
                tipo[0] = 1;
                tipo[1] = 3;
                tipo[2] = 2;
                break;
            case 3:
                tipo[2] = 3;
                break;
            case 4:
                tipo[2] = 4;
                break;
            case 5:
                tipo[2] = 5;
                break;
            default:
                tipo[0] = 1;
                tipo[1] = 3;
                tipo[2] = 0;
        }

        List<Vagas> m = vagasService.findForTable(request, tipo);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Recrutamento.Vagas.class)
    public Vagas findById(@PathParam("id") int id, @Context SecurityContext securityContext) {
        Vagas m = vagasService.findById(id);
        return m;
    }

    @GET
    @Path("view/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Recrutamento.VagasView.class)
    public Vagas viewFindById(@PathParam("id") int id, @Context SecurityContext securityContext) {
        Vagas m = vagasService.viewFindById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Vagas m, @Context SecurityContext securityContext) {
        utilitarios.setSecutiryContext(securityContext);
        Usuario u = usuarioService.findById(utilitarios.usuario());
        m.setUsuario(u);
        if (m.getDataInicio() == null) {
            m.setDataInicio(new Date());
        }
        if (m.getFinalizado() == null) {
            m.setFinalizado(Boolean.FALSE);
        }
        vagasService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, Vagas entity) throws ParseException {
        Vagas v = vagasService.findById(id);
        Vagas e = entity;
        v.setCargo(e.getCargo());
        v.setDataFim(e.getDataFim());
        v.setDataInicio(e.getDataInicio());
        v.setDescricao(e.getDescricao());
        v.setFinalizado(e.getFinalizado());
        v.setSigiloso(e.getSigiloso());
        v.setTipo(e.getTipo());
        v.setPerfil(e.getPerfil());
        v.setQuantidade(e.getQuantidade());
        v.setPlanoAvaliacao(e.getPlanoAvaliacao());

        for (Competencia c : e.getCompetencias()) {
            if (c.getId() == 0) {
                c.setAtivo(true);
                c.setTipo('a');
                competenciaService.insert(c);
            } else {
                competenciaService.update(c);
            }
        }
        v.setCompetencias(e.getCompetencias());

        DateFormat formatter = new SimpleDateFormat("HH:mm");

        for (Candidato c : e.getCandidatos()) {
            for (Entrevista ent : c.getEntrevistas()) {
                if (ent.getId() == 0) {
                    ent.setCandidato(c);
                    if(ent.getSituacao() == 0) {
                        ent.setSituacao(1);
                    }
                    entrevistaService.insert(ent);
                } else {
                    Entrevista entUp = entrevistaService.findById(ent.getId());
                    entUp.setConfirmado(ent.isConfirmado());
                    entUp.setDataProgramada(ent.getDataProgramada());
                    entUp.setDescricao(ent.getDescricao());
                    entUp.setHora(ent.getHora());
                    entUp.setLocalEntrevista(ent.getLocalEntrevista());
                    entUp.setResposta(ent.getResposta());
                    entUp.setSituacao(ent.getSituacao());
                    entrevistaService.update(entUp);
                }
            }
        }
        vagasService.update(v);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        vagasService.delete(id);
    }

}
