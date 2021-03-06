package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.QualificacaoService;
import br.com.empresa.rh.model.Qualificacao;
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
@Path("/qualificacao")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class QualificacaoResource {

    @Autowired
    private QualificacaoService QualificacaoService;

    @Context
    protected UriInfo info;

    public QualificacaoService getQualificacaoService() {
        return QualificacaoService;
    }

    public void setQualificacaoService(QualificacaoService marcaService) {
        this.QualificacaoService = marcaService;
    }

    public QualificacaoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(QualificacaoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Qualificacao> m = QualificacaoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Qualificacao findById(@PathParam("id") int id) {
        Qualificacao m = QualificacaoService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Qualificacao m) {
        QualificacaoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, Qualificacao entity) {
        QualificacaoService.update(entity);		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        QualificacaoService.delete(id);
    }

}
