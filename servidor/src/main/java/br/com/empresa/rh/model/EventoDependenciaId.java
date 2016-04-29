/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author charles
 */
@Embeddable
public class EventoDependenciaId  implements java.io.Serializable {


     private int eventoDependenciaId;
     private int eventoId;

    public EventoDependenciaId() {
    }

    public EventoDependenciaId(int eventoDependenciaId, int eventoId) {
       this.eventoDependenciaId = eventoDependenciaId;
       this.eventoId = eventoId;
    }
   


    @Column(name="evento_dependencia_id", unique=true, nullable=false)
    public int getEventoDependenciaId() {
        return this.eventoDependenciaId;
    }
    
    public void setEventoDependenciaId(int eventoDependenciaId) {
        this.eventoDependenciaId = eventoDependenciaId;
    }


    @Column(name="evento_id", unique=true, nullable=false)
    public int getEventoId() {
        return this.eventoId;
    }
    
    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EventoDependenciaId) ) return false;
		 EventoDependenciaId castOther = ( EventoDependenciaId ) other; 
         
		 return (this.getEventoDependenciaId()==castOther.getEventoDependenciaId())
 && (this.getEventoId()==castOther.getEventoId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getEventoDependenciaId();
         result = 37 * result + this.getEventoId();
         return result;
   }   


}

