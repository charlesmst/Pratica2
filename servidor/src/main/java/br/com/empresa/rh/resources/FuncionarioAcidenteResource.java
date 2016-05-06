package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.FuncionarioAcidenteService;
import br.com.empresa.rh.model.FuncionarioAcidente;
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
    @Path("/funcionario-acidente")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class FuncionarioAcidenteResource {

    @Autowired
    private FuncionarioAcidenteService FuncionarioAcidenteService;

    @Context
    protected UriInfo info;

    public FuncionarioAcidenteService getFuncionarioAcidenteService() {
        return FuncionarioAcidenteService;
    }

    public void setFuncionarioAcidenteService(FuncionarioAcidenteService marcaService) {
        this.FuncionarioAcidenteService = marcaService;
    }

    public FuncionarioAcidenteResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(FuncionarioAcidenteService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<FuncionarioAcidente> m = FuncionarioAcidenteService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public FuncionarioAcidente findById(@PathParam("id") int id) {
        FuncionarioAcidente m = FuncionarioAcidenteService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(FuncionarioAcidente m) {
        FuncionarioAcidenteService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, FuncionarioAcidente entity) {
        FuncionarioAcidenteService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        FuncionarioAcidenteService.delete(id);
    }

}
