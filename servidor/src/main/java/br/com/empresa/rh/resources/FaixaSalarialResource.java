package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.model.CargoNivel;
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
    private FaixaSalarialService faixaSalarialService;

    @Context
    protected UriInfo info;

    public FaixaSalarialService getFaixaSalarialService() {
        return faixaSalarialService;
    }

    public void setFaixaSalarialService(FaixaSalarialService marcaService) {
        this.faixaSalarialService = marcaService;
    }

    public FaixaSalarialResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(faixaSalarialService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<FaixaSalarial> m = faixaSalarialService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public FaixaSalarial findById(@PathParam("id") int id) {
        FaixaSalarial m = faixaSalarialService.findById(id);
        return m;
    }
 @GET
    @Path("cargonivel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FaixaSalarial> findByCargoNivel(@PathParam("id") int id) {
        CargoNivel nivel = new CargoNivel();
        nivel.setId(id);
        List<FaixaSalarial> m = faixaSalarialService.findByCargoNivel(nivel);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(FaixaSalarial m) {
        faixaSalarialService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, FaixaSalarial entity) {
        faixaSalarialService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        faixaSalarialService.delete(id);
    }

}
