package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.QuestaoService;
import br.com.empresa.rh.model.Questao;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.response.CountResponse;
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
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/questao")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class QuestaoResource {

    @Autowired
    private QuestaoService questaoService;

    @Context
    protected UriInfo info;

    public QuestaoService getQuestaoService() {
        return questaoService;
    }

    public void setQuestaoService(QuestaoService marcaService) {
        this.questaoService = marcaService;
    }

    public QuestaoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(questaoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Questao> m = questaoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Questao findById(@PathParam("id") long id) {
        Questao m = questaoService.findById(id);
        return m;
    }

    @GET
    @Path("nivel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Questao> findByNivel(@PathParam("id") long id) {
        return questaoService.findAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Questao m) {
        questaoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") long id, Questao entity) {
        questaoService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") long id) {
        questaoService.delete(id);
    }

}
