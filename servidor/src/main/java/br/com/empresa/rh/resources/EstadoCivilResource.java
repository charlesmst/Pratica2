package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.EstadoCivilService;
import br.com.empresa.rh.model.EstadoCivil;
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
@Path("/estado-civil")
@RolesAllowed(NivelAcesso.CANDIDATO)
public class EstadoCivilResource {

    @Autowired
    private EstadoCivilService estadoCivilService;

    @Context
    protected UriInfo info;

    public EstadoCivilService getEstadoCivilService() {
        return estadoCivilService;
    }

    public void setEstadoCivilService(EstadoCivilService marcaService) {
        this.estadoCivilService = marcaService;
    }

    public EstadoCivilResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(estadoCivilService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<EstadoCivil> m = estadoCivilService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EstadoCivil findById(@PathParam("id") long id) {
        EstadoCivil m = estadoCivilService.findById(id);
        return m;
    }

    @GET
    @Path("nivel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstadoCivil> findByNivel(@PathParam("id") long id) {
        return estadoCivilService.findAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(EstadoCivil m) {
        estadoCivilService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") long id, EstadoCivil entity) {
        estadoCivilService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") long id) {
        estadoCivilService.delete(id);
    }

}
