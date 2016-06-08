package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.model.Competencia;
import br.com.empresa.rh.model.Usuario;
import br.com.empresa.rh.service.VagasService;
import br.com.empresa.rh.model.Vagas;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.model.view.Recrutamento;
import br.com.empresa.rh.response.CountResponse;
import br.com.empresa.rh.service.CargoService;
import br.com.empresa.rh.service.CompetenciaService;
import br.com.empresa.rh.service.UsuarioService;
import br.com.empresa.rh.util.Utilitarios;
import com.fasterxml.jackson.annotation.JsonView;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/vagas")
@RolesAllowed(NivelAcesso.CANDIDATO)
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
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Vagas> m = vagasService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Recrutamento.Vagas.class)
    public Vagas findById(@PathParam("id") int id) {
        Vagas m = vagasService.findById(id);
        return m;
    }

    @GET
    @Path("view/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Recrutamento.VagasView.class)
    public Vagas viewFindById(@PathParam("id") int id) {
        Vagas m = vagasService.viewFindById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Vagas m, @Context SecurityContext securityContext) {
        utilitarios.setSecutiryContext(securityContext);
        Usuario u = usuarioService.findById(utilitarios.usuario());
        m.setUsuario(u);
        vagasService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, Vagas entity) {
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
        vagasService.update(v);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        vagasService.delete(id);
    }

}
