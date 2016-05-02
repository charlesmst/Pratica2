/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.util.ApiException;

/**
 *
 * @author charles
 */
public enum TipoCalculo {
    mes(1), ferias(2), complementar(3);
    private int numero;

    private TipoCalculo(int numero) {
        this.numero = numero;
    }
    
    public int getNumero() {
        return numero;
    }
    public static TipoCalculo parse(int num){
        if(TipoCalculo.values().length < (num -1)){
            throw new ApiException("Tipo de cálculo não encontrado", null);
        }
        return TipoCalculo.values()[num -1];
    }
    
}
