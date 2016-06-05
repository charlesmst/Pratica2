package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

import br.com.empresa.rh.model.view.Ficha;
import br.com.empresa.rh.model.view.Folha;
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
 * FuncionarioQualificacao generated by hbm2java
 */
@Entity
@Table(name = "funcionario_qualificacao", schema = "public"
)

@SequenceGenerator(name = "funcionario_qualificacao_seq", sequenceName = "funcionario_qualificacao_seq", initialValue = 1, allocationSize = 1)

public class FuncionarioQualificacao implements java.io.Serializable {

    private int id;
    private FuncionarioCargo funcionarioCargo;
    @JsonView({Folha.FuncionarioFicha.class})
    private Qualificacao qualificacao;
    @JsonView({Folha.FuncionarioFicha.class})

    private String certificado;
    @JsonView({Folha.FuncionarioFicha.class})

    private Date validade;

    public FuncionarioQualificacao() {
    }

    @Id
    @GeneratedValue(generator = "funcionario_qualificacao_seq", strategy = GenerationType.SEQUENCE)

    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_cargo_id", nullable = false)
    public FuncionarioCargo getFuncionarioCargo() {
        return this.funcionarioCargo;
    }

    public void setFuncionarioCargo(FuncionarioCargo funcionarioCargo) {
        this.funcionarioCargo = funcionarioCargo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qualificacao_id", nullable = false)
    public Qualificacao getQualificacao() {
        return this.qualificacao;
    }

    public void setQualificacao(Qualificacao qualificacao) {
        this.qualificacao = qualificacao;
    }

    @Column(name = "certificado", length = 100)
    public String getCertificado() {
        return this.certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "validade", length = 13)
    public Date getValidade() {
        return this.validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

}
