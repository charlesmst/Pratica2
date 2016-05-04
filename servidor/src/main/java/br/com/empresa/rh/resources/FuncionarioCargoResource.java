package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.FuncionarioCargoService;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.model.view.Folha;
import br.com.empresa.rh.response.CountResponse;
import com.fasterxml.jackson.annotation.JsonView;
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
@Path("/funcionariocargo")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class FuncionarioCargoResource {

    @Autowired
    private FuncionarioCargoService funcionarioCargoService;

    @Context
    protected UriInfo info;

    public FuncionarioCargoService getFuncionarioCargoService() {
        return funcionarioCargoService;
    }

    public void setFuncionarioCargoService(FuncionarioCargoService marcaService) {
        this.funcionarioCargoService = marcaService;
    }

    public FuncionarioCargoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(funcionarioCargoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView({Folha.FuncionarioCargo.class})
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<FuncionarioCargo> m = funcionarioCargoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public FuncionarioCargo findById(@PathParam("id") int id) {
        FuncionarioCargo m = funcionarioCargoService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(FuncionarioCargo m) {
        funcionarioCargoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, FuncionarioCargo entity) {
        funcionarioCargoService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        funcionarioCargoService.delete(id);
    }

}
