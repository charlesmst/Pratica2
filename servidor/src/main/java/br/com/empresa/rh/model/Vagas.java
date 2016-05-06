package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Vagas generated by hbm2java
 */
@Entity
@SequenceGenerator(name = "vagas_seq", sequenceName = "vagas_seq", initialValue = 1, allocationSize = 1)
@Table(name="vagas"
    ,schema="public"
)
public class Vagas  implements java.io.Serializable {


     private int id;
     private NecessidadePessoa necessidadePessoa;
     private PlanoAvaliacao planoAvaliacao;
     private boolean sigiloso;
     private String descricao;
     private String perfil;
     private int tipo;
     private Date dataInicio;
     private Date dataFim;
     private Boolean finalizado;
     private Set<Competencia> competencias = new HashSet<Competencia>(0);
     private Set<Candidato> candidatos = new HashSet<Candidato>(0);

    public Vagas() {
    }

	
    public Vagas(int id, NecessidadePessoa necessidadePessoa, PlanoAvaliacao planoAvaliacao, boolean sigiloso, String descricao, String perfil, char tipo, Date dataInicio) {
        this.id = id;
        this.necessidadePessoa = necessidadePessoa;
        this.planoAvaliacao = planoAvaliacao;
        this.sigiloso = sigiloso;
        this.descricao = descricao;
        this.perfil = perfil;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
    }
    public Vagas(int id, NecessidadePessoa necessidadePessoa, PlanoAvaliacao planoAvaliacao, boolean sigiloso, String descricao, String perfil, char tipo, Date dataInicio, Date dataFim, Boolean finalizado, Set<Competencia> competencias, Set<Candidato> candidatos) {
       this.id = id;
       this.necessidadePessoa = necessidadePessoa;
       this.planoAvaliacao = planoAvaliacao;
       this.sigiloso = sigiloso;
       this.descricao = descricao;
       this.perfil = perfil;
       this.tipo = tipo;
       this.dataInicio = dataInicio;
       this.dataFim = dataFim;
       this.finalizado = finalizado;
       this.competencias = competencias;
       this.candidatos = candidatos;
    }
   
     @Id 
     @GeneratedValue(generator = "vagas_seq", strategy = GenerationType.SEQUENCE)
    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="necessidade_pessoa_id", nullable=true)
    public NecessidadePessoa getNecessidadePessoa() {
        return this.necessidadePessoa;
    }
    
    public void setNecessidadePessoa(NecessidadePessoa necessidadePessoa) {
        this.necessidadePessoa = necessidadePessoa;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="plano_avaliacao_id", nullable=true)
    public PlanoAvaliacao getPlanoAvaliacao() {
        return this.planoAvaliacao;
    }
    
    public void setPlanoAvaliacao(PlanoAvaliacao planoAvaliacao) {
        this.planoAvaliacao = planoAvaliacao;
    }

    
    @Column(name="sigiloso", nullable=false)
    public boolean isSigiloso() {
        return this.sigiloso;
    }
    
    public void setSigiloso(boolean sigiloso) {
        this.sigiloso = sigiloso;
    }

    
    @Column(name="descricao", nullable=false)
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    @Column(name="perfil", nullable=false)
    public String getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    
    @Column(name="tipo", nullable=false)
    public int getTipo() {
        return this.tipo;
    }
    
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="data_inicio", nullable=false, length=13)
    public Date getDataInicio() {
        return this.dataInicio;
    }
    
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="data_fim", length=13)
    public Date getDataFim() {
        return this.dataFim;
    }
    
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    
    @Column(name="finalizado")
    public Boolean getFinalizado() {
        return this.finalizado;
    }
    
    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="vagas_has_competencia", schema="public", joinColumns = { 
        @JoinColumn(name="vagas_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="competencia_id", nullable=false, updatable=false) })
    public Set<Competencia> getCompetencias() {
        return this.competencias;
    }
    
    public void setCompetencias(Set<Competencia> competencias) {
        this.competencias = competencias;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="vagas")
    public Set<Candidato> getCandidatos() {
        return this.candidatos;
    }
    
    public void setCandidatos(Set<Candidato> candidatos) {
        this.candidatos = candidatos;
    }




}


