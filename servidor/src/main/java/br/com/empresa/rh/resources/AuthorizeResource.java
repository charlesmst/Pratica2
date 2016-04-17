/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.SecurityFilter;
import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.model.request.UsuarioSenha;
import br.com.empresa.rh.model.response.RestResponseUser;
import java.util.Arrays;
import javax.ws.rs.core.Context;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.springframework.stereotype.Component;

/**
 * REST Web Service
 *
 * @author charles
 */
@Path("authorize")
@Component
public class AuthorizeResource {

    public AuthorizeResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponseUser whoAmI(@Context SecurityContext context) {
        RestResponseUser l = new RestResponseUser();
        String name = context.getUserPrincipal().getName();
        boolean admin = context.isUserInRole("admin");
        l.setNome(context.getUserPrincipal().getName());

        return l;

    }

    @GET
    @Path("token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateToken() {
//        JWTManager m = JWTManager.getInstance();
        
        String key = SecurityFilter.encodeAuthorize("Charles", NivelAcesso.ADMIN);

        return Response.ok(key).build();
        
    }
}
