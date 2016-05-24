package br.com.empresa.rh.resources;
import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.PlanoAvaliacaoService;
import br.com.empresa.rh.model.PlanoAvaliacao;
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
@Path("/plano-avaliacao")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class PlanoAvaliacaoResource {

    @Autowired
    private PlanoAvaliacaoService planoAvaliacaoService;

    @Context
    protected UriInfo info;

    public PlanoAvaliacaoService getPlanoAvaliacaoService() {
        return planoAvaliacaoService;
    }

    public void setPlanoAvaliacaoService(PlanoAvaliacaoService marcaService) {
        this.planoAvaliacaoService = marcaService;
    }

    public PlanoAvaliacaoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(planoAvaliacaoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<PlanoAvaliacao> m = planoAvaliacaoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PlanoAvaliacao findById(@PathParam("id") int id) {
        PlanoAvaliacao m = planoAvaliacaoService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(PlanoAvaliacao m) {
        planoAvaliacaoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, PlanoAvaliacao entity) {
        planoAvaliacaoService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        planoAvaliacaoService.delete(id);
    }

}
