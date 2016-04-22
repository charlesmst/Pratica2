package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.NecessidadePessoaService;
import br.com.empresa.rh.model.NecessidadePessoa;
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
@Path("/necessidade-pessoa")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class NecessidadePessoaResource {

    @Autowired
    private NecessidadePessoaService necessidadePessoaService;

    @Context
    protected UriInfo info;

    public NecessidadePessoaService getNecessidadePessoaService() {
        return necessidadePessoaService;
    }

    public void setNecessidadePessoaService(NecessidadePessoaService marcaService) {
        this.necessidadePessoaService = marcaService;
    }

    public NecessidadePessoaResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(necessidadePessoaService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<NecessidadePessoa> m = necessidadePessoaService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public NecessidadePessoa findById(@PathParam("id") long id) {
        NecessidadePessoa m = necessidadePessoaService.findById(id);
        return m;
    }

    @GET
    @Path("nivel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NecessidadePessoa> findByNivel(@PathParam("id") long id) {
        return necessidadePessoaService.findAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(NecessidadePessoa m) {
        necessidadePessoaService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") long id, NecessidadePessoa entity) {
        necessidadePessoaService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") long id) {
        necessidadePessoaService.delete(id);
    }

}