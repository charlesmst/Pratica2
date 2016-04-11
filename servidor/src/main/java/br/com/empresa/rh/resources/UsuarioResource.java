package br.com.empresa.rh.resources;

import br.com.empresa.rh.service.UsuarioService;
import br.com.empresa.rh.model.Usuario;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioDAO;

    public UsuarioService getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioService marcaDAO) {
        this.usuarioDAO = marcaDAO;
    }

    public UsuarioResource() {
    }

    @GET
    @Produces("application/json")
    @Path("count")
    public long count() {
        return usuarioDAO.count();
    }
    @GET
    @Produces("application/json")
    public List<Usuario> findAll() {
        return usuarioDAO.findAll();
    }

    @GET
    @Produces("application/json")
    public List<Usuario> findFilter(@QueryParam("filter") String filter,@QueryParam("order") String order,@QueryParam("limit") int limit,@QueryParam("page") int page ) {
        return usuarioDAO.findAll();
    }
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Usuario findById(@PathParam("id") int id) {
        Usuario m = usuarioDAO.findById(id);
        return m;
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void insert(Usuario m) {
        usuarioDAO.insert(m);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    @Path("{id}")
    public void update(@PathParam("id") long id, Usuario m) {
        Usuario n = usuarioDAO.findById(id);
        n.setUsuario(m.getUsuario());
        usuarioDAO.update(n);
    }
    
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public void delete(@PathParam("id") Integer id) {
        usuarioDAO.delete(id);
    }

    @POST
    @Path("/add")
    public Response insertByForm(@FormParam("id") int id, @FormParam("user") String name) {
        Usuario m = new Usuario();
        m.setId(id);
        m.setUsuario(name);
        usuarioDAO.insert(m);
        return Response.status(Response.Status.OK).build();
    }
}
