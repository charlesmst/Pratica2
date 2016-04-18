package br.com.empresa.rh.model;
// Generated 17/04/2016 21:10:50 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * DemissaoTipo generated by hbm2java
 */
@Entity
@Table(name="demissao_tipo"
    ,schema="public"
)
public class DemissaoTipo  implements java.io.Serializable {


     private int id;
     private String nome;
     private Set<FuncionarioCargo> funcionarioCargos = new HashSet<FuncionarioCargo>(0);

    public DemissaoTipo() {
    }

	
    public DemissaoTipo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public DemissaoTipo(int id, String nome, Set<FuncionarioCargo> funcionarioCargos) {
       this.id = id;
       this.nome = nome;
       this.funcionarioCargos = funcionarioCargos;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="nome", nullable=false, length=100)
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="demissaoTipo")
    public Set<FuncionarioCargo> getFuncionarioCargos() {
        return this.funcionarioCargos;
    }
    
    public void setFuncionarioCargos(Set<FuncionarioCargo> funcionarioCargos) {
        this.funcionarioCargos = funcionarioCargos;
    }




}

