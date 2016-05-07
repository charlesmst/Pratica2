package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.EmpresaService;
import br.com.empresa.rh.model.Empresa;
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
@Path("/empresa")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class EmpresaResource {

    @Autowired
    private EmpresaService empresaService;

    @Context
    protected UriInfo info;

    public EmpresaService getEmpresaService() {
        return empresaService;
    }

    public void setEmpresaService(EmpresaService marcaService) {
        this.empresaService = marcaService;
    }

    public EmpresaResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(empresaService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Empresa> m = empresaService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("listagem")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Folha.EmpresaVisualizacao.class)
    public Response findListagem() {
        TableRequest request = TableRequest.build(info);
        List<Empresa> m = empresaService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Empresa findById(@PathParam("id") int id) {
        Empresa m = empresaService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Empresa m) {
        empresaService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, Empresa entity) {
        empresaService.update(entity);

    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        empresaService.delete(id);
    }

}
