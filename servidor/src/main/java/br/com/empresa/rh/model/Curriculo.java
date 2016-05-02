package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1


import br.com.empresa.rh.model.view.Recrutamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Curriculo generated by hbm2java
 */
@Entity
@Table(name="curriculo"
    ,schema="public"
)
public class Curriculo  implements java.io.Serializable {

     private int pessoaId;
     private Pessoa pessoa;
     private String objetivo;

    public Curriculo() {
    }

    public Curriculo(Pessoa pessoa, String objetivo) {
       this.pessoa = pessoa;
       this.objetivo = objetivo;
    }
   
     @GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="pessoa"))@Id @GeneratedValue(generator="generator")

    
    @Column(name="pessoa_id", unique=true, nullable=false)
    public int getPessoaId() {
        return this.pessoaId;
    }
    
    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    
    @Column(name="objetivo", nullable=false)
    public String getObjetivo() {
        return this.objetivo;
    }
    
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }




}


