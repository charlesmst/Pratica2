package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.CurriculoQualificacaoService;
import br.com.empresa.rh.model.CurriculoQualificacao;
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
@Path("/curriculo-qualificacao")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class CurriculoQualificacaoResource {

    @Autowired
    private CurriculoQualificacaoService CurriculoQualificacaoService;

    @Context
    protected UriInfo info;

    public CurriculoQualificacaoService getCurriculoQualificacaoService() {
        return CurriculoQualificacaoService;
    }

    public void setCurriculoQualificacaoService(CurriculoQualificacaoService marcaService) {
        this.CurriculoQualificacaoService = marcaService;
    }

    public CurriculoQualificacaoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(CurriculoQualificacaoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<CurriculoQualificacao> m = CurriculoQualificacaoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CurriculoQualificacao findById(@PathParam("id") int id) {
        CurriculoQualificacao m = CurriculoQualificacaoService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(CurriculoQualificacao m) {
        CurriculoQualificacaoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, CurriculoQualificacao entity) {
        CurriculoQualificacaoService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        CurriculoQualificacaoService.delete(id);
    }

}
