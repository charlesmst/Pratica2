package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.FuncionarioService;
import br.com.empresa.rh.model.Funcionario;
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
@Path("/funcionario")
@RolesAllowed(NivelAcesso.RH)
public class FuncionarioResource {

    @Autowired
    private FuncionarioService funcionarioService;

    @Context
    protected UriInfo info;

    public FuncionarioService getFuncionarioService() {
        return funcionarioService;
    }

    public void setFuncionarioService(FuncionarioService marcaService) {
        this.funcionarioService = marcaService;
    }

    public FuncionarioResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(funcionarioService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Funcionario> m = funcionarioService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Funcionario findById(@PathParam("id") long id) {
        Funcionario m = funcionarioService.findById(id);
        return m;
    }

    @GET
    @Path("nivel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Funcionario> findByNivel(@PathParam("id") long id) {
        return funcionarioService.findAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Funcionario m) {
        funcionarioService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") long id, Funcionario entity) {
        funcionarioService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") long id) {
        funcionarioService.delete(id);
    }

}