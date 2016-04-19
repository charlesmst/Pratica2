package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Unidade generated by hbm2java
 */
@Entity
@Table(name="unidade"
    ,schema="public"
)
public class Unidade  implements java.io.Serializable {


     private int id;
     private Empresa empresa;
     private String nome;
     private Set<FuncionarioCargo> funcionarioCargos = new HashSet<FuncionarioCargo>(0);

    public Unidade() {
    }

	
    public Unidade(int id, Empresa empresa) {
        this.id = id;
        this.empresa = empresa;
    }
    public Unidade(int id, Empresa empresa, String nome, Set<FuncionarioCargo> funcionarioCargos) {
       this.id = id;
       this.empresa = empresa;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="empresa_id", nullable=false)
    public Empresa getEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    
    @Column(name="nome", length=100)
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="unidade")
    public Set<FuncionarioCargo> getFuncionarioCargos() {
        return this.funcionarioCargos;
    }
    
    public void setFuncionarioCargos(Set<FuncionarioCargo> funcionarioCargos) {
        this.funcionarioCargos = funcionarioCargos;
    }




}


