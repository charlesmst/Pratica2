package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.EventoFuncionarioService;
import br.com.empresa.rh.model.EventoFuncionario;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.model.view.Folha;
import br.com.empresa.rh.response.CountResponse;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.mail.Folder;
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
@Path("/eventofuncionario")
@RolesAllowed(NivelAcesso.RH)
public class EventoFuncionarioResource {

    @Autowired
    private EventoFuncionarioService EventoFuncionarioService;

    @Context
    protected UriInfo info;

    public EventoFuncionarioService getEventoFuncionarioService() {
        return EventoFuncionarioService;
    }

    public void setEventoFuncionarioService(EventoFuncionarioService marcaService) {
        this.EventoFuncionarioService = marcaService;
    }

    public EventoFuncionarioResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("funcionario/{id}/{mes}/{ano}/count")
    public CountResponse count(@PathParam("id") int cargoId,@PathParam("mes") int mes,@PathParam("ano") int ano) {
        FuncionarioCargo cargo = new FuncionarioCargo();
        cargo.setId(cargoId);
        return new CountResponse(EventoFuncionarioService.count(cargo,mes,ano));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("funcionario/{id}/{mes}/{ano}")
    @JsonView(Folha.FuncionarioEvento.class)
    public Response findAll(@PathParam("id") int cargoId,@PathParam("mes") int mes,@PathParam("ano") int ano) {
        TableRequest request = TableRequest.build(info);
        FuncionarioCargo cargo = new FuncionarioCargo();
        cargo.setId(cargoId);
        List<EventoFuncionario> m = EventoFuncionarioService.findForTable(request, cargo,mes,ano);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EventoFuncionario findById(@PathParam("id") int id) {
        EventoFuncionario m = EventoFuncionarioService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(EventoFuncionario m) {
        EventoFuncionarioService.insert(m);
    }


    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        EventoFuncionarioService.delete(id);
    }

}
