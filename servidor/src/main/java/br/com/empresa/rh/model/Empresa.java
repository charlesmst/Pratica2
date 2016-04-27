package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

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
 * Empresa generated by hbm2java
 */
@Entity
@Table(name = "empresa", schema = "public"
)
public class Empresa implements java.io.Serializable {

    private int id;
    private Cidade cidade;
    private String nome;
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;
    private Date dataFundacao;
    private String endereco;
    private String bairro;
    private String telefone;
    private String logo;
    private String email;
    private boolean ativo = true;
    private Set<Unidade> unidades = new HashSet<Unidade>(0);

    public Empresa() {
    }

    public Empresa(int id, Cidade cidade, String nome, String cnpj, String nomeFantasia, String razaoSocial, Date dataFundacao, String endereco, String bairro, String telefone, String email) {
        this.id = id;
        this.cidade = cidade;
        this.nome = nome;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.dataFundacao = dataFundacao;
        this.endereco = endereco;
        this.bairro = bairro;
        this.telefone = telefone;
        this.email = email;
    }

    public Empresa(int id, Cidade cidade, String nome, String cnpj, String nomeFantasia, String razaoSocial, Date dataFundacao, String endereco, String bairro, String telefone, String logo, String email, Set<Unidade> unidades) {
        this.id = id;
        this.cidade = cidade;
        this.nome = nome;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.dataFundacao = dataFundacao;
        this.endereco = endereco;
        this.bairro = bairro;
        this.telefone = telefone;
        this.logo = logo;
        this.email = email;
        this.unidades = unidades;
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
    @JoinColumn(name = "cidade_id", nullable = false)
    public Cidade getCidade() {
        return this.cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Column(name = "nome", nullable = false, length = 100)
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "cnpj", nullable = false, length = 14)
    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Column(name = "nome_fantasia", nullable = false, length = 100)
    public String getNomeFantasia() {
        return this.nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @Column(name = "razao_social", nullable = false, length = 100)
    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_fundacao", nullable = false, length = 13)
    public Date getDataFundacao() {
        return this.dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    @Column(name = "endereco", nullable = false, length = 100)
    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Column(name = "bairro", nullable = false, length = 100)
    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Column(name = "telefone", nullable = false, length = 15)
    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Column(name = "logo", length = 100)
    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
    public Set<Unidade> getUnidades() {
        return this.unidades;
    }

    public void setUnidades(Set<Unidade> unidades) {
        this.unidades = unidades;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
