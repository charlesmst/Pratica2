package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

import br.com.empresa.rh.model.view.Folha;
import com.fasterxml.jackson.annotation.JsonView;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Evento generated by hbm2java
 */
@Entity
@Table(name = "evento", schema = "public"
)
@SequenceGenerator(name = "evento_seq", sequenceName = "evento_seq", initialValue = 1, allocationSize = 1)

public class Evento implements java.io.Serializable {

    @JsonView(Folha.EventoVisualizacao.class)
    private int id;
    @JsonView(Folha.EventoVisualizacao.class)
    private String nome;
    private boolean visivelFolha;
    private int tipo;
    private String script;
    private boolean padrao;
    private boolean ativo = true;

    private Set<EventoFuncionario> eventoFuncionarios = new HashSet<EventoFuncionario>(0);
    private Set<EventoMensal> eventoMensals = new HashSet<EventoMensal>(0);
    private Set<CargoHasEvento> cargoHasEventos = new HashSet<CargoHasEvento>(0);
    private Set<FolhaCalculadaEvento> folhaCalculadaEventos = new HashSet<FolhaCalculadaEvento>(0);

    private Set<EventoDependencia> eventoDependencias = new HashSet<EventoDependencia>(0);

    public Evento() {
    }

    public Evento(int id, String nome, boolean visivelFolha, int tipo, String script, boolean padrao) {
        this.id = id;
        this.nome = nome;
        this.visivelFolha = visivelFolha;
        this.tipo = tipo;
        this.script = script;
        this.padrao = padrao;
    }

    public Evento(int id, String nome, boolean visivelFolha, int tipo, String script, boolean padrao, Set<EventoFuncionario> eventoFuncionarios, Set<EventoMensal> eventoMensals, Set<CargoHasEvento> cargoHasEventos, Set<FolhaCalculadaEvento> folhaCalculadaEventos) {
        this.id = id;
        this.nome = nome;
        this.visivelFolha = visivelFolha;
        this.tipo = tipo;
        this.script = script;
        this.padrao = padrao;
        this.eventoFuncionarios = eventoFuncionarios;
        this.eventoMensals = eventoMensals;
        this.cargoHasEventos = cargoHasEventos;
        this.folhaCalculadaEventos = folhaCalculadaEventos;
    }

    @Id

    @GeneratedValue(generator = "evento_seq", strategy = GenerationType.SEQUENCE)

    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nome", nullable = false, length = 100)
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "visivel_folha", nullable = false)
    public boolean isVisivelFolha() {
        return this.visivelFolha;
    }

    public void setVisivelFolha(boolean visivelFolha) {
        this.visivelFolha = visivelFolha;
    }

    @Column(name = "tipo", nullable = false)
    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Column(name = "script", nullable = false)
    public String getScript() {
        return this.script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    @Column(name = "padrao", nullable = false, length = 1)
    public boolean getPadrao() {
        return this.padrao;
    }

    public void setPadrao(boolean padrao) {
        this.padrao = padrao;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
    public Set<EventoFuncionario> getEventoFuncionarios() {
        return this.eventoFuncionarios;
    }

    public void setEventoFuncionarios(Set<EventoFuncionario> eventoFuncionarios) {
        this.eventoFuncionarios = eventoFuncionarios;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
    public Set<EventoMensal> getEventoMensals() {
        return this.eventoMensals;
    }

    public void setEventoMensals(Set<EventoMensal> eventoMensals) {
        this.eventoMensals = eventoMensals;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
    public Set<CargoHasEvento> getCargoHasEventos() {
        return this.cargoHasEventos;
    }

    public void setCargoHasEventos(Set<CargoHasEvento> cargoHasEventos) {
        this.cargoHasEventos = cargoHasEventos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
    public Set<FolhaCalculadaEvento> getFolhaCalculadaEventos() {
        return this.folhaCalculadaEventos;
    }

    public void setFolhaCalculadaEventos(Set<FolhaCalculadaEvento> folhaCalculadaEventos) {
        this.folhaCalculadaEventos = folhaCalculadaEventos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

//    @OneToMany(fetch = FetchType.LAZY
//            , orphanRemoval = true, cascade = { CascadeType.MERGE,CascadeType.PERSIST}
//    )
//    @JoinColumn(name = "evento_id")
//    @JoinTable(name = "evento_dependencia", joinColumns = {
//        @JoinColumn(name = "evento_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
//        @JoinColumn(name = "evento_dependencia_id", referencedColumnName = "id", nullable = false)})
//    @OneToMany
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<EventoDependencia> getEventoDependencias() {
        return this.eventoDependencias;
    }

    public void setEventoDependencias(Set<EventoDependencia> eventoDependencias) {
        this.eventoDependencias = eventoDependencias;
    }
}
