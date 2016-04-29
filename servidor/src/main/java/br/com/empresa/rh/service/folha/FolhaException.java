/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

/**
 *
 * @author charles
 */
public class FolhaException extends RuntimeException{

    public FolhaException(String message, Throwable cause) {
        super(message, cause);
    }

    public FolhaException(String message) {
        super(message);
    }
    
}
