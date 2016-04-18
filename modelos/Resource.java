<#if package?? && package != "">
package ${package};

</#if>

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.${name}Service;
import br.com.empresa.rh.model.${name};
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
@Path("/${name}")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class ${name}Resource {

    @Autowired
    private ${name}Service ${name}Service;

    @Context
    protected UriInfo info;

    public ${name}Service get${name}Service() {
        return ${name}Service;
    }

    public void set${name}Service(${name}Service marcaService) {
        this.${name}Service = marcaService;
    }

    public ${name}Resource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(${name}Service.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<${name}> m = ${name}Service.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ${name} findById(@PathParam("id") long id) {
        ${name} m = ${name}Service.findById(id);
        return m;
    }

    @GET
    @Path("nivel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<${name}> findByNivel(@PathParam("id") long id) {
        return ${name}Service.findAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(${name} m) {
        ${name}Service.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") long id, ${name} entity) {
        ${name}Service.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") long id) {
        ${name}Service.delete(id);
    }

}
