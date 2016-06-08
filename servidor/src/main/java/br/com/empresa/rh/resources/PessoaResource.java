package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.service.PessoaService;
import br.com.empresa.rh.model.Pessoa;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.model.view.Folha;
import br.com.empresa.rh.response.CountResponse;
import br.com.empresa.rh.util.ApiException;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletContext;
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
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/pessoa")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @Context
    protected UriInfo info;

    public PessoaService getPessoaService() {
        return pessoaService;
    }

    public void setPessoaService(PessoaService marcaService) {
        this.pessoaService = marcaService;
    }

    public PessoaResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count() {
        return new CountResponse(pessoaService.count());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Folha.FuncionarioFicha.class)
    public Response findAll() {
        TableRequest request = TableRequest.build(info);
        boolean somenteF = false;
        if(info.getQueryParameters().containsKey("somenteFuncionarios") ){
            String v = info.getQueryParameters().getFirst("somenteFuncionarios");
            somenteF = v.equals("1");
        }
        List<Pessoa> m = pessoaService.findForTable(request, somenteF);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @JsonView(Folha.FuncionarioFicha.class)

    @Produces(MediaType.APPLICATION_JSON)
    public Pessoa findById(@PathParam("id") int id) {
        Pessoa m = pessoaService.findById(id);
        return m;
    }

    @GET
    @Path("nivel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> findByNivel(@PathParam("id") int id) {
        return pessoaService.findAll();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Pessoa m, @Context ServletContext servletContext) {
        manageUploadFile(m, servletContext);
        pessoaService.insert(m);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void update(@PathParam("id") int id, Pessoa entity, @Context ServletContext servletContext) {
        manageUploadFile(entity, servletContext);
        pessoaService.update(entity);
    }

    /**
     * Detecta se enviou a imagem em base 64, e faz upload
     */
    public void manageUploadFile(Pessoa pessoa, ServletContext servletContext) {
        if (pessoa.getImagem() != null && pessoa.getImagem().startsWith("data:image")) {
            Pattern p = Pattern.compile("^data:image\\/(png|jpg|jpeg);base64,");
            Matcher m = p.matcher(pessoa.getImagem());
            if (!m.find()) {
                return;
            }

            String base64Image = pessoa.getImagem().substring(m.group().length());

            String extension = m.group(1);
            if (extension.equals("jpeg")) {
                extension = "jpg";
            }
            String fileName = UUID.randomUUID().toString() + "." + extension;
            String fullName = servletContext.getRealPath("images/" + fileName);
            InputStream stream = new ByteArrayInputStream(Base64.getDecoder().decode(base64Image.getBytes()));

            OutputStream outStream;
            try {
                File targetFile = new File(fullName);
                byte[] buffer = new byte[stream.available()];
                stream.read(buffer);
                outStream = new FileOutputStream(targetFile);
                outStream.write(buffer);
                pessoa.setImagem(fileName);
            } catch (Exception ex) {
                Logger.getLogger(PessoaResource.class.getName()).log(Level.SEVERE, null, ex);
                throw new ApiException("Não foi possível atualizar a imagem");
            }
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        pessoaService.delete(id);
    }

}
