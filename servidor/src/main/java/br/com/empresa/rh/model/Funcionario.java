package br.com.empresa.rh.model;
// Generated 17/04/2016 21:10:50 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Funcionario generated by hbm2java
 */
@Entity
@Table(name="funcionario"
    ,schema="public"
)
public class Funcionario  implements java.io.Serializable {


     private int pessoaId;
     private Banco banco;
     private Pessoa pessoa;
     private VinculoEmpregaticio vinculoEmpregaticio;
     private String conta;
     private int agencia;
     private Set<QualificacoesHasFuncionario> qualificacoesHasFuncionarios = new HashSet<QualificacoesHasFuncionario>(0);
     private Set<Dependente> dependentes = new HashSet<Dependente>(0);
     private Set<FuncionarioCargo> funcionarioCargos = new HashSet<FuncionarioCargo>(0);

    public Funcionario() {
    }

	
    public Funcionario(Banco banco, Pessoa pessoa, VinculoEmpregaticio vinculoEmpregaticio, String conta, int agencia) {
        this.banco = banco;
        this.pessoa = pessoa;
        this.vinculoEmpregaticio = vinculoEmpregaticio;
        this.conta = conta;
        this.agencia = agencia;
    }
    public Funcionario(Banco banco, Pessoa pessoa, VinculoEmpregaticio vinculoEmpregaticio, String conta, int agencia, Set<QualificacoesHasFuncionario> qualificacoesHasFuncionarios, Set<Dependente> dependentes, Set<FuncionarioCargo> funcionarioCargos) {
       this.banco = banco;
       this.pessoa = pessoa;
       this.vinculoEmpregaticio = vinculoEmpregaticio;
       this.conta = conta;
       this.agencia = agencia;
       this.qualificacoesHasFuncionarios = qualificacoesHasFuncionarios;
       this.dependentes = dependentes;
       this.funcionarioCargos = funcionarioCargos;
    }
   
     @GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="pessoa"))@Id @GeneratedValue(generator="generator")

    
    @Column(name="pessoa_id", unique=true, nullable=false)
    public int getPessoaId() {
        return this.pessoaId;
    }
    
    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="banco_id", nullable=false)
    public Banco getBanco() {
        return this.banco;
    }
    
    public void setBanco(Banco banco) {
        this.banco = banco;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="vinculo_empregaticio_id", nullable=false)
    public VinculoEmpregaticio getVinculoEmpregaticio() {
        return this.vinculoEmpregaticio;
    }
    
    public void setVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio) {
        this.vinculoEmpregaticio = vinculoEmpregaticio;
    }

    
    @Column(name="conta", nullable=false, length=50)
    public String getConta() {
        return this.conta;
    }
    
    public void setConta(String conta) {
        this.conta = conta;
    }

    
    @Column(name="agencia", nullable=false)
    public int getAgencia() {
        return this.agencia;
    }
    
    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="funcionario")
    public Set<QualificacoesHasFuncionario> getQualificacoesHasFuncionarios() {
        return this.qualificacoesHasFuncionarios;
    }
    
    public void setQualificacoesHasFuncionarios(Set<QualificacoesHasFuncionario> qualificacoesHasFuncionarios) {
        this.qualificacoesHasFuncionarios = qualificacoesHasFuncionarios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="funcionario")
    public Set<Dependente> getDependentes() {
        return this.dependentes;
    }
    
    public void setDependentes(Set<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="funcionario")
    public Set<FuncionarioCargo> getFuncionarioCargos() {
        return this.funcionarioCargos;
    }
    
    public void setFuncionarioCargos(Set<FuncionarioCargo> funcionarioCargos) {
        this.funcionarioCargos = funcionarioCargos;
    }




}


