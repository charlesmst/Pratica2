/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.Evento;
import br.com.empresa.rh.model.response.EventoFolha;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
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
    public void add(Evento evento){
        this.eventos.add(new EventoScript(evento));
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
    public void removerDuplicados(){
        //Ordena corretamente
        
        eventos.sort(new Comparator<IEvento>() {
            @Override
            public int compare(IEvento f1, IEvento f2) {
                return Integer.compare(f1.getEvento().getId(),f2.getEvento().getId());
            }
        });

        int lastId = -1;
        for (int i = eventos.size() - 1; i >=0; i--) {
            if(eventos.get(i).getEvento().getId() != lastId){
                lastId = eventos.get(i).getEvento().getId();
            }else{
                eventos.remove(i);
            }
        }
    }
}
