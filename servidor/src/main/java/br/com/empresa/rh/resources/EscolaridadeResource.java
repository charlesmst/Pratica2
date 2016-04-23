package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.EscolaridadeService;
import br.com.empresa.rh.model.Escolaridade;
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
@Path("/escolaridade")
@RolesAllowed(NivelAcesso.CANDIDATO)
public class EscolaridadeResource {

    @Autowired
    private EscolaridadeService escolaridadeService;

    @Context
    protected UriInfo info;

    public EscolaridadeService getEscolaridadeService() {
        return escolaridadeService;
    }

    public void setEscolaridadeService(EscolaridadeService marcaService) {
        this.escolaridadeService = marcaService;
    }

    public EscolaridadeResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(escolaridadeService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Escolaridade> m = escolaridadeService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Escolaridade findById(@PathParam("id") long id) {
        Escolaridade m = escolaridadeService.findById(id);
        return m;
    }

    @GET
    @Path("nivel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Escolaridade> findByNivel(@PathParam("id") long id) {
        return escolaridadeService.findAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Escolaridade m) {
        escolaridadeService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") long id, Escolaridade entity) {
        escolaridadeService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") long id) {
        escolaridadeService.delete(id);
    }

}
