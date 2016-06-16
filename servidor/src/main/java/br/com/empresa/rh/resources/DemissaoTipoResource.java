package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.DemissaoTipoService;
import br.com.empresa.rh.model.DemissaoTipo;
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
@Path("/demissaotipo")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class DemissaoTipoResource {

    @Autowired
    private DemissaoTipoService DemissaoTipoService;

    @Context
    protected UriInfo info;

    public DemissaoTipoService getDemissaoTipoService() {
        return DemissaoTipoService;
    }

    public void setDemissaoTipoService(DemissaoTipoService marcaService) {
        this.DemissaoTipoService = marcaService;
    }

    public DemissaoTipoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(DemissaoTipoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<DemissaoTipo> m = DemissaoTipoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DemissaoTipo findById(@PathParam("id") int id) {
        DemissaoTipo m = DemissaoTipoService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(DemissaoTipo m) {
        DemissaoTipoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, DemissaoTipo entity) {
        DemissaoTipoService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        DemissaoTipoService.delete(id);
    }

}
