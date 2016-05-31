package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.util.Utilitarios;
import java.util.HashMap;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/permissoes")
@RolesAllowed(NivelAcesso.CANDIDATO)
public class PermissoesResource {

    @Context
    private SecurityContext secutiryContext;

    @Autowired
    private br.com.empresa.rh.util.Utilitarios utilitarios;

    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("folha")
    public HashMap<String, Boolean> folha() {
        utilitarios.setSecutiryContext(secutiryContext);
        
        HashMap<String, Boolean> permissoes = new HashMap<>();
        permissoes.put("excluir", utilitarios.usuarioTemPermissao(NivelAcesso.ADMIN));
        permissoes.put("outrosFuncionarios",  utilitarios.usuarioTemPermissao(NivelAcesso.RH));
        return permissoes;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("recrutamento")
    @RolesAllowed(NivelAcesso.NENHUM)

    public HashMap<String, Boolean> recrutamento() {
        utilitarios.setSecutiryContext(secutiryContext);
        
        HashMap<String, Boolean> permissoes = new HashMap<>();
        permissoes.put("usuario", utilitarios.usuarioTemPermissao(NivelAcesso.RH));
        //permissoes.put("usuario", utilitarios.usuarioTemPermissao(NivelAcesso.RH) || utilitarios.usuarioIs(NivelAcesso.CANDIDATO));
//        permissoes.put("outrosFuncionarios",  utilitarios.usuarioTemPermissao(NivelAcesso.RH));
        return permissoes;

    }
}
