package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.QuestaoOpcaoService;
import br.com.empresa.rh.model.QuestaoOpcao;
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
@Path("/questao-opcao")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class QuestaoOpcaoResource {

    @Autowired
    private QuestaoOpcaoService questaoOpcaoService;

    @Context
    protected UriInfo info;

    public QuestaoOpcaoService getQuestaoOpcaoService() {
        return questaoOpcaoService;
    }

    public void setQuestaoOpcaoService(QuestaoOpcaoService marcaService) {
        this.questaoOpcaoService = marcaService;
    }

    public QuestaoOpcaoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(questaoOpcaoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<QuestaoOpcao> m = questaoOpcaoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public QuestaoOpcao findById(@PathParam("id") int id) {
        QuestaoOpcao m = questaoOpcaoService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(QuestaoOpcao m) {
        questaoOpcaoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, QuestaoOpcao entity) {
        questaoOpcaoService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        questaoOpcaoService.delete(id);
    }

}
