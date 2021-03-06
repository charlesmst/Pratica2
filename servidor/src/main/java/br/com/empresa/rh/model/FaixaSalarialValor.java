package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FaixaSalarialValor generated by hbm2java
 */
@Entity
@Table(name="faixa_salarial_valor"
    ,schema="public"
)
public class FaixaSalarialValor  implements java.io.Serializable {


     private FaixaSalarialValorId id;
     @JsonIgnore
     private FaixaSalarial faixaSalarial;
     private double valor;
     private int cargaHoraria;

    public FaixaSalarialValor() {
    }

    public FaixaSalarialValor(FaixaSalarialValorId id, FaixaSalarial faixaSalarial, double valor, int cargaHoraria) {
       this.id = id;
       this.faixaSalarial = faixaSalarial;
       this.valor = valor;
       this.cargaHoraria = cargaHoraria;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="dataInicio", column=@Column(name="data_inicio", nullable=false, length=13) ), 
        @AttributeOverride(name="faixaSalarialId", column=@Column(name="faixa_salarial_id", nullable=false) ) } )
    public FaixaSalarialValorId getId() {
        return this.id;
    }
    
    public void setId(FaixaSalarialValorId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="faixa_salarial_id", nullable=false, insertable=false, updatable=false)
    public FaixaSalarial getFaixaSalarial() {
        return this.faixaSalarial;
    }
    
    public void setFaixaSalarial(FaixaSalarial faixaSalarial) {
        this.faixaSalarial = faixaSalarial;
    }

    
    @Column(name="valor", nullable=false, precision=10)
    public double getValor() {
        return this.valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }

    
    @Column(name="carga_horaria", nullable=false)
    public int getCargaHoraria() {
        return this.cargaHoraria;
    }
    
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }




}


