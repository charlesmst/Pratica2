/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.filter.secure;

/**
 *
 * @author charles
 */
public class NivelAcesso {

    public static final String CANDIDATO = "1";
    public static final String FUNCIONARIO = "2";
    public static final String GESTOR = "3";
    public static final String RH = "4";
    public static final String ADMIN = "5";
    public static final String NENHUM = "0";
    
    public static int getNivel(String nivel){
        return Integer.parseInt(nivel);
    }

}
