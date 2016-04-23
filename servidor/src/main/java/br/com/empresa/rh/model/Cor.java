package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Cor generated by hbm2java
 */
@Entity
@SequenceGenerator(name = "cor_seq", sequenceName = "cor_seq", initialValue = 1, allocationSize = 1)
@Table(name = "cor", schema = "public"
)
public class Cor implements java.io.Serializable {

    private int id;
    private String nome;
    private Set<Pessoa> pessoas = new HashSet<Pessoa>(0);

    public Cor() {
    }

    public Cor(int id) {
        this.id = id;
    }

    public Cor(int id, String nome, Set<Pessoa> pessoas) {
        this.id = id;
        this.nome = nome;
        this.pessoas = pessoas;
    }

    @Id
    @GeneratedValue(generator = "cor_seq", strategy = GenerationType.SEQUENCE)

    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nome", length = 100)
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cor")
    public Set<Pessoa> getPessoas() {
        return this.pessoas;
    }

    public void setPessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

}
