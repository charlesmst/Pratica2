package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.model.Cargo;
import br.com.empresa.rh.service.CargoHasEventoService;
import br.com.empresa.rh.model.CargoHasEvento;
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
@Path("/cargohasevento")
@RolesAllowed(NivelAcesso.RH)
public class CargoHasEventoResource {

    @Autowired
    private CargoHasEventoService cargoHasEventoService;

    @Context
    protected UriInfo info;

    public CargoHasEventoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cargo/{cargo}/{ano}/{mes}")
    public Response findAll(@PathParam("cargo") int cargo,@PathParam("ano") int ano,@PathParam("mes") int mes ) {
        Cargo c = new Cargo();
        c.setId(cargo);
        List<CargoHasEvento> m = cargoHasEventoService.findByCargo(c,ano,mes);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CargoHasEvento findById(@PathParam("id") int id) {
        CargoHasEvento m = cargoHasEventoService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(CargoHasEvento m) {
        cargoHasEventoService.insert(m);
    }


    @DELETE
    @Path("{cargo}/{evento}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("cargo") int cargo,@PathParam("evento") int evento) {
        cargoHasEventoService.delete(cargo,evento);
    }

}
