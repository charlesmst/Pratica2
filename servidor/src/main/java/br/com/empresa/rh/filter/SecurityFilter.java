/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.filter;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
//import org.jose4j.jwt.JwtClaims;
//import org.jose4j.jwt.MalformedClaimException;

/**
 *
 * @author charles
 */
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        SecurityContext originalContext = requestContext.getSecurityContext();
        MultivaluedMap<String, String> m = requestContext.getHeaders();

        //Busca do método, se não tiver o atributo, busca da classe
        RolesAllowed a = resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class);
        if (a == null) {
            a = resourceInfo.getResourceClass().getAnnotation(RolesAllowed.class);
        }
        if (a == null) {
            return;
        }
        if (a.value()[0].equals(NivelAcesso.NENHUM)) {
            return;
        }
        if (!m.containsKey(AUTHORIZATION_HEADER)) {
            buildResponseUnauthorized(requestContext, false);
            return;
        }

        String[] niveis = a.value();
        int nivelNecessario = 0;
        for (String nivel : niveis) {
            int n = NivelAcesso.getNivel(nivel);
            if (n > nivelNecessario) {
                nivelNecessario = n;
            }
        }

        try {
            //Pega o json web token do cabecalho
            String header = requestContext.getHeaderString(AUTHORIZATION_HEADER);
            //Transorma pro claim
            if (header == null) {
                buildResponseUnauthorized(requestContext, false);
                return;
            }
            Authorizer auth = decodeAuthorize(header, originalContext.isSecure());
            int nivelPessoa = 0;
            for (String group : auth.getRoles()) {
                int n = NivelAcesso.getNivel(group);
                if (n > nivelPessoa) {
                    nivelPessoa = n;
                }
            }

            if (nivelPessoa >= nivelNecessario) {
                //cria e seta a pessoa no security context
                requestContext.setSecurityContext(auth);
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(SecurityFilter.class.getName()).log(Level.SEVERE, null, ex);

        }
        buildResponseUnauthorized(requestContext, true);

    }

    private void buildResponseUnauthorized(ContainerRequestContext requestContext, boolean logado) {
        String mensagem = "Acesso não autorizado para o nível de acesso";
        if (!logado) {
            mensagem = "Acesso não autorizado para usuários não autenticados";
        }
        Response r = Response.status(Response.Status.UNAUTHORIZED).entity("{\"mensagem\":\"" + mensagem + "\"}").build();
        requestContext.abortWith(r);
    }

    public static class Authorizer implements SecurityContext {

        Set<String> roles;
        String username;
        boolean isSecure;

        public Authorizer(Set<String> roles, final String username,
                boolean isSecure) {
            this.roles = roles;
            this.username = username;
            this.isSecure = isSecure;
        }

        public Set<String> getRoles() {
            return roles;
        }

        @Override
        public Principal getUserPrincipal() {
            return new User(username);
        }

        @Override
        public boolean isUserInRole(String role) {
            return roles.contains(role);
        }

        @Override
        public boolean isSecure() {
            return isSecure;
        }

        @Override
        public String getAuthenticationScheme() {
            return "JWT";
        }
    }

    @PreMatching
    public static class User implements Principal {

        String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    public static String encodeAuthorize(String user, String role) {
        String s = user + ":" + role;
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

    public static Authorizer decodeAuthorize(String data, boolean isSecure) {
        String decode = new String(Base64.getDecoder().decode(data));
        String[] dec = decode.split(":");
        Set<String> roles = new HashSet<>();
        roles.add(dec[1]);
        Authorizer a = new Authorizer(roles, dec[0], isSecure);
        return a;
    }
}
