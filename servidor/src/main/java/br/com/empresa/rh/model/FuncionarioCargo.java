package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

import br.com.empresa.rh.model.view.Folha;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FuncionarioCargo generated by hbm2java
 */
@Entity
@Table(name = "funcionario_cargo", schema = "public"
)
@SequenceGenerator(name = "funcionario_cargo_seq", sequenceName = "funcionario_cargo_seq", initialValue = 1, allocationSize = 1)

public class FuncionarioCargo implements java.io.Serializable {

    @JsonView({Folha.Funcionario.class, Folha.FuncionarioFicha.class})
    private int id;
    @JsonView({Folha.CargosFuncionarioComCargo.class, Folha.FuncionarioFicha.class, Folha.FolhaCalculada.class})

    private Cargo cargo;
    @JsonView({Folha.FuncionarioFicha.class})
    private DemissaoTipo demissaoTipo;
    @JsonView(Folha.Funcionario.class)
    private Funcionario funcionario;
    @JsonView({Folha.FuncionarioFicha.class})
    private Sindicato sindicato;
    @JsonView({Folha.FuncionarioFicha.class})

    private Unidade unidade;
    @JsonView({Folha.FuncionarioFicha.class})

    private Date dataEntrada;
    @JsonView({Folha.FuncionarioFicha.class})

    private Date dataSaida;
    @JsonView({Folha.FuncionarioFicha.class})

    private boolean ativo;
    @JsonView({Folha.FuncionarioFicha.class})

    private Set<FuncionarioCargoHasAdvertenciaTipo> funcionarioCargoHasAdvertenciaTipos = new HashSet<FuncionarioCargoHasAdvertenciaTipo>(0);

    private Set<EventoFuncionario> eventoFuncionarios = new HashSet<EventoFuncionario>(0);
    private Set<FolhaCalculada> folhaCalculadas = new HashSet<FolhaCalculada>(0);
    @JsonView({Folha.FuncionarioFicha.class})

    private Set<FuncionarioAcidente> funcionarioAcidentes = new HashSet<FuncionarioAcidente>(0);
    @JsonView({Folha.FuncionarioFicha.class})

    private Set<FuncionarioCargoHasMotivoFalta> funcionarioCargoHasMotivoFaltas = new HashSet<FuncionarioCargoHasMotivoFalta>(0);
    @JsonView({Folha.FuncionarioFicha.class})

    private Set<FuncionarioFaixa> funcionarioFaixas = new HashSet<FuncionarioFaixa>(0);
    @JsonView({Folha.FuncionarioFicha.class})

    private Set<FuncionarioQualificacao> funcionarioQualificacaos = new HashSet<FuncionarioQualificacao>(0);
    @JsonView({Folha.FuncionarioFicha.class})

    private Set<SindicatoHasFuncionarioCargo> sindicatoHasFuncionarioCargos = new HashSet<SindicatoHasFuncionarioCargo>(0);
    @JsonView({Folha.FuncionarioFicha.class})

    private Set<Ferias> feriases = new HashSet<Ferias>(0);

    public FuncionarioCargo() {
    }

    public FuncionarioCargo(int id, Cargo cargo, Funcionario funcionario, Sindicato sindicato, Unidade unidade, Date dataEntrada, boolean ativo) {
        this.id = id;
        this.cargo = cargo;
        this.funcionario = funcionario;
        this.sindicato = sindicato;
        this.unidade = unidade;
        this.dataEntrada = dataEntrada;
        this.ativo = ativo;
    }

    public FuncionarioCargo(int id, Cargo cargo, DemissaoTipo demissaoTipo, Funcionario funcionario, Sindicato sindicato, Unidade unidade, Date dataEntrada, Date dataSaida, boolean ativo, Set<FuncionarioCargoHasAdvertenciaTipo> funcionarioCargoHasAdvertenciaTipos, Set<EventoFuncionario> eventoFuncionarios, Set<FolhaCalculada> folhaCalculadas, Set<FuncionarioAcidente> funcionarioAcidentes, Set<FuncionarioCargoHasMotivoFalta> funcionarioCargoHasMotivoFaltas, Set<FuncionarioFaixa> funcionarioFaixas, Set<FuncionarioQualificacao> funcionarioQualificacaos, Set<SindicatoHasFuncionarioCargo> sindicatoHasFuncionarioCargos, Set<Ferias> feriases) {
        this.id = id;
        this.cargo = cargo;
        this.demissaoTipo = demissaoTipo;
        this.funcionario = funcionario;
        this.sindicato = sindicato;
        this.unidade = unidade;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.ativo = ativo;
        this.funcionarioCargoHasAdvertenciaTipos = funcionarioCargoHasAdvertenciaTipos;
        this.eventoFuncionarios = eventoFuncionarios;
        this.folhaCalculadas = folhaCalculadas;
        this.funcionarioAcidentes = funcionarioAcidentes;
        this.funcionarioCargoHasMotivoFaltas = funcionarioCargoHasMotivoFaltas;
        this.funcionarioFaixas = funcionarioFaixas;
        this.funcionarioQualificacaos = funcionarioQualificacaos;
        this.sindicatoHasFuncionarioCargos = sindicatoHasFuncionarioCargos;
        this.feriases = feriases;
    }

