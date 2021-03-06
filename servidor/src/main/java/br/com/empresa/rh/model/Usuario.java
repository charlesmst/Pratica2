package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

import br.com.empresa.rh.model.view.Folha;
import br.com.empresa.rh.model.view.Recrutamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario", schema = "public"
)
public class Usuario implements java.io.Serializable {

    @JsonView({Recrutamento.Usuario.class})
    private int pessoaId;
    @JsonView({Recrutamento.Usuario.class})
    private Pessoa pessoa;
    @JsonView({Recrutamento.Usuario.class, Folha.FuncionarioFicha.class})
    private String usuario;
    @JsonView({Recrutamento.Usuario.class})
    private String senha;
    @JsonView({Folha.FuncionarioFicha.class})
    private Integer nivel;
    private Set<NecessidadePessoa> necessidadePessoas = new HashSet<NecessidadePessoa>(0);
    private Set<Auditoria> auditorias = new HashSet<Auditoria>(0);

    public Usuario() {
    }

    public Usuario(Pessoa pessoa, String usuario, String senha) {
        this.pessoa = pessoa;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario(Pessoa pessoa, String usuario, String senha, Integer nivel, Set<NecessidadePessoa> necessidadePessoas, Set<Auditoria> auditorias) {
        this.pessoa = pessoa;
        this.usuario = usuario;
        this.senha = senha;
        this.nivel = nivel;
        this.necessidadePessoas = necessidadePessoas;
        this.auditorias = auditorias;
    }

    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "pessoa"))
    @Id
    @GeneratedValue(generator = "generator")

    @Column(name = "pessoa_id", unique = true, nullable = false)
    public int getPessoaId() {
        return this.pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Column(name = "usuario", nullable = false, length = 100)
    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Column(name = "senha", nullable = false, length = 100)
    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Column(name = "nivel")
    public Integer getNivel() {
        return this.nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    public Set<NecessidadePessoa> getNecessidadePessoas() {
        return this.necessidadePessoas;
    }

    public void setNecessidadePessoas(Set<NecessidadePessoa> necessidadePessoas) {
        this.necessidadePessoas = necessidadePessoas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    public Set<Auditoria> getAuditorias() {
        return this.auditorias;
    }

    public void setAuditorias(Set<Auditoria> auditorias) {
        this.auditorias = auditorias;
    }

}
