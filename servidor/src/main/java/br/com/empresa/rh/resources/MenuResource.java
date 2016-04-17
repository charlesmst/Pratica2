package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.MenuService;
import br.com.empresa.rh.model.Menu;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/menu")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class MenuResource {

    @Autowired
    private MenuService menuService;

    @Context
    protected UriInfo info;

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService marcaService) {
        this.menuService = marcaService;
    }

    public MenuResource() {
    }

    @GET
    @Produces("application/json")
    @Path("count")
    public CountResponse count() {
        return new CountResponse(menuService.count());
    }

    @GET
    @Produces("application/json")
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Menu> m = menuService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Menu findById(@PathParam("id") long id) {
        Menu m = menuService.findById(id);
        return m;
    }

    @GET
    @Path("nivel/{id}")
    @Produces("application/json")
    public List<Menu> findByNivel(@PathParam("id") long id) {
        return menuService.findAll();
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void insert(Menu m) {
        menuService.insert(m);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    @Path("{id}")
    public void update(@PathParam("id") long id, Menu m) {
        Menu n = menuService.findById(id);
        n.setIcone(m.getIcone());
        n.setUrl(m.getUrl());
        menuService.update(n);
    }

    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public void delete(@PathParam("id") long id) {
        menuService.delete(id);
    }

}
