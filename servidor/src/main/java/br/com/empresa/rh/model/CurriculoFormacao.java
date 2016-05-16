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
    private String titulacao;
    @JsonView({Recrutamento.CurriculoFormacao.class})
    private String curso;
    @JsonView({Recrutamento.CurriculoFormacao.class})
    private String Instituicao;
    @JsonView({Recrutamento.CurriculoFormacao.class})
    private int situacao;
    @JsonView({Recrutamento.CurriculoFormacao.class})
    private Date anoConclusao;
    @JsonView({Recrutamento.CurriculoFormacao.class})
    private Integer cargaHoraria;
    @JsonView({Recrutamento.CurriculoFormacao.class})
    private String descricao;

    public CurriculoFormacao() {
    }

    public CurriculoFormacao(int id, Pessoa pessoa, String titulacao, String curso, String Instituicao, int situacao) {
        this.id = id;
        this.pessoa = pessoa;
        this.titulacao = titulacao;
        this.curso = curso;
        this.Instituicao = Instituicao;
        this.situacao = situacao;
    }

    public CurriculoFormacao(int id, Pessoa pessoa, String titulacao, String curso, String Instituicao, int situacao, Date anoConclusao, Integer cargaHoraria, String descricao) {
        this.id = id;
        this.pessoa = pessoa;
        this.titulacao = titulacao;
        this.curso = curso;
        this.Instituicao = Instituicao;
        this.situacao = situacao;
        this.anoConclusao = anoConclusao;
        this.cargaHoraria = cargaHoraria;
        this.descricao = descricao;
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

    @Column(name = "titulacao", nullable = false, length = 50)
    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
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
        return Instituicao;
    }

    public void setInstituicao(String Instituicao) {
        this.Instituicao = Instituicao;
    }

    @Column(name = "situacao", nullable = false)
    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ano_conclusao")
    public Date getAnoConclusao() {
        return anoConclusao;
    }

    public void setAnoConclusao(Date anoConclusao) {
        this.anoConclusao = anoConclusao;
    }

    @Column(name = "carga_horaria")
    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Column(name = "descricao")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
