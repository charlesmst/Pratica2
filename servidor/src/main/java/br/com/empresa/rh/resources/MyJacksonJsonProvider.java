/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.resources;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
 
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
 
/**
 * Jackson JSON processor could be controlled via providing a custom Jackson ObjectMapper instance. 
 * This could be handy if you need to redefine the default Jackson behavior and to fine-tune how 
 * your JSON data structures look like (copied from Jersey web site). * 
 * @see https://jersey.java.net/documentation/latest/media.html#d0e4799
 */
 
@Provider
//@Produces({MediaType.APPLICATION_JSON})
//@Consumes(MediaType.APPLICATION_JSON)
//@Singleton
public class MyJacksonJsonProvider implements ContextResolver<ObjectMapper> {
    
    private final ObjectMapper mapper = new ObjectMapper();

    public MyJacksonJsonProvider() {
        /*
         * Register JodaModule to handle Joda DateTime Objects.
         * https://github.com/FasterXML/jackson-datatype-jsr310
         */
//        mapper.registerModule(new JSR310Module());;
        mapper.setSerializationInclusion(Include.NON_EMPTY);
//        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    }

    @Override
    public ObjectMapper getContext(Class<?> arg0) {
        return mapper;
    }
}