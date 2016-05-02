/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.util;

import br.com.empresa.rh.model.response.MensagemResponse;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;

/**
 *
 * @author charles
 */
public class ApiException  extends RuntimeException implements ApiResponseException{

    @Override
    public Response toResponse() {
        MensagemResponse m = new MensagemResponse(false, getMessage());
        return Response.status(500).entity(m).build();                
    }
    
    public ApiException(String mensagem, Exception ex){
        super(mensagem);
    }
    
    public ApiException(String mensagem){
        super(mensagem);
    }
    public ApiException(String mensagem, Exception ex, Logger logger){
        super(mensagem);
        if(ex != null){
//            logger.error(ex.getMessage());
        }
    }
    
    public ApiException(String mensagem, Exception ex, Class classLogger){
        super(mensagem);
         if(ex != null){
//             LogManager.getLogger(classLogger).error(ex.getMessage());
        }
    }
}