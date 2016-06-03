package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.TipoSanguineoService;
import br.com.empresa.rh.model.TipoSanguineo;
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
@Path("/tiposanguineo")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class TipoSanguineoResource {

    @Autowired
    private TipoSanguineoService TipoSanguineoService;

    @Context
    protected UriInfo info;

    public TipoSanguineoService getTipoSanguineoService() {
        return TipoSanguineoService;
    }

    public void setTipoSanguineoService(TipoSanguineoService marcaService) {
        this.TipoSanguineoService = marcaService;
    }

    public TipoSanguineoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(TipoSanguineoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<TipoSanguineo> m = TipoSanguineoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TipoSanguineo findById(@PathParam("id") int id) {
        TipoSanguineo m = TipoSanguineoService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(TipoSanguineo m) {
        TipoSanguineoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, TipoSanguineo entity) {
        TipoSanguineoService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        TipoSanguineoService.delete(id);
    }

}
