package br.com.empresa.rh.resources;

import br.com.empresa.rh.service.MenuService;
import br.com.empresa.rh.model.Menu;
import br.com.empresa.rh.model.Nivel;
import br.com.empresa.rh.request.TableRequest;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/menu")
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
    public long count() {
        return menuService.count();
    }

    @GET
    @Produces("application/json")

    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Menu> m = menuService.findForTable(request);
        return Response.ok().entity(m).build();
    }

//    @GET
//    @Produces("application/json")
//    public List<Menu> findFilter(@QueryParam("filter") String filter, @QueryParam("order") String order, @QueryParam("limit") int limit, @QueryParam("page") int page) {
//        return menuService.findAll();
//    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Menu findById(@PathParam("id") int id) {
        Menu m = menuService.findById(id);
        return m;
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
    public void delete(@PathParam("id") Integer id) {
        menuService.delete(id);
    }

}
