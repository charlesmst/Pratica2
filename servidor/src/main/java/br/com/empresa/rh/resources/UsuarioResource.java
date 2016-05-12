package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.model.Pessoa;
import br.com.empresa.rh.service.UsuarioService;
import br.com.empresa.rh.model.Usuario;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.model.view.Recrutamento;
import br.com.empresa.rh.response.CountResponse;
import br.com.empresa.rh.service.PessoaService;
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
@Path("/usuario")
@RolesAllowed(NivelAcesso.CANDIDATO)
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PessoaService pessoaService;

    @Context
    protected UriInfo info;

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService marcaService) {
        this.usuarioService = marcaService;
    }

    public UsuarioResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(usuarioService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Recrutamento.Usuario.class)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Usuario> m = usuarioService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Recrutamento.Usuario.class)
    public Usuario findById(@PathParam("id") int id) {
        Usuario m = usuarioService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Usuario m) {
        usuarioService.insert(m);
        Pessoa p = new Pessoa();
        
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, Usuario entity) {
        Pessoa p = pessoaService.findById(entity.getPessoaId());
        p.setNome(entity.getPessoa().getNome());
        p.setCpf(entity.getPessoa().getCpf());
        p.setEmail(entity.getPessoa().getEmail());
        p.setRg(entity.getPessoa().getRg());
        p.setTelCelular(entity.getPessoa().getTelCelular());
        p.setTelFixo(entity.getPessoa().getTelFixo());
        p.setSexo(entity.getPessoa().getSexo());
        p.setDataNascimento(entity.getPessoa().getDataNascimento());
        pessoaService.update(p);
        
        Usuario u = usuarioService.findById(id);
        u.setUsuario(entity.getUsuario().toUpperCase());
        u.setSenha(entity.getSenha());
        usuarioService.update(u);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        usuarioService.delete(id);
    }

}
