package br.com.empresa.rh.model;
// Generated 17/04/2016 21:10:50 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * QualificacoesHasFuncionarioId generated by hbm2java
 */
@Embeddable
public class QualificacoesHasFuncionarioId  implements java.io.Serializable {


     private int qualificacaoId;
     private int funcionarioPessoaId;

    public QualificacoesHasFuncionarioId() {
    }

    public QualificacoesHasFuncionarioId(int qualificacaoId, int funcionarioPessoaId) {
       this.qualificacaoId = qualificacaoId;
       this.funcionarioPessoaId = funcionarioPessoaId;
    }
   


    @Column(name="qualificacao_id", nullable=false)
    public int getQualificacaoId() {
        return this.qualificacaoId;
    }
    
    public void setQualificacaoId(int qualificacaoId) {
        this.qualificacaoId = qualificacaoId;
    }


    @Column(name="funcionario_pessoa_id", nullable=false)
    public int getFuncionarioPessoaId() {
        return this.funcionarioPessoaId;
    }
    
    public void setFuncionarioPessoaId(int funcionarioPessoaId) {
        this.funcionarioPessoaId = funcionarioPessoaId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof QualificacoesHasFuncionarioId) ) return false;
		 QualificacoesHasFuncionarioId castOther = ( QualificacoesHasFuncionarioId ) other; 
         
		 return (this.getQualificacaoId()==castOther.getQualificacaoId())
 && (this.getFuncionarioPessoaId()==castOther.getFuncionarioPessoaId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getQualificacaoId();
         result = 37 * result + this.getFuncionarioPessoaId();
         return result;
   }   


}


