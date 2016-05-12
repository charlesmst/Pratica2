package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.model.Empresa;
import br.com.empresa.rh.service.FolhaCalculadaService;
import br.com.empresa.rh.model.FolhaCalculada;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.model.response.FolhaResponse;
import br.com.empresa.rh.model.view.Folha;
import br.com.empresa.rh.response.CountResponse;
import br.com.empresa.rh.service.FuncionarioCargoService;
import br.com.empresa.rh.util.ApiException;
import br.com.empresa.rh.util.Relatorios;
import br.com.empresa.rh.util.SecurityApiException;
import br.com.empresa.rh.util.Utilitarios;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;
import net.sf.jasperreports.engine.JRException;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/folhacalculada")
@RolesAllowed(NivelAcesso.FUNCIONARIO)
public class FolhaCalculadaResource {

    @Autowired
    private FolhaCalculadaService folhaCalculadaService;

    @Autowired
    private FuncionarioCargoService funcionarioCargoService;
    @Autowired
    private Utilitarios utilitarios;
    @Autowired
    private Relatorios relatorios;

    @Context
    protected UriInfo info;

    public FolhaCalculadaResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public CountResponse count(@Context SecurityContext securityContext) {
        utilitarios.setSecutiryContext(securityContext);

        TableRequest request = TableRequest.build(info);
        MultivaluedMap<String, String> queryString = info.getQueryParameters();
        int mes = 0;
        int ano = 0;
        Empresa empresa = null;
        List<FuncionarioCargo> funcionarios = null;
        if (queryString.containsKey("mes")) {
            mes = Integer.parseInt(queryString.getFirst("mes"));
        }
        if (queryString.containsKey("ano")) {
            ano = Integer.parseInt(queryString.getFirst("ano"));
        }
        if (queryString.containsKey("empresa")) {
            int e = Integer.parseInt(queryString.getFirst("empresa"));
            empresa = new Empresa();
            empresa.setId(e);
        }

        if (utilitarios.usuarioTemPermissao(NivelAcesso.RH)) {
            if (queryString.containsKey("functionarios")) {
                funcionarios = new ArrayList<>();
                for (String s : queryString.get("functionarios")) {
                    FuncionarioCargo f = new FuncionarioCargo();
                    f.setId(Integer.parseInt(s));
                    funcionarios.add(f);
                }
            }
        } else {
            //Se não tem acesso a rh, só consulta os dele mesmo
            funcionarios = funcionarioCargoService.findByFuncionario(utilitarios.usuario());

        }
        long c = folhaCalculadaService.count(request, empresa, mes, ano, funcionarios);

        return new CountResponse(c);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView({Folha.FolhaCalculada.class})
    public Response findAll(@Context SecurityContext securityContext) {
        utilitarios.setSecutiryContext(securityContext);
        TableRequest request = TableRequest.build(info);
        MultivaluedMap<String, String> queryString = info.getQueryParameters();
        int mes = 0;
        int ano = 0;
        Empresa empresa = null;
        List<FuncionarioCargo> funcionarios = null;
        if (queryString.containsKey("mes")) {
            mes = Integer.parseInt(queryString.getFirst("mes"));
        }
        if (queryString.containsKey("ano")) {
            ano = Integer.parseInt(queryString.getFirst("ano"));
        }
        if (queryString.containsKey("empresa")) {
            int e = Integer.parseInt(queryString.getFirst("empresa"));
            empresa = new Empresa();
            empresa.setId(e);
        }
        if (utilitarios.usuarioTemPermissao(NivelAcesso.RH)) {
            if (queryString.containsKey("functionarios")) {
                funcionarios = new ArrayList<>();
                for (String s : queryString.get("functionarios")) {
                    FuncionarioCargo f = new FuncionarioCargo();
                    f.setId(Integer.parseInt(s));
                    funcionarios.add(f);
                }
            }
        } else {
            //Se não tem acesso a rh, só consulta os dele mesmo
            funcionarios = funcionarioCargoService.findByFuncionario(utilitarios.usuario());
        }
        List<FolhaCalculada> m = folhaCalculadaService.findForTable(request, empresa, mes, ano, funcionarios);
        return Response.ok().entity(m).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public FolhaResponse findById(@PathParam("id") int id, @Context SecurityContext securityContext) {
        utilitarios.setSecutiryContext(securityContext);
        FolhaCalculada m = folhaCalculadaService.findById(id);
        if (!utilitarios.usuarioTemPermissao(NivelAcesso.RH)) {
            if (m.getFuncionarioCargo().getFuncionario().getPessoaId() != utilitarios.usuario()) {
                throw new SecurityApiException();
            }
        }
        FolhaResponse r = folhaCalculadaService.fromFolhaCalculada(m);
        if (!utilitarios.usuarioTemPermissao(NivelAcesso.RH)) {
            r.setEventosInvisiveis(null);
        }
        return r;
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(NivelAcesso.ADMIN)
    public void delete(@PathParam("id") int id) {
        folhaCalculadaService.delete(id);
    }

    @GET
    @Path("relatorio/{id}")

    public Response relatorio(@PathParam("id") int id, @Context SecurityContext securityContext, @Context ServletContext servletContext
    ) {
        utilitarios.setSecutiryContext(securityContext);
        FolhaCalculada m = folhaCalculadaService.findById(id);
        if (!utilitarios.usuarioTemPermissao(NivelAcesso.RH)) {
            if (m.getFuncionarioCargo().getFuncionario().getPessoaId() != utilitarios.usuario()) {
                throw new SecurityApiException();
            }
        }
        FolhaResponse r = folhaCalculadaService.fromFolhaCalculada(m);
        if (!utilitarios.usuarioTemPermissao(NivelAcesso.RH)) {
            r.setEventosInvisiveis(null);
        }
        List<FolhaResponse> l = Arrays.asList(r);
        final byte[] bytesData;
        try {
            bytesData = relatorios.generateReport(servletContext.getRealPath("folha.jrxml"), l, new HashMap<>());
        } catch (JRException ex) {
            throw new ApiException("Erro ao gerar o relatório: " + ex.getMessage());
        }
        ContentDisposition contentDisposition = new ContentDisposition("attachment;filename=folha.pdf");

        return Response.ok(
                new StreamingOutput() {
                    @Override
                    public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                        outputStream.write(bytesData);
                    }
                })
                .header("Content-Disposition", contentDisposition.toString())
                .header("Content-Type", "application/pdf")
                .build();
    }

}
