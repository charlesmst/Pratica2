package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.MotivoFaltaService;
import br.com.empresa.rh.model.MotivoFalta;
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
@Path("/motivofalta")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class MotivoFaltaResource {

    @Autowired
    private MotivoFaltaService MotivoFaltaService;

    @Context
    protected UriInfo info;

    public MotivoFaltaService getMotivoFaltaService() {
        return MotivoFaltaService;
    }

    public void setMotivoFaltaService(MotivoFaltaService marcaService) {
        this.MotivoFaltaService = marcaService;
    }

    public MotivoFaltaResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(MotivoFaltaService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<MotivoFalta> m = MotivoFaltaService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MotivoFalta findById(@PathParam("id") int id) {
        MotivoFalta m = MotivoFaltaService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(MotivoFalta m) {
        MotivoFaltaService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, MotivoFalta entity) {
        MotivoFaltaService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        MotivoFaltaService.delete(id);
    }

}
