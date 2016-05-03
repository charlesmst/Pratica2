/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author charles
 */
public class EventoDependenciaId implements java.io.Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(insertable = false, updatable = false)

    private Evento evento;
//    @JoinColumn(insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Evento eventoDependencia;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.evento);
        hash = 17 * hash + Objects.hashCode(this.eventoDependencia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EventoDependenciaId other = (EventoDependenciaId) obj;
        if (!Objects.equals(this.evento, other.evento)) {
            return false;
        }
        if (!Objects.equals(this.eventoDependencia, other.eventoDependencia)) {
            return false;
        }
        return true;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Evento getEventoDependencia() {
        return eventoDependencia;
    }

    public void setEventoDependencia(Evento eventoDependencia) {
        this.eventoDependencia = eventoDependencia;
    }

}
