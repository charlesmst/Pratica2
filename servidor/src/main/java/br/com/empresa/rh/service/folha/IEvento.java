/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.Evento;
import java.util.Stack;

/**
 *
 * @author charles
 */
public interface IEvento {

    Evento getEvento();

    double getReferencia();

    double getValorCalculado();

    boolean isAplicavel();
    
    void calcula(Folha folha,Parametros parametros,Consulta consultas,EventoCollection eventos,Console console,Utilitarios utilitarios,Stack<IEvento> stack);
    
    boolean isCalculado();
}
