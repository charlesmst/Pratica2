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
public class EventoNaoCalculavel implements IEvento {

    private Evento evento;

    public EventoNaoCalculavel(Evento evento) {
        this.evento = evento;
    }

    @Override
    public Evento getEvento() {
        return evento;
    }

    @Override
    public double getReferencia() {
        throw new UnsupportedOperationException("Método não suportado.");
    }

    @Override
    public double getValorCalculado() {
        throw new UnsupportedOperationException("Método não suportado.");
    }

    @Override
    public boolean isAplicavel() {
        return false;
    }

    @Override
    public void calcula(Folha folha,Parametros parametros, Consulta consultas, EventoCollection eventos, Console console, Utilitarios utilitarios, Stack<IEvento> stack) {
        throw new UnsupportedOperationException("Método não suportado.");
    }

    @Override
    public boolean isCalculado() {
        return false;
    }

}
