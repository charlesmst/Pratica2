/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.Evento;

/**
 *
 * @author charles
 */
public interface IEvento {

    Evento getEvento();

    int getReferencia();

    double getValorCalculado();

    boolean isAplicavel();
    
    void calcula(Parametros parametros);
}
