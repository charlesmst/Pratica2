/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.util;

/**
 *
 * @author charles
 */
public class SecurityApiException extends ApiException{
    public SecurityApiException(){
        super("Usuário não tem permissão de acesso ao recurso");
    }
}
