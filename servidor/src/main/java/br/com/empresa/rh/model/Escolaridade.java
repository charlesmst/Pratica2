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
 * Escolaridade generated by hbm2java
 */
@Entity
@Table(name="escolaridade"
    ,schema="public"
)
public class Escolaridade  implements java.io.Serializable {


     private int id;
     private String nome;
     private Set<Pessoa> pessoas = new HashSet<Pessoa>(0);

    public Escolaridade() {
    }

	
    public Escolaridade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public Escolaridade(int id, String nome, Set<Pessoa> pessoas) {
       this.id = id;
       this.nome = nome;
       this.pessoas = pessoas;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="escolaridade")
    public Set<Pessoa> getPessoas() {
        return this.pessoas;
    }
    
    public void setPessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }




}


