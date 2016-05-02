package br.com.empresa.rh.resources;
import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.VagasService;
import br.com.empresa.rh.model.Vagas;
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
@Path("/vagas")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class VagasResource {

    @Autowired
    private VagasService VagasService;

    @Context
    protected UriInfo info;

    public VagasService getVagasService() {
        return VagasService;
    }

    public void setVagasService(VagasService marcaService) {
        this.VagasService = marcaService;
    }

    public VagasResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(VagasService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Vagas> m = VagasService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vagas findById(@PathParam("id") int id) {
        Vagas m = VagasService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Vagas m) {
        VagasService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, Vagas entity) {
        VagasService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        VagasService.delete(id);
    }

}
