package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.FaixaSalarialService;
import br.com.empresa.rh.model.FaixaSalarial;
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
@Path("/faixasalarial")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class FaixaSalarialResource {

    @Autowired
    private FaixaSalarialService FaixaSalarialService;

    @Context
    protected UriInfo info;

    public FaixaSalarialService getFaixaSalarialService() {
        return FaixaSalarialService;
    }

    public void setFaixaSalarialService(FaixaSalarialService marcaService) {
        this.FaixaSalarialService = marcaService;
    }

    public FaixaSalarialResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(FaixaSalarialService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<FaixaSalarial> m = FaixaSalarialService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public FaixaSalarial findById(@PathParam("id") int id) {
        FaixaSalarial m = FaixaSalarialService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(FaixaSalarial m) {
        FaixaSalarialService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, FaixaSalarial entity) {
        FaixaSalarialService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        FaixaSalarialService.delete(id);
    }

}