    @Id
    @GeneratedValue(generator = "funcionario_cargo_seq", strategy = GenerationType.SEQUENCE)

    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id", nullable = false)
    public Cargo getCargo() {
        return this.cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demissao_tipo_id")
    public DemissaoTipo getDemissaoTipo() {
        return this.demissaoTipo;
    }

    public void setDemissaoTipo(DemissaoTipo demissaoTipo) {
        this.demissaoTipo = demissaoTipo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_pessoa_id", nullable = false)
    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sindicato_id", nullable = true)
    public Sindicato getSindicato() {
        return this.sindicato;
    }

    public void setSindicato(Sindicato sindicato) {
        this.sindicato = sindicato;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_id", nullable = false)
    public Unidade getUnidade() {
        return this.unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_entrada", nullable = false, length = 13)
    public Date getDataEntrada() {
        return this.dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_saida", length = 13)
    public Date getDataSaida() {
        return this.dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    @Column(name = "ativo", nullable = false)
    public boolean isAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<FuncionarioCargoHasAdvertenciaTipo> getFuncionarioCargoHasAdvertenciaTipos() {
        return this.funcionarioCargoHasAdvertenciaTipos;
    }

    public void setFuncionarioCargoHasAdvertenciaTipos(Set<FuncionarioCargoHasAdvertenciaTipo> funcionarioCargoHasAdvertenciaTipos) {
        this.funcionarioCargoHasAdvertenciaTipos = funcionarioCargoHasAdvertenciaTipos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo")
    public Set<EventoFuncionario> getEventoFuncionarios() {
        return this.eventoFuncionarios;
    }

    public void setEventoFuncionarios(Set<EventoFuncionario> eventoFuncionarios) {
        this.eventoFuncionarios = eventoFuncionarios;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo")
    public Set<FolhaCalculada> getFolhaCalculadas() {
        return this.folhaCalculadas;
    }

    public void setFolhaCalculadas(Set<FolhaCalculada> folhaCalculadas) {
        this.folhaCalculadas = folhaCalculadas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<FuncionarioAcidente> getFuncionarioAcidentes() {
        return this.funcionarioAcidentes;
    }

    public void setFuncionarioAcidentes(Set<FuncionarioAcidente> funcionarioAcidentes) {
        this.funcionarioAcidentes = funcionarioAcidentes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<FuncionarioCargoHasMotivoFalta> getFuncionarioCargoHasMotivoFaltas() {
        return this.funcionarioCargoHasMotivoFaltas;
    }

    public void setFuncionarioCargoHasMotivoFaltas(Set<FuncionarioCargoHasMotivoFalta> funcionarioCargoHasMotivoFaltas) {
        this.funcionarioCargoHasMotivoFaltas = funcionarioCargoHasMotivoFaltas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<FuncionarioFaixa> getFuncionarioFaixas() {
        return this.funcionarioFaixas;
    }

    public void setFuncionarioFaixas(Set<FuncionarioFaixa> funcionarioFaixas) {
        this.funcionarioFaixas = funcionarioFaixas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<FuncionarioQualificacao> getFuncionarioQualificacaos() {
        return this.funcionarioQualificacaos;
    }

    public void setFuncionarioQualificacaos(Set<FuncionarioQualificacao> funcionarioQualificacaos) {
        this.funcionarioQualificacaos = funcionarioQualificacaos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo")
    public Set<SindicatoHasFuncionarioCargo> getSindicatoHasFuncionarioCargos() {
        return this.sindicatoHasFuncionarioCargos;
    }

    public void setSindicatoHasFuncionarioCargos(Set<SindicatoHasFuncionarioCargo> sindicatoHasFuncionarioCargos) {
        this.sindicatoHasFuncionarioCargos = sindicatoHasFuncionarioCargos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Ferias> getFeriases() {
        return this.feriases;
    }

    public void setFeriases(Set<Ferias> feriases) {
        this.feriases = feriases;
    }

}
