package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * EventoMensal generated by hbm2java
 */
@Entity
@Table(name="evento_mensal"
    ,schema="public"
)
public class EventoMensal  implements java.io.Serializable {


     private int id;
     private Evento evento;
     private FuncionarioCargo funcionarioCargo;
     private int mes;
     private int ano;

    public EventoMensal() {
    }

    public EventoMensal(int id, Evento evento, FuncionarioCargo funcionarioCargo, int mes, int ano) {
       this.id = id;
       this.evento = evento;
       this.funcionarioCargo = funcionarioCargo;
       this.mes = mes;
       this.ano = ano;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="evento_id", nullable=false)
    public Evento getEvento() {
        return this.evento;
    }
    
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="funcionario_cargo_id", nullable=false)
    public FuncionarioCargo getFuncionarioCargo() {
        return this.funcionarioCargo;
    }
    
    public void setFuncionarioCargo(FuncionarioCargo funcionarioCargo) {
        this.funcionarioCargo = funcionarioCargo;
    }

    
    @Column(name="mes", nullable=false)
    public int getMes() {
        return this.mes;
    }
    
    public void setMes(int mes) {
        this.mes = mes;
    }

    
    @Column(name="ano", nullable=false)
    public int getAno() {
        return this.ano;
    }
    
    public void setAno(int ano) {
        this.ano = ano;
    }




}


