package br.com.empresa.rh.resources;

import br.com.empresa.rh.service.MarcaService;
import br.com.empresa.rh.model.Marca;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/marca")
public class MarcaResource {

    @Autowired
    private MarcaService marcaDAO;

    public MarcaService getMarcaDAO() {
        return marcaDAO;
    }

    public void setMarcaDAO(MarcaService marcaDAO) {
        this.marcaDAO = marcaDAO;
    }

    public MarcaResource() {
    }

    @GET
    @Produces("application/json")
    public List<Marca> findAll() {
        return marcaDAO.findAll();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Marca findById(@PathParam("id") Integer id) {
        Marca m = marcaDAO.findById(id);
        return m;
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void insert(Marca m) {
        marcaDAO.insert(m);
    }

    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public void delete(@PathParam("id") Integer id) {
        Marca m = marcaDAO.findById(id);
        marcaDAO.delete(m);
    }

    @POST
    @Path("/add")
    public Response insertByForm(@FormParam("id") int id, @FormParam("nome") String name) {
        Marca m = new Marca();
        m.setId(id);
        m.setDescricao(name);
        marcaDAO.insert(m);
        return Response.status(Response.Status.OK).build();
    }
}
