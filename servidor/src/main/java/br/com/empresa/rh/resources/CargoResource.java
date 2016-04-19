package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.CargoService;
import br.com.empresa.rh.model.Cargo;
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
@Path("/cargo")
@RolesAllowed(NivelAcesso.GESTOR)
public class CargoResource {

    @Autowired
    private CargoService CargoService;

    @Context
    protected UriInfo info;

    public CargoService getCargoService() {
        return CargoService;
    }

    public void setCargoService(CargoService marcaService) {
        this.CargoService = marcaService;
    }

    public CargoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(CargoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Cargo> m = CargoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cargo findById(@PathParam("id") long id) {
        Cargo m = CargoService.findById(id);
        return m;
    }

    @GET
    @Path("nivel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cargo> findByNivel(@PathParam("id") long id) {
        return CargoService.findAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Cargo m) {
        CargoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") long id, Cargo entity) {
        CargoService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") long id) {
        CargoService.delete(id);
    }

}
