package br.com.empresa.rh.model;
// Generated 17/04/2016 21:10:50 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FolhaCalculadaEvento generated by hbm2java
 */
@Entity
@Table(name="folha_calculada_evento"
    ,schema="public"
)
public class FolhaCalculadaEvento  implements java.io.Serializable {


     private int id;
     private Evento evento;
     private FolhaCalculada folhaCalculada;
     private BigDecimal valor;
     private boolean visivel;
     private char tipo;

    public FolhaCalculadaEvento() {
    }

    public FolhaCalculadaEvento(int id, Evento evento, FolhaCalculada folhaCalculada, BigDecimal valor, boolean visivel, char tipo) {
       this.id = id;
       this.evento = evento;
       this.folhaCalculada = folhaCalculada;
       this.valor = valor;
       this.visivel = visivel;
       this.tipo = tipo;
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
    @JoinColumn(name="folha_calculada_id", nullable=false)
    public FolhaCalculada getFolhaCalculada() {
        return this.folhaCalculada;
    }
    
    public void setFolhaCalculada(FolhaCalculada folhaCalculada) {
        this.folhaCalculada = folhaCalculada;
    }

    
    @Column(name="valor", nullable=false, precision=10)
    public BigDecimal getValor() {
        return this.valor;
    }
    
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    
    @Column(name="visivel", nullable=false)
    public boolean isVisivel() {
        return this.visivel;
    }
    
    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    
    @Column(name="tipo", nullable=false, length=1)
    public char getTipo() {
        return this.tipo;
    }
    
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }




}

