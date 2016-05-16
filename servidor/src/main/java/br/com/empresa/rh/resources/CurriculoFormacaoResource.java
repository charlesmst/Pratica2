package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.CurriculoFormacaoService;
import br.com.empresa.rh.model.CurriculoFormacao;
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
@Path("/curriculo-formacao")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class CurriculoFormacaoResource {

    @Autowired
    private CurriculoFormacaoService CurriculoFormacaoService;

    @Context
    protected UriInfo info;

    public CurriculoFormacaoService getCurriculoFormacaoService() {
        return CurriculoFormacaoService;
    }

    public void setCurriculoFormacaoService(CurriculoFormacaoService marcaService) {
        this.CurriculoFormacaoService = marcaService;
    }

    public CurriculoFormacaoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(CurriculoFormacaoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<CurriculoFormacao> m = CurriculoFormacaoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CurriculoFormacao findById(@PathParam("id") int id) {
        CurriculoFormacao m = CurriculoFormacaoService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(CurriculoFormacao m) {
        CurriculoFormacaoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, CurriculoFormacao entity) {
        CurriculoFormacaoService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        CurriculoFormacaoService.delete(id);
    }

}
