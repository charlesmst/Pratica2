package br.com.empresa.rh.resources;


import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.CurriculoService;
import br.com.empresa.rh.model.Curriculo;
import br.com.empresa.rh.model.Pessoa;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.model.view.Recrutamento;
import br.com.empresa.rh.response.CountResponse;
import br.com.empresa.rh.service.CidadeService;
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
@Path("/curriculo")
@RolesAllowed(NivelAcesso.CANDIDATO)
public class CurriculoResource {

    @Autowired
    private CurriculoService curriculoService;
    
    @Autowired
    private PessoaService pessoaService;

    @Context
    protected UriInfo info;

    public CurriculoService getCurriculoService() {
        return curriculoService;
    }

    public void setCurriculoService(CurriculoService marcaService) {
        this.curriculoService = marcaService;
    }

    public CurriculoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(curriculoService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Recrutamento.Curriculo.class)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        List<Curriculo> m = curriculoService.findForTable(request);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Recrutamento.Curriculo.class)
    public Curriculo findById(@PathParam("id") int id) {
        Curriculo m = curriculoService.findById(id);
        return m;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Curriculo m) {
        curriculoService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, Curriculo entity) {
        Pessoa p = pessoaService.findById(entity.getPessoaId());
        Pessoa e = entity.getPessoa();
        p.setNome(e.getNome());
        p.setDataNascimento(e.getDataNascimento());
        p.setCpf(e.getCpf());
        p.setRg(e.getRg());
        p.setEmail(e.getEmail());
        p.setTelCelular(e.getTelCelular());
        p.setTelFixo(e.getTelFixo());
        p.setCep(e.getCep());
        p.setBairro(e.getBairro());
        p.setEndereco(e.getEndereco());
        p.setCidade(e.getCidade());
        p.setEscolaridade(e.getEscolaridade());
        p.setEstadoCivil(e.getEstadoCivil());
        p.setSexo(e.getSexo());
        p.setCnh(e.getCnh());
        p.setCnhCategoria(e.getCnhCategoria());
   
        pessoaService.update(p);
        
        curriculoService.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        curriculoService.delete(id);
    }

}
