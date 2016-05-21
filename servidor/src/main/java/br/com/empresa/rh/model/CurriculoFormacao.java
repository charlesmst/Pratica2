/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.model;

import br.com.empresa.rh.model.view.Recrutamento;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gustavo.michels
 */
@Entity
@Table(name = "curriculo_formacao", schema = "public"
)
public class CurriculoFormacao implements Serializable {

    @JsonView({Recrutamento.CurriculoFormacao.class})
    private int id;
    private Pessoa pessoa;
    @JsonView({Recrutamento.CurriculoFormacao.class})
    private Escolaridade escolaridade;
    @JsonView({Recrutamento.CurriculoFormacao.class})
    private String curso;
    @JsonView({Recrutamento.CurriculoFormacao.class})
    private String instituicao;
    @JsonView({Recrutamento.CurriculoFormacao.class})
    private int situacao;
    @JsonView({Recrutamento.CurriculoFormacao.class})
    private Date dataInicial;
    @JsonView({Recrutamento.CurriculoFormacao.class})
    private Date dataFinal;
    @JsonView({Recrutamento.CurriculoFormacao.class})
    private Integer cargaHoraria;

    public CurriculoFormacao() {
    }

    public CurriculoFormacao(int id, Pessoa pessoa, Escolaridade escolaridade, String curso, String instituicao, int situacao) {
        this.id = id;
        this.pessoa = pessoa;
        this.escolaridade = escolaridade;
        this.curso = curso;
        this.instituicao = instituicao;
        this.situacao = situacao;
    }

    public CurriculoFormacao(int id, Pessoa pessoa, Escolaridade escolaridade, String curso, String instituicao, int situacao, Date dataInicial, Date dataFinal, Integer cargaHoraria) {
        this.id = id;
        this.pessoa = pessoa;
        this.escolaridade = escolaridade;
        this.curso = curso;
        this.instituicao = instituicao;
        this.situacao = situacao;
        this.dataInicial = dataInicial;
        this.cargaHoraria = cargaHoraria;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escolaridade_id", nullable = false)
    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    @Column(name = "curso", nullable = false, length = 100)
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Column(name = "instituicao", nullable = false, length = 200)
    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    @Column(name = "situacao", nullable = false)
    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicial", nullable = false)
    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_final", nullable = false)
    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    @Column(name = "carga_horaria")
    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

}
