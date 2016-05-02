package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FolhaCalculada generated by hbm2java
 */
@Entity
@Table(name = "folha_calculada", schema = "public"
)
@SequenceGenerator(name = "folha_calculada_seq", sequenceName = "folha_calculada_seq", initialValue = 1, allocationSize = 1)

public class FolhaCalculada implements java.io.Serializable {

    private int id;
    private FuncionarioCargo funcionarioCargo;
    private Date dataReferente;
    private Date dataGerado;
    private Set<FolhaCalculadaEvento> folhaCalculadaEventos = new HashSet<FolhaCalculadaEvento>(0);

    private int mes;
    private int ano;
    private int tipo;

    private boolean excluido;
    
    public FolhaCalculada() {
    }

    public FolhaCalculada(int id, FuncionarioCargo funcionarioCargo, Date dataReferente, Date dataGerado) {
        this.id = id;
        this.funcionarioCargo = funcionarioCargo;
        this.dataReferente = dataReferente;
        this.dataGerado = dataGerado;
    }

    public FolhaCalculada(int id, FuncionarioCargo funcionarioCargo, Date dataReferente, Date dataGerado, Set<FolhaCalculadaEvento> folhaCalculadaEventos) {
        this.id = id;
        this.funcionarioCargo = funcionarioCargo;
        this.dataReferente = dataReferente;
        this.dataGerado = dataGerado;
        this.folhaCalculadaEventos = folhaCalculadaEventos;
    }

    @Id

    @GeneratedValue(generator = "folha_calculada_seq", strategy = GenerationType.SEQUENCE)

    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_cargo_id", nullable = false)
    public FuncionarioCargo getFuncionarioCargo() {
        return this.funcionarioCargo;
    }

    public void setFuncionarioCargo(FuncionarioCargo funcionarioCargo) {
        this.funcionarioCargo = funcionarioCargo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_referente", nullable = false, length = 13)
    public Date getDataReferente() {
        return this.dataReferente;
    }

    public void setDataReferente(Date dataReferente) {
        this.dataReferente = dataReferente;
    }

    @Column(name = "data_gerado", nullable = false)
    public Date getDataGerado() {
        return this.dataGerado;
    }

    public void setDataGerado(Date dataGerado) {
        this.dataGerado = dataGerado;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "folhaCalculada", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<FolhaCalculadaEvento> getFolhaCalculadaEventos() {
        return this.folhaCalculadaEventos;
    }

    public void setFolhaCalculadaEventos(Set<FolhaCalculadaEvento> folhaCalculadaEventos) {
        this.folhaCalculadaEventos = folhaCalculadaEventos;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

}
