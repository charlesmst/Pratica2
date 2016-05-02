/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.response.MensagemResponse;
import br.com.empresa.rh.util.ApiResponseException;
import javax.ws.rs.core.Response;

/**
 *
 * @author charles
 */
public class FolhaException extends RuntimeException implements ApiResponseException{
    @Override
    public Response toResponse() {
        MensagemResponse m = new MensagemResponse(false, getMessage());
        return Response.status(500).entity(m).build();                
    }
    public FolhaException(String message, Throwable cause) {
        super(message, cause);
    }

    public FolhaException(String message) {
        super(message);
    }
    
}
