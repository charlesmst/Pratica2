package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

import br.com.empresa.rh.model.view.Folha;
import br.com.empresa.rh.model.view.Recrutamento;
import com.fasterxml.jackson.annotation.JsonView;
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

/**
 * Cargo generated by hbm2java
 */
@Entity
@Table(name = "cargo", schema = "public"
)
public class Cargo implements java.io.Serializable {

    @JsonView({Folha.CargosFuncionario.class, Recrutamento.NecessidadePessoa.class, Folha.FuncionarioFicha.class})
    private int id;
     @JsonView({ Folha.FuncionarioFicha.class})
    private Cbo cbo;
    @JsonView({Folha.CargosFuncionario.class, Recrutamento.NecessidadePessoa.class, Folha.FuncionarioFicha.class})
    private String nome;
     @JsonView({ Folha.FuncionarioFicha.class})
    private String atribuicoes;
      @JsonView({ Folha.FuncionarioFicha.class})
    private boolean ativo = true;
    private Set<NecessidadePessoa> necessidadePessoas = new HashSet<NecessidadePessoa>(0);
    private Set<CargoHasEvento> cargoHasEventos = new HashSet<CargoHasEvento>(0);
    @JsonView(Folha.CargosFuncionario.class)
    private Set<FuncionarioCargo> funcionarioCargos = new HashSet<FuncionarioCargo>(0);
    private Set<CargoNivel> cargoNivels = new HashSet<CargoNivel>(0);

    public Cargo() {
    }

    public Cargo(int id, Cbo cbo, String nome) {
        this.id = id;
        this.cbo = cbo;
        this.nome = nome;
    }

    public Cargo(int id, Cbo cbo, String nome, String atribuicoes, Set<NecessidadePessoa> necessidadePessoas, Set<CargoHasEvento> cargoHasEventos, Set<FuncionarioCargo> funcionarioCargos, Set<CargoNivel> cargoNivels) {
        this.id = id;
        this.cbo = cbo;
        this.nome = nome;
        this.atribuicoes = atribuicoes;
        this.necessidadePessoas = necessidadePessoas;
        this.cargoHasEventos = cargoHasEventos;
        this.funcionarioCargos = funcionarioCargos;
        this.cargoNivels = cargoNivels;
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
    @JoinColumn(name = "cbo_id", nullable = false)
    public Cbo getCbo() {
        return this.cbo;
    }

    public void setCbo(Cbo cbo) {
        this.cbo = cbo;
    }

    @Column(name = "nome", nullable = false, length = 200)
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "atribuicoes", length = 2000)
    public String getAtribuicoes() {
        return this.atribuicoes;
    }

    public void setAtribuicoes(String atribuicoes) {
        this.atribuicoes = atribuicoes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cargo")
    public Set<NecessidadePessoa> getNecessidadePessoas() {
        return this.necessidadePessoas;
    }

    public void setNecessidadePessoas(Set<NecessidadePessoa> necessidadePessoas) {
        this.necessidadePessoas = necessidadePessoas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cargo")
    public Set<CargoHasEvento> getCargoHasEventos() {
        return this.cargoHasEventos;
    }

    public void setCargoHasEventos(Set<CargoHasEvento> cargoHasEventos) {
        this.cargoHasEventos = cargoHasEventos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cargo")
    public Set<FuncionarioCargo> getFuncionarioCargos() {
        return this.funcionarioCargos;
    }

    public void setFuncionarioCargos(Set<FuncionarioCargo> funcionarioCargos) {
        this.funcionarioCargos = funcionarioCargos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cargo")
    public Set<CargoNivel> getCargoNivels() {
        return this.cargoNivels;
    }

    public void setCargoNivels(Set<CargoNivel> cargoNivels) {
        this.cargoNivels = cargoNivels;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
