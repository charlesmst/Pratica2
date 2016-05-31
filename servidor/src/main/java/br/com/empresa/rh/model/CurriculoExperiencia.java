package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

import br.com.empresa.rh.model.view.Recrutamento;
import com.fasterxml.jackson.annotation.JsonView;
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
 * CurriculoExperiencia generated by hbm2java
 */
@Entity
@SequenceGenerator(name = "curriculo_experiencia_seq", sequenceName = "curriculo_experiencia_seq", initialValue = 1, allocationSize = 1)
@Table(name = "curriculo_experiencia", schema = "public"
)
public class CurriculoExperiencia implements java.io.Serializable {

    @JsonView({Recrutamento.CurriculoExperiencia.class, Recrutamento.Curriculo.class})
    private int id;
    private Pessoa pessoa;
    @JsonView({Recrutamento.CurriculoExperiencia.class, Recrutamento.Curriculo.class})
    private String cargo;
    @JsonView({Recrutamento.CurriculoExperiencia.class, Recrutamento.Curriculo.class})
    private Date dataInicial;
    @JsonView({Recrutamento.CurriculoExperiencia.class, Recrutamento.Curriculo.class})
    private Date dataFinal;
    @JsonView({Recrutamento.CurriculoExperiencia.class, Recrutamento.Curriculo.class})
    private String pessoaReferencia;
    @JsonView({Recrutamento.CurriculoExperiencia.class, Recrutamento.Curriculo.class})
    private String pessoaContato;
    @JsonView({Recrutamento.CurriculoExperiencia.class, Recrutamento.Curriculo.class})
    private String empresa;
    @JsonView({Recrutamento.CurriculoExperiencia.class, Recrutamento.Curriculo.class})
    private String descricao;

    public CurriculoExperiencia() {
    }

    public CurriculoExperiencia(int id, Pessoa pessoa, Date dataInicial) {
        this.id = id;
        this.pessoa = pessoa;
        this.dataInicial = dataInicial;
    }

    public CurriculoExperiencia(int id, Pessoa pessoa, String cargo, Date dataInicial, Date dataFinal, String pessoaReferencia, String pessoaContato, String empresa, String descricao) {
        this.id = id;
        this.pessoa = pessoa;
        this.cargo = cargo;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.pessoaReferencia = pessoaReferencia;
        this.pessoaContato = pessoaContato;
        this.empresa = empresa;
        this.descricao = descricao;
    }

    @Id
    @GeneratedValue(generator = "curriculo_experiencia_seq", strategy = GenerationType.SEQUENCE)

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

    
    @Column(name = "cargo", nullable = false, length = 200)
    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicial", nullable = false)
    public Date getDataInicial() {
        return this.dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_final", nullable = false)
    public Date getDataFinal() {
        return this.dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    @Column(name = "pessoa_referencia", length = 200)
    public String getPessoaReferencia() {
        return this.pessoaReferencia;
    }

    public void setPessoaReferencia(String pessoaReferencia) {
        this.pessoaReferencia = pessoaReferencia;
    }

    @Column(name = "pessoa_contato", length = 200)
    public String getPessoaContato() {
        return this.pessoaContato;
    }

    public void setPessoaContato(String pessoaContato) {
        this.pessoaContato = pessoaContato;
    }

    @Column(name = "empresa", length = 500)
    public String getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    @Column(name = "descricao")
    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
