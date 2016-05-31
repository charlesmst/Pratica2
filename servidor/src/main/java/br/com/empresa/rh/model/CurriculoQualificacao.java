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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gustavo.michels
 */
@Entity
@SequenceGenerator(name = "curriculo_qualificacao_id_seq", sequenceName = "curriculo_qualificacao_id_seq", initialValue = 1, allocationSize = 1)
@Table(name = "curriculo_qualificacao", schema = "public"
)
public class CurriculoQualificacao implements Serializable {

    @JsonView({Recrutamento.CurriculoQualificacao.class, Recrutamento.Curriculo.class})
    private int id;
    private Pessoa pessoa;
    @JsonView({Recrutamento.CurriculoQualificacao.class, Recrutamento.Curriculo.class})
    private String nome;
    @JsonView({Recrutamento.CurriculoQualificacao.class, Recrutamento.Curriculo.class})
    private Integer cargaHoraria;
    @JsonView({Recrutamento.CurriculoQualificacao.class, Recrutamento.Curriculo.class})
    private Date dataInicial;
    @JsonView({Recrutamento.CurriculoQualificacao.class, Recrutamento.Curriculo.class})
    private Date dataFinal;
    @JsonView({Recrutamento.CurriculoQualificacao.class, Recrutamento.Curriculo.class})
    private String descricao;

    public CurriculoQualificacao() {
    }

    public CurriculoQualificacao(int id, Pessoa pessoa, String nome, String descricao) {
        this.id = id;
        this.pessoa = pessoa;
        this.nome = nome;
        this.descricao = descricao;
    }

    public CurriculoQualificacao(int id, Pessoa pessoa, String nome, Integer cargaHoraria, Date dataInicial, Date dataFinal, String descricao) {
        this.id = id;
        this.pessoa = pessoa;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.descricao = descricao;
    }

    @Id
    @GeneratedValue(generator = "curriculo_qualificacao_id_seq", strategy = GenerationType.SEQUENCE)
    
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

    @Column(name = "nome", nullable = false, length = 200)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "carga_horaria")
    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicial")
    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_final")
    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    @Column(name = "descricao")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
