package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.EventoService;
import br.com.empresa.rh.model.Evento;
import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.Parametro;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.response.CountResponse;
import br.com.empresa.rh.service.folha.CalculoFolha;
import br.com.empresa.rh.service.folha.EventoCollection;
import br.com.empresa.rh.service.folha.Parametros;
import java.math.BigDecimal;
import java.util.Arrays;
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
@Path("/evento")
@RolesAllowed(NivelAcesso.RH)
public class EventoResource {

    @Autowired
    private EventoService eventoService;

    
    @Autowired
    private CalculoFolha calculoFolha;
    
    @Context
    protected UriInfo info;

    public EventoService getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoService marcaService) {
        this.eventoService = marcaService;
    }

    public EventoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(eventoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Evento> m = eventoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Evento findById(@PathParam("id") long id) {
        Evento m = eventoService.findById(id);
        return m;
    }

    @GET
    @Path("nivel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evento> findByNivel(@PathParam("id") long id) {
        return eventoService.findAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Evento m) {
        eventoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") long id, Evento entity) {
        eventoService.update(entity);
		
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") long id) {
        eventoService.delete(id);
    }

    
    @POST
    @Path("test")
    @Consumes({MediaType.APPLICATION_JSON})
    public EventoCollection testar(Evento m) {
        EventoCollection c = new EventoCollection(Arrays.asList(m));
//        Funcionario f = new Funcionario();
//        calculoFolha.setEventos(c);
//        calculoFolha.calcula(f);
        return c;
    }
}
