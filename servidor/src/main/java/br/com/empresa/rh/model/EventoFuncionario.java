package br.com.empresa.rh.model;
// Generated 17/04/2016 21:10:50 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * EventoFuncionario generated by hbm2java
 */
@Entity
@Table(name="evento_funcionario"
    ,schema="public"
)
public class EventoFuncionario  implements java.io.Serializable {


     private int id;
     private Evento evento;
     private FuncionarioCargo funcionarioCargo;
     private Date dataInicio;
     private Date dataFim;

    public EventoFuncionario() {
    }

	
    public EventoFuncionario(int id, Evento evento, FuncionarioCargo funcionarioCargo, Date dataInicio) {
        this.id = id;
        this.evento = evento;
        this.funcionarioCargo = funcionarioCargo;
        this.dataInicio = dataInicio;
    }
    public EventoFuncionario(int id, Evento evento, FuncionarioCargo funcionarioCargo, Date dataInicio, Date dataFim) {
       this.id = id;
       this.evento = evento;
       this.funcionarioCargo = funcionarioCargo;
       this.dataInicio = dataInicio;
       this.dataFim = dataFim;
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

    @Temporal(TemporalType.DATE)
    @Column(name="data_inicio", nullable=false, length=13)
    public Date getDataInicio() {
        return this.dataInicio;
    }
    
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="data_fim", length=13)
    public Date getDataFim() {
        return this.dataFim;
    }
    
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }




}


