package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

import br.com.empresa.rh.model.view.Recrutamento;
import com.fasterxml.jackson.annotation.JsonView;
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
import javax.persistence.Transient;

/**
 * Candidato generated by hbm2java
 */
@Entity
@SequenceGenerator(name = "candidato_seq", sequenceName = "candidato_seq", initialValue = 1, allocationSize = 1)
@Table(name = "candidato", schema = "public"
)
public class Candidato implements java.io.Serializable {

    @JsonView({Recrutamento.Vagas.class, Recrutamento.Entrevista.class})
    private int id;
    @JsonView({Recrutamento.Vagas.class, Recrutamento.Entrevista.class})
    private Pessoa pessoa;
    @JsonView({Recrutamento.Entrevista.class})
    private Vagas vagas;
    @JsonView({Recrutamento.Vagas.class, Recrutamento.Entrevista.class})
    private Date dataInscricao;
    @JsonView({Recrutamento.Entrevista.class})
    private Set<Resposta> respostas = new HashSet<Resposta>(0);
    @JsonView({Recrutamento.Vagas.class, Recrutamento.Entrevista.class})
    private Set<Competencia> competencias = new HashSet<Competencia>(0);
    @JsonView({Recrutamento.Vagas.class, Recrutamento.Entrevista.class})
    private Set<Entrevista> entrevistas = new HashSet<Entrevista>(0);
    @JsonView({Recrutamento.VagasView.class})
    private boolean isCandidato;

    public Candidato() {
    }

    public Candidato(int id, Pessoa pessoa, Vagas vagas, Date dataInscricao) {
        this.id = id;
        this.pessoa = pessoa;
        this.vagas = vagas;
        this.dataInscricao = dataInscricao;
    }

    public Candidato(int id, Pessoa pessoa, Vagas vagas, Date dataInscricao, Set<Resposta> respostas, Set<Competencia> competencias, Set<Entrevista> entrevistas) {
        this.id = id;
        this.pessoa = pessoa;
        this.vagas = vagas;
        this.dataInscricao = dataInscricao;
        this.respostas = respostas;
        this.competencias = competencias;
        this.entrevistas = entrevistas;
    }

    @Id
    @GeneratedValue(generator = "candidato_seq", strategy = GenerationType.SEQUENCE)

    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    @Transient
    public boolean getIsCandidato() {
        return this.isCandidato;
    }
    
    public void setIsCandidato(boolean isCandidato) {
        this.isCandidato = isCandidato;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vagas_id", nullable = false)
    public Vagas getVagas() {
        return this.vagas;
    }

    public void setVagas(Vagas vagas) {
        this.vagas = vagas;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inscricao", nullable = false, length = 13)
    public Date getDataInscricao() {
        return this.dataInscricao;
    }

    public void setDataInscricao(Date dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidato")
    public Set<Resposta> getRespostas() {
        return this.respostas;
    }

    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "competencia_has_candidato", schema = "public", joinColumns = {
        @JoinColumn(name = "candidato_id", nullable = false, updatable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "competencia_id", nullable = false, updatable = false)})
    public Set<Competencia> getCompetencias() {
        return this.competencias;
    }

    public void setCompetencias(Set<Competencia> competencias) {
        this.competencias = competencias;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidato")
    public Set<Entrevista> getEntrevistas() {
        return this.entrevistas;
    }

    public void setEntrevistas(Set<Entrevista> entrevistas) {
        this.entrevistas = entrevistas;
    }

}
