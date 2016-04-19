package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FaixaSalarial generated by hbm2java
 */
@Entity
@Table(name="faixa_salarial"
    ,schema="public"
)
public class FaixaSalarial  implements java.io.Serializable {


     private int id;
     private String nome;
     private Set<FaixaSalarialValor> faixaSalarialValors = new HashSet<FaixaSalarialValor>(0);
     private Set<CargoNivel> cargoNivels = new HashSet<CargoNivel>(0);
     private Set<FuncionarioFaixa> funcionarioFaixas = new HashSet<FuncionarioFaixa>(0);

    public FaixaSalarial() {
    }

	
    public FaixaSalarial(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public FaixaSalarial(int id, String nome, Set<FaixaSalarialValor> faixaSalarialValors, Set<CargoNivel> cargoNivels, Set<FuncionarioFaixa> funcionarioFaixas) {
       this.id = id;
       this.nome = nome;
       this.faixaSalarialValors = faixaSalarialValors;
       this.cargoNivels = cargoNivels;
       this.funcionarioFaixas = funcionarioFaixas;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="nome", nullable=false, length=200)
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="faixaSalarial")
    public Set<FaixaSalarialValor> getFaixaSalarialValors() {
        return this.faixaSalarialValors;
    }
    
    public void setFaixaSalarialValors(Set<FaixaSalarialValor> faixaSalarialValors) {
        this.faixaSalarialValors = faixaSalarialValors;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="cargo_nivel_has_faixa_salarial", schema="public", joinColumns = { 
        @JoinColumn(name="faixa_salarial_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="cargo_nivel_id", nullable=false, updatable=false) })
    public Set<CargoNivel> getCargoNivels() {
        return this.cargoNivels;
    }
    
    public void setCargoNivels(Set<CargoNivel> cargoNivels) {
        this.cargoNivels = cargoNivels;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="faixaSalarial")
    public Set<FuncionarioFaixa> getFuncionarioFaixas() {
        return this.funcionarioFaixas;
    }
    
    public void setFuncionarioFaixas(Set<FuncionarioFaixa> funcionarioFaixas) {
        this.funcionarioFaixas = funcionarioFaixas;
    }




}


