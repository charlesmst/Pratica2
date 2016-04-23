package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.NecessidadeEspecialService;
import br.com.empresa.rh.model.NecessidadeEspecial;
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
@Path("/necessidade-especial")
@RolesAllowed(NivelAcesso.CANDIDATO)
public class NecessidadeEspecialResource {

    @Autowired
    private NecessidadeEspecialService necessidadeEspecialService;

    @Context
    protected UriInfo info;

    public NecessidadeEspecialService getNecessidadeEspecialService() {
        return necessidadeEspecialService;
    }

    public void setNecessidadeEspecialService(NecessidadeEspecialService marcaService) {
        this.necessidadeEspecialService = marcaService;
    }

    public NecessidadeEspecialResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(necessidadeEspecialService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<NecessidadeEspecial> m = necessidadeEspecialService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public NecessidadeEspecial findById(@PathParam("id") long id) {
        NecessidadeEspecial m = necessidadeEspecialService.findById(id);
        return m;
    }

    @GET
    @Path("nivel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NecessidadeEspecial> findByNivel(@PathParam("id") long id) {
        return necessidadeEspecialService.findAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(NecessidadeEspecial m) {
        necessidadeEspecialService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") long id, NecessidadeEspecial entity) {
        necessidadeEspecialService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") long id) {
        necessidadeEspecialService.delete(id);
    }

}
