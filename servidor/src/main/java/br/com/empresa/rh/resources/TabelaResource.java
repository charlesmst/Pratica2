package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.TabelaService;
import br.com.empresa.rh.model.Tabela;
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
@Path("/tabela")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class TabelaResource {

    @Autowired
    private TabelaService tabelaService;

    @Context
    protected UriInfo info;

    public TabelaService getTabelaService() {
        return tabelaService;
    }

    public void setTabelaService(TabelaService marcaService) {
        this.tabelaService = marcaService;
    }

    public TabelaResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(tabelaService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("tipos")
    public List<String> tiposExistentes() {
        return tabelaService.tipos();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Tabela> m = tabelaService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tabela findById(@PathParam("id") int id) {
        Tabela m = tabelaService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Tabela m) {
        tabelaService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, Tabela entity) {
        tabelaService.update(entity);

    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        tabelaService.delete(id);
    }

}
