package br.com.empresa.rh.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;


//@Entity
//@SequenceGenerator(name = "menu_seq", sequenceName = "menu_seq", initialValue = 1, allocationSize = 1)
//@XmlRootElement
public class Menu implements Serializable {

    @Id
    @GeneratedValue(generator = "menu_seq", strategy = GenerationType.SEQUENCE)
    private long id;
    private String nome;
    private String icone;
    private String url;
    
    @ManyToMany
    private Set<Nivel> niveis =  new HashSet<>(0);


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Nivel> getNiveis() {
        return niveis;
    }

    public void setNiveis(Set<Nivel> niveis) {
        this.niveis = niveis;
    }


}
