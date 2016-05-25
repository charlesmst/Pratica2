package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1


import br.com.empresa.rh.model.view.Recrutamento;
import com.fasterxml.jackson.annotation.JsonView;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Questao generated by hbm2java
 */
@Entity
@SequenceGenerator(name = "questao_seq", sequenceName = "questao_seq", initialValue = 1, allocationSize = 1)
@Table(name="questao"
    ,schema="public"
)
public class Questao  implements java.io.Serializable {


     @JsonView({Recrutamento.Avaliacao.class})
     private int id;
     @JsonView({Recrutamento.Avaliacao.class})
     private String descricao;
     @JsonView({Recrutamento.Avaliacao.class})
     private Character tipo;
     @JsonView({Recrutamento.Avaliacao.class})
     private boolean visivelCandidato;
     private Set<Resposta> respostas = new HashSet<Resposta>(0);
     private Set<QuestaoOpcao> questaoOpcaos = new HashSet<QuestaoOpcao>(0);
     private Set<PlanoAvaliacao> planoAvaliacaos = new HashSet<PlanoAvaliacao>(0);

    public Questao() {
    }

	
    public Questao(int id, String descricao, boolean visivelCandidato) {
        this.id = id;
        this.descricao = descricao;
        this.visivelCandidato = visivelCandidato;
    }
    public Questao(int id, String descricao, Character tipo, boolean visivelCandidato, Set<Resposta> respostas, Set<QuestaoOpcao> questaoOpcaos, Set<PlanoAvaliacao> planoAvaliacaos) {
       this.id = id;
       this.descricao = descricao;
       this.tipo = tipo;
       this.visivelCandidato = visivelCandidato;
       this.respostas = respostas;
       this.questaoOpcaos = questaoOpcaos;
       this.planoAvaliacaos = planoAvaliacaos;
    }
   
     @Id 
     @GeneratedValue(generator = "questao_seq", strategy = GenerationType.SEQUENCE)
    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="descricao", nullable=false, length=2000)
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    @Column(name="tipo", length=1)
    public Character getTipo() {
        return this.tipo;
    }
    
    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    
    @Column(name="visivel_candidato", nullable=false)
    public boolean isVisivelCandidato() {
        return this.visivelCandidato;
    }
    
    public void setVisivelCandidato(boolean visivelCandidato) {
        this.visivelCandidato = visivelCandidato;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="questao")
    public Set<Resposta> getRespostas() {
        return this.respostas;
    }
    
    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="questao")
    public Set<QuestaoOpcao> getQuestaoOpcaos() {
        return this.questaoOpcaos;
    }
    
    public void setQuestaoOpcaos(Set<QuestaoOpcao> questaoOpcaos) {
        this.questaoOpcaos = questaoOpcaos;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="plano_avaliacao_has_questao", schema="public", joinColumns = { 
        @JoinColumn(name="questao_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="plano_avaliacao_id", nullable=false, updatable=false) })
    public Set<PlanoAvaliacao> getPlanoAvaliacaos() {
        return this.planoAvaliacaos;
    }
    
    public void setPlanoAvaliacaos(Set<PlanoAvaliacao> planoAvaliacaos) {
        this.planoAvaliacaos = planoAvaliacaos;
    }




}


