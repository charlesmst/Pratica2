package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.PessoaCarteiraService;
import br.com.empresa.rh.model.PessoaCarteira;
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
@Path("/pessoa-carteira")
@RolesAllowed(NivelAcesso.CANDIDATO)
public class PessoaCarteiraResource {

    @Autowired
    private PessoaCarteiraService pessoaCarteiraService;

    @Context
    protected UriInfo info;

    public PessoaCarteiraService getPessoaCarteiraService() {
        return pessoaCarteiraService;
    }

    public void setPessoaCarteiraService(PessoaCarteiraService marcaService) {
        this.pessoaCarteiraService = marcaService;
    }

    public PessoaCarteiraResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(pessoaCarteiraService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<PessoaCarteira> m = pessoaCarteiraService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PessoaCarteira findById(@PathParam("id") long id) {
        PessoaCarteira m = pessoaCarteiraService.findById(id);
        return m;
    }

    @GET
    @Path("nivel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PessoaCarteira> findByNivel(@PathParam("id") long id) {
        return pessoaCarteiraService.findAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(PessoaCarteira m) {
        pessoaCarteiraService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") long id, PessoaCarteira entity) {
        pessoaCarteiraService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") long id) {
        pessoaCarteiraService.delete(id);
    }

}
