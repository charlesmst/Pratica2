package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.AdvertenciaTipoService;
import br.com.empresa.rh.model.AdvertenciaTipo;
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
@Path("/advertenciatipo")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class AdvertenciaTipoResource {

    @Autowired
    private AdvertenciaTipoService AdvertenciaTipoService;

    @Context
    protected UriInfo info;

    public AdvertenciaTipoService getAdvertenciaTipoService() {
        return AdvertenciaTipoService;
    }

    public void setAdvertenciaTipoService(AdvertenciaTipoService marcaService) {
        this.AdvertenciaTipoService = marcaService;
    }

    public AdvertenciaTipoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(AdvertenciaTipoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<AdvertenciaTipo> m = AdvertenciaTipoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AdvertenciaTipo findById(@PathParam("id") int id) {
        AdvertenciaTipo m = AdvertenciaTipoService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(AdvertenciaTipo m) {
        AdvertenciaTipoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, AdvertenciaTipo entity) {
        AdvertenciaTipoService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        AdvertenciaTipoService.delete(id);
    }

}
