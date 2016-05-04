/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.SecurityFilter;
import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.model.Usuario;
import br.com.empresa.rh.model.request.UsuarioSenha;
import br.com.empresa.rh.model.response.LoginResponse;
import br.com.empresa.rh.model.response.RestResponseUser;
import br.com.empresa.rh.service.UsuarioService;
import br.com.empresa.rh.util.ApiException;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * REST Web Service
 *
 * @author charles
 */
@Path("authorize")
@Component
public class AuthorizeResource {

    @Autowired
    private UsuarioService usuarioService;

    public AuthorizeResource() {
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed(NivelAcesso.CANDIDATO)
    public RestResponseUser whoAmI(@Context SecurityContext context) {
        RestResponseUser l = new RestResponseUser();
        l.setNome(context.getUserPrincipal().getName());
        for (int i = 5; i >= 1; i--) {
            if (context.isUserInRole(i + "")) {
                l.setNivel(i + "");
                break;
            }
        }
        return l;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateToken(UsuarioSenha user) {

        Usuario u = usuarioService.pessoaUsuario(user.getUsuario());
        if (u != null && u.getSenha().equals(user.getSenha())) {
            String key = SecurityFilter.encodeAuthorize(u.getPessoaId() + "", u.getNivel() + "");
            LoginResponse r = new LoginResponse(key, u.getPessoa().getNome());
            r.setEmail(u.getPessoa().getEmail());
            r.setFoto("");
            return Response.ok(r).build();
        }
        throw new ApiException("Usuário ou senha incorreto");

    }
}
