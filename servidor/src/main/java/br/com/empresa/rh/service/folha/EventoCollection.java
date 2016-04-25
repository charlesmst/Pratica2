/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.Evento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author charles
 */
public class EventoCollection implements Serializable {

    private List<IEvento> eventos;

    public List<IEvento> getEventos() {
        return eventos;
    }

    public EventoCollection(List<Evento> eventos) {
        if (this.eventos == null) {
            this.eventos = new ArrayList<>();
        }
        for (Evento evento : eventos) {
            this.eventos.add(new EventoScript(evento));
        }
    }
    
    public void addAll(List<Evento> eventos){
        for (Evento evento : eventos) {
            this.eventos.add(new EventoScript(evento));
        }
    }
    public EventoCollection() {
            this.eventos = new ArrayList<>();
    }

    public double getTotalVencimentos() {
        return getBases() + getProventos() + getBeneficios();
    }

    public double getDescontos() {
        double valor = 0d;
        for (IEvento evento : eventos) {
            if (evento.getEvento().getTipo() == EventoTipo.DESCONTO) {
                valor += evento.getValorCalculado();
            }
        }
        return valor;
    }

    public double getProventos() {
        double valor = 0d;
        for (IEvento evento : eventos) {
            if (evento.getEvento().getTipo() == EventoTipo.PROVENTO) {
                valor += evento.getValorCalculado();
            }
        }
        return valor;
    }

    public double getBeneficios() {
        double valor = 0d;
        for (IEvento evento : eventos) {
            if (evento.getEvento().getTipo() == EventoTipo.BENEFICIO) {
                valor += evento.getValorCalculado();
            }
        }
        return valor;
    }

    public double getBases() {
        double valor = 0d;
        for (IEvento evento : eventos) {
            if (evento.getEvento().getTipo() == EventoTipo.BASE) {
                valor += evento.getValorCalculado();
            }
        }
        return valor;
    }
}
