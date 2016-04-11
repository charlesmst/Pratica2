package br.com.empresa.rh.resources;

import br.com.empresa.rh.service.NivelService;
import br.com.empresa.rh.model.Nivel;
import java.util.ArrayList;
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
@Path("/nivel")
public class NivelResource {

//    @Autowired
//    private NivelService nivelService;
//
//    public NivelService getNivelService() {
//        return nivelService;
//    }

//    public void setNivelService(NivelService marcaService) {
//        this.nivelService = marcaService;
//    }
//
//    @GET
//    @Produces("application/json")
//    @Path("count")
//    public long count() {
//        return nivelService.count();
//    }
    @GET
    @Produces({"application/json","application/xml"})
    public List<Nivel> findAll() {
//        return nivelService.findAll();
        return new ArrayList<>();
    }

//    @GET
//    @Produces("application/json")
//    public List<Nivel> findFilter(@QueryParam("filter") String filter,@QueryParam("order") String order,@QueryParam("limit") int limit,@QueryParam("page") int page ) {
//        return nivelService.findAll();
//    }
//    @GET
//    @Path("{id}")
//    @Produces("application/json")
//    public Nivel findById(@PathParam("id") int id) {
//        Nivel m = nivelService.findById(id);
//        return m;
//    }
//
//    @POST
//    @Consumes({"application/xml", "application/json"})
//    public void insert(Nivel m) {
//        nivelService.insert(m);
//    }
//
//    @POST
//    @Consumes({"application/xml", "application/json"})
//    @Path("{id}")
//    public void update(@PathParam("id") long id, Nivel m) {
//        Nivel n = nivelService.findById(id);
//        n.setNome(m.getNome());
//        nivelService.update(n);
//    }
//    
//    @DELETE
//    @Path("{id}")
//    @Produces("application/json")
//    public void delete(@PathParam("id") Integer id) {
//        nivelService.delete(id);
//    }

   
}
