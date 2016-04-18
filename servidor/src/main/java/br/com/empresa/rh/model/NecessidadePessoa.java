package br.com.empresa.rh.model;
// Generated 17/04/2016 21:10:50 by Hibernate Tools 4.3.1


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
 * NecessidadePessoa generated by hbm2java
 */
@Entity
@Table(name="necessidade_pessoa"
    ,schema="public"
)
public class NecessidadePessoa  implements java.io.Serializable {


     private int id;
     private Cargo cargo;
     private Usuario usuario;
     private String descricao;
     private String perfil;
     private char situacao;
     private Date dataRequisicao;
     private int quantidade;
     private Set<Vagas> vagases = new HashSet<Vagas>(0);

    public NecessidadePessoa() {
    }

	
    public NecessidadePessoa(int id, Cargo cargo, Usuario usuario, String descricao, String perfil, char situacao, Date dataRequisicao, int quantidade) {
        this.id = id;
        this.cargo = cargo;
        this.usuario = usuario;
        this.descricao = descricao;
        this.perfil = perfil;
        this.situacao = situacao;
        this.dataRequisicao = dataRequisicao;
        this.quantidade = quantidade;
    }
    public NecessidadePessoa(int id, Cargo cargo, Usuario usuario, String descricao, String perfil, char situacao, Date dataRequisicao, int quantidade, Set<Vagas> vagases) {
       this.id = id;
       this.cargo = cargo;
       this.usuario = usuario;
       this.descricao = descricao;
       this.perfil = perfil;
       this.situacao = situacao;
       this.dataRequisicao = dataRequisicao;
       this.quantidade = quantidade;
       this.vagases = vagases;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cargo_id", nullable=false)
    public Cargo getCargo() {
        return this.cargo;
    }
    
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuario_pessoa_id", nullable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="descricao", nullable=false, length=500)
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    @Column(name="perfil", nullable=false)
    public String getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    
    @Column(name="situacao", nullable=false, length=1)
    public char getSituacao() {
        return this.situacao;
    }
    
    public void setSituacao(char situacao) {
        this.situacao = situacao;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="data_requisicao", nullable=false, length=13)
    public Date getDataRequisicao() {
        return this.dataRequisicao;
    }
    
    public void setDataRequisicao(Date dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

    
    @Column(name="quantidade", nullable=false)
    public int getQuantidade() {
        return this.quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="necessidadePessoa")
    public Set<Vagas> getVagases() {
        return this.vagases;
    }
    
    public void setVagases(Set<Vagas> vagases) {
        this.vagases = vagases;
    }




}

