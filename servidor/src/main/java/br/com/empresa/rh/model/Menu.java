package br.com.empresa.rh.model;

import br.com.empresa.rh.model.view.MenuList;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@SequenceGenerator(name = "menu_seq", sequenceName = "menu_seq", initialValue = 1, allocationSize = 1)
@XmlRootElement
public class Menu implements Serializable, MenuList {

    @Id
    @GeneratedValue(generator = "menu_seq", strategy = GenerationType.SEQUENCE)
    private long id;
    private String nome;
    private String icone;
    private String url;

    @ManyToMany
    private List<Nivel> niveis;


    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Nivel> getNiveis() {
        return niveis;
    }

    public void setNiveis(List<Nivel> niveis) {
        this.niveis = niveis;
    }

}
