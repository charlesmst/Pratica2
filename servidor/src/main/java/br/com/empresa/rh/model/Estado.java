package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Estado generated by hbm2java
 */
@Entity
@Table(name="estado"
    ,schema="public"
)
public class Estado  implements java.io.Serializable {


     private String uf;
     private String nome;
     private Set<Cidade> cidades = new HashSet<Cidade>(0);

    public Estado() {
    }

	
    public Estado(String uf, String nome) {
        this.uf = uf;
        this.nome = nome;
    }
    public Estado(String uf, String nome, Set<Cidade> cidades) {
       this.uf = uf;
       this.nome = nome;
       this.cidades = cidades;
    }
   
     @Id 

    
    @Column(name="uf", unique=true, nullable=false, length=2)
    public String getUf() {
        return this.uf;
    }
    
    public void setUf(String uf) {
        this.uf = uf;
    }

    
    @Column(name="nome", nullable=false, length=500)
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="estado")
    public Set<Cidade> getCidades() {
        return this.cidades;
    }
    
    public void setCidades(Set<Cidade> cidades) {
        this.cidades = cidades;
    }




}


