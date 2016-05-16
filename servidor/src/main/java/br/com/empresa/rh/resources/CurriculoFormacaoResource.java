package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.CurriculoFormacaoService;
import br.com.empresa.rh.model.CurriculoFormacao;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.model.view.Recrutamento;
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
@Path("/curriculo-formacao")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class CurriculoFormacaoResource {

    @Autowired
    private CurriculoFormacaoService curriculoFormacaoService;

    @Context
    protected UriInfo info;

    public CurriculoFormacaoService getCurriculoFormacaoService() {
        return curriculoFormacaoService;
    }

    public void setCurriculoFormacaoService(CurriculoFormacaoService marcaService) {
        this.curriculoFormacaoService = marcaService;
    }

    public CurriculoFormacaoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(curriculoFormacaoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<CurriculoFormacao> m = curriculoFormacaoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CurriculoFormacao findById(@PathParam("id") int id) {
        CurriculoFormacao m = curriculoFormacaoService.findById(id);
        return m;
    }

    @GET
    @Path("pessoa/{pessoaId}")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed(NivelAcesso.RH)
    @JsonView(Recrutamento.CurriculoFormacao.class)
    public List<CurriculoFormacao> findForPessoa(@PathParam("pessoaId") int id) {
        return curriculoFormacaoService.findForPessoa(id);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(CurriculoFormacao m) {
        curriculoFormacaoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, CurriculoFormacao entity) {
        curriculoFormacaoService.update(entity);

    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        curriculoFormacaoService.delete(id);
    }

}
