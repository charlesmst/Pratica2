package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FaixaSalarialValorId generated by hbm2java
 */
@Embeddable
public class FaixaSalarialValorId  implements java.io.Serializable {


     private Date dataInicio;
     private int faixaSalarialId;

    public FaixaSalarialValorId() {
    }

    public FaixaSalarialValorId(Date dataInicio, int faixaSalarialId) {
       this.dataInicio = dataInicio;
       this.faixaSalarialId = faixaSalarialId;
    }
   


    @Column(name="data_inicio", nullable=false, length=13)
    public Date getDataInicio() {
        return this.dataInicio;
    }
    
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }


    @Column(name="faixa_salarial_id", nullable=false)
    public int getFaixaSalarialId() {
        return this.faixaSalarialId;
    }
    
    public void setFaixaSalarialId(int faixaSalarialId) {
        this.faixaSalarialId = faixaSalarialId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FaixaSalarialValorId) ) return false;
		 FaixaSalarialValorId castOther = ( FaixaSalarialValorId ) other; 
         
		 return ( (this.getDataInicio()==castOther.getDataInicio()) || ( this.getDataInicio()!=null && castOther.getDataInicio()!=null && this.getDataInicio().equals(castOther.getDataInicio()) ) )
 && (this.getFaixaSalarialId()==castOther.getFaixaSalarialId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDataInicio() == null ? 0 : this.getDataInicio().hashCode() );
         result = 37 * result + this.getFaixaSalarialId();
         return result;
   }   


}


