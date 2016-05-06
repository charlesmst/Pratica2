package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

import br.com.empresa.rh.model.view.Folha;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FuncionarioCargo generated by hbm2java
 */
@Entity
@Table(name = "funcionario_cargo", schema = "public"
)
public class FuncionarioCargo implements java.io.Serializable {

    @JsonView(Folha.Funcionario.class)
    private int id;
    @JsonView(Folha.CargosFuncionario.class)

    private Cargo cargo;
    private DemissaoTipo demissaoTipo;
    @JsonView(Folha.Funcionario.class)
    private Funcionario funcionario;
    private Sindicato sindicato;
    private Unidade unidade;
    private Date dataEntrada;
    private Date dataSaida;
    private boolean ativo;
    private Set<FuncionarioCargoHasAdvertenciaTipo> funcionarioCargoHasAdvertenciaTipos = new HashSet<FuncionarioCargoHasAdvertenciaTipo>(0);
    private Set<EventoMensal> eventoMensals = new HashSet<EventoMensal>(0);
    private Set<EventoFuncionario> eventoFuncionarios = new HashSet<EventoFuncionario>(0);
    private Set<FolhaCalculada> folhaCalculadas = new HashSet<FolhaCalculada>(0);
    private Set<FuncionarioAcidente> funcionarioAcidentes = new HashSet<FuncionarioAcidente>(0);
    private Set<FuncionarioCargoHasMotivoFalta> funcionarioCargoHasMotivoFaltas = new HashSet<FuncionarioCargoHasMotivoFalta>(0);
    private Set<FuncionarioFaixa> funcionarioFaixas = new HashSet<FuncionarioFaixa>(0);
    private Set<FuncionarioQualificacao> funcionarioQualificacaos = new HashSet<FuncionarioQualificacao>(0);
    private Set<SindicatoHasFuncionarioCargo> sindicatoHasFuncionarioCargos = new HashSet<SindicatoHasFuncionarioCargo>(0);
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

    public FuncionarioCargo(int id, Cargo cargo, DemissaoTipo demissaoTipo, Funcionario funcionario, Sindicato sindicato, Unidade unidade, Date dataEntrada, Date dataSaida, boolean ativo, Set<FuncionarioCargoHasAdvertenciaTipo> funcionarioCargoHasAdvertenciaTipos, Set<EventoMensal> eventoMensals, Set<EventoFuncionario> eventoFuncionarios, Set<FolhaCalculada> folhaCalculadas, Set<FuncionarioAcidente> funcionarioAcidentes, Set<FuncionarioCargoHasMotivoFalta> funcionarioCargoHasMotivoFaltas, Set<FuncionarioFaixa> funcionarioFaixas, Set<FuncionarioQualificacao> funcionarioQualificacaos, Set<SindicatoHasFuncionarioCargo> sindicatoHasFuncionarioCargos, Set<Ferias> feriases) {
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
        this.eventoMensals = eventoMensals;
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
    @JoinColumn(name = "sindicato_id", nullable = false)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo")
    public Set<FuncionarioCargoHasAdvertenciaTipo> getFuncionarioCargoHasAdvertenciaTipos() {
        return this.funcionarioCargoHasAdvertenciaTipos;
    }

    public void setFuncionarioCargoHasAdvertenciaTipos(Set<FuncionarioCargoHasAdvertenciaTipo> funcionarioCargoHasAdvertenciaTipos) {
        this.funcionarioCargoHasAdvertenciaTipos = funcionarioCargoHasAdvertenciaTipos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo")
    public Set<EventoMensal> getEventoMensals() {
        return this.eventoMensals;
    }

    public void setEventoMensals(Set<EventoMensal> eventoMensals) {
        this.eventoMensals = eventoMensals;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo")
    public Set<FuncionarioAcidente> getFuncionarioAcidentes() {
        return this.funcionarioAcidentes;
    }

    public void setFuncionarioAcidentes(Set<FuncionarioAcidente> funcionarioAcidentes) {
        this.funcionarioAcidentes = funcionarioAcidentes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo")
    public Set<FuncionarioCargoHasMotivoFalta> getFuncionarioCargoHasMotivoFaltas() {
        return this.funcionarioCargoHasMotivoFaltas;
    }

    public void setFuncionarioCargoHasMotivoFaltas(Set<FuncionarioCargoHasMotivoFalta> funcionarioCargoHasMotivoFaltas) {
        this.funcionarioCargoHasMotivoFaltas = funcionarioCargoHasMotivoFaltas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo")
    public Set<FuncionarioFaixa> getFuncionarioFaixas() {
        return this.funcionarioFaixas;
    }

    public void setFuncionarioFaixas(Set<FuncionarioFaixa> funcionarioFaixas) {
        this.funcionarioFaixas = funcionarioFaixas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionarioCargo")
    public Set<Ferias> getFeriases() {
        return this.feriases;
    }

    public void setFeriases(Set<Ferias> feriases) {
        this.feriases = feriases;
    }

}
