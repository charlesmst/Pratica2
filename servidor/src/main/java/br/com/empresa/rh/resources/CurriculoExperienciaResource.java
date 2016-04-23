package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.CurriculoExperienciaService;
import br.com.empresa.rh.model.CurriculoExperiencia;
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
@Path("/curriculo-experiencia")
@RolesAllowed(NivelAcesso.CANDIDATO)
public class CurriculoExperienciaResource {

    @Autowired
    private CurriculoExperienciaService curriculoExperienciaService;

    @Context
    protected UriInfo info;

    public CurriculoExperienciaService getCurriculoExperienciaService() {
        return curriculoExperienciaService;
    }

    public void setCurriculoExperienciaService(CurriculoExperienciaService marcaService) {
        this.curriculoExperienciaService = marcaService;
    }

    public CurriculoExperienciaResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(curriculoExperienciaService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<CurriculoExperiencia> m = curriculoExperienciaService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CurriculoExperiencia findById(@PathParam("id") long id) {
        CurriculoExperiencia m = curriculoExperienciaService.findById(id);
        return m;
    }

    @GET
    @Path("nivel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CurriculoExperiencia> findByNivel(@PathParam("id") long id) {
        return curriculoExperienciaService.findAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(CurriculoExperiencia m) {
        curriculoExperienciaService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") long id, CurriculoExperiencia entity) {
        curriculoExperienciaService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") long id) {
        curriculoExperienciaService.delete(id);
    }

}
