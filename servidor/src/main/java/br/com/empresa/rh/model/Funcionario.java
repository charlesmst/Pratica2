package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

import br.com.empresa.rh.model.view.Folha;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
@Table(name = "funcionario", schema = "public"
)
public class Funcionario implements java.io.Serializable {

    @JsonView({Folha.Funcionario.class, Folha.FuncionarioFicha.class})
    private Integer  pessoaId;
    @JsonView({Folha.FuncionarioFicha.class})
    private Banco banco;
    @JsonView({Folha.Funcionario.class})
    private Pessoa pessoa;
    @JsonView({Folha.FuncionarioFicha.class})
    private VinculoEmpregaticio vinculoEmpregaticio;
    @JsonView({Folha.FuncionarioFicha.class})
    private String conta;
    @JsonView({Folha.FuncionarioFicha.class})
    private int agencia;
    @JsonView({Folha.FuncionarioFicha.class})
    private TipoSanguineo tipoSanguineo;
    @JsonView({Folha.FuncionarioFicha.class})
    private Set<Dependente> dependentes = new HashSet<Dependente>(0);
    @JsonView({Folha.FuncionarioFicha.class})
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

    public Funcionario(Banco banco, Pessoa pessoa, VinculoEmpregaticio vinculoEmpregaticio, String conta, int agencia, Set<Dependente> dependentes, Set<FuncionarioCargo> funcionarioCargos) {
        this.banco = banco;
        this.pessoa = pessoa;
        this.vinculoEmpregaticio = vinculoEmpregaticio;
        this.conta = conta;
        this.agencia = agencia;
        this.dependentes = dependentes;
        this.funcionarioCargos = funcionarioCargos;
    }

    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "pessoa"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "pessoa_id", unique = true, nullable = false)
    public Integer  getPessoaId() {
        return this.pessoaId;
    }

    public void setPessoaId(Integer  pessoaId) {
        this.pessoaId = pessoaId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banco_id", nullable = false)
    public Banco getBanco() {
        return this.banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vinculo_empregaticio_id", nullable = false)
    public VinculoEmpregaticio getVinculoEmpregaticio() {
        return this.vinculoEmpregaticio;
    }

    public void setVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio) {
        this.vinculoEmpregaticio = vinculoEmpregaticio;
    }

    @Column(name = "conta", nullable = false, length = 50)
    public String getConta() {
        return this.conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    @Column(name = "agencia", nullable = false)
    public int getAgencia() {
        return this.agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionario",cascade = CascadeType.ALL,orphanRemoval = true)
    public Set<Dependente> getDependentes() {
        return this.dependentes;
    }

    public void setDependentes(Set<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionario",cascade = CascadeType.ALL)
    public Set<FuncionarioCargo> getFuncionarioCargos() {
        return this.funcionarioCargos;
    }

    public void setFuncionarioCargos(Set<FuncionarioCargo> funcionarioCargos) {
        this.funcionarioCargos = funcionarioCargos;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_sanguineo_id", nullable = true)
    public TipoSanguineo getTipoSanguineo() {
        return this.tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

}
