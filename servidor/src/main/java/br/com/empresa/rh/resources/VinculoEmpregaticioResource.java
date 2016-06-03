package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.VinculoEmpregaticioService;
import br.com.empresa.rh.model.VinculoEmpregaticio;
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
@Path("/vinculoempregaticio")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class VinculoEmpregaticioResource {

    @Autowired
    private VinculoEmpregaticioService VinculoEmpregaticioService;

    @Context
    protected UriInfo info;

    public VinculoEmpregaticioService getVinculoEmpregaticioService() {
        return VinculoEmpregaticioService;
    }

    public void setVinculoEmpregaticioService(VinculoEmpregaticioService marcaService) {
        this.VinculoEmpregaticioService = marcaService;
    }

    public VinculoEmpregaticioResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(VinculoEmpregaticioService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<VinculoEmpregaticio> m = VinculoEmpregaticioService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public VinculoEmpregaticio findById(@PathParam("id") int id) {
        VinculoEmpregaticio m = VinculoEmpregaticioService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(VinculoEmpregaticio m) {
        VinculoEmpregaticioService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, VinculoEmpregaticio entity) {
        VinculoEmpregaticioService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        VinculoEmpregaticioService.delete(id);
    }

}
