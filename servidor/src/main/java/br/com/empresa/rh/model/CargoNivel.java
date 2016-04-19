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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CargoNivel generated by hbm2java
 */
@Entity
@Table(name="cargo_nivel"
    ,schema="public"
)
public class CargoNivel  implements java.io.Serializable {


     private int id;
     private Cargo cargo;
     private String nome;
     private Integer experiencia;
     private Set<FuncionarioFaixa> funcionarioFaixas = new HashSet<FuncionarioFaixa>(0);
     private Set<Competencia> competencias = new HashSet<Competencia>(0);
     private Set<FaixaSalarial> faixaSalarials = new HashSet<FaixaSalarial>(0);

    public CargoNivel() {
    }

	
    public CargoNivel(int id, Cargo cargo, String nome) {
        this.id = id;
        this.cargo = cargo;
        this.nome = nome;
    }
    public CargoNivel(int id, Cargo cargo, String nome, Integer experiencia, Set<FuncionarioFaixa> funcionarioFaixas, Set<Competencia> competencias, Set<FaixaSalarial> faixaSalarials) {
       this.id = id;
       this.cargo = cargo;
       this.nome = nome;
       this.experiencia = experiencia;
       this.funcionarioFaixas = funcionarioFaixas;
       this.competencias = competencias;
       this.faixaSalarials = faixaSalarials;
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
    @JoinColumn(name="cargo_id", nullable=false)
    public Cargo getCargo() {
        return this.cargo;
    }
    
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    
    @Column(name="nome", nullable=false, length=200)
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    @Column(name="experiencia")
    public Integer getExperiencia() {
        return this.experiencia;
    }
    
    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cargoNivel")
    public Set<FuncionarioFaixa> getFuncionarioFaixas() {
        return this.funcionarioFaixas;
    }
    
    public void setFuncionarioFaixas(Set<FuncionarioFaixa> funcionarioFaixas) {
        this.funcionarioFaixas = funcionarioFaixas;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="competencia_has_cargo_nivel", schema="public", joinColumns = { 
        @JoinColumn(name="cargo_nivel_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="competencia_id", nullable=false, updatable=false) })
    public Set<Competencia> getCompetencias() {
        return this.competencias;
    }
    
    public void setCompetencias(Set<Competencia> competencias) {
        this.competencias = competencias;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="cargo_nivel_has_faixa_salarial", schema="public", joinColumns = { 
        @JoinColumn(name="cargo_nivel_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="faixa_salarial_id", nullable=false, updatable=false) })
    public Set<FaixaSalarial> getFaixaSalarials() {
        return this.faixaSalarials;
    }
    
    public void setFaixaSalarials(Set<FaixaSalarial> faixaSalarials) {
        this.faixaSalarials = faixaSalarials;
    }




}


