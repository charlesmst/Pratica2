/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.resources;

import org.springframework.stereotype.Component;

@Component
@javax.ws.rs.Path("exampleWithInjection")
public class StringResource {

    @javax.ws.rs.GET
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
    public String get() {
        return "hsaudasd";
    }


}