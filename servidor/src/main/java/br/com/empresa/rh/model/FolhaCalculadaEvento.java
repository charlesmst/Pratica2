package br.com.empresa.rh.model;
// Generated 19/04/2016 00:32:26 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * FolhaCalculadaEvento generated by hbm2java
 */
@Entity
@Table(name = "folha_calculada_evento", schema = "public"
)
@SequenceGenerator(name = "folha_calculada_evento_seq", sequenceName = "folha_calculada_evento_seq", initialValue = 1, allocationSize = 1)

public class FolhaCalculadaEvento implements java.io.Serializable {

    private int id;
    private Evento evento;
    private FolhaCalculada folhaCalculada;
    private double valor;
    private boolean visivel;
    private int tipo;
    private String nomeEvento;
    private double referencia;
    private EventoFuncionario eventoFuncionario;

    public FolhaCalculadaEvento() {
    }

    public FolhaCalculadaEvento(int id, Evento evento, FolhaCalculada folhaCalculada, double valor, boolean visivel, int tipo, int referencia) {
        this.id = id;
        this.evento = evento;
        this.folhaCalculada = folhaCalculada;
        this.valor = valor;
        this.visivel = visivel;
        this.tipo = tipo;
        this.referencia = referencia;
    }

    @Id
    @GeneratedValue(generator = "folha_calculada_evento_seq", strategy = GenerationType.SEQUENCE)

    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    public Evento getEvento() {
        return this.evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folha_calculada_id", nullable = false)
    public FolhaCalculada getFolhaCalculada() {
        return this.folhaCalculada;
    }

    public void setFolhaCalculada(FolhaCalculada folhaCalculada) {
        this.folhaCalculada = folhaCalculada;
    }

    @Column(name = "valor", nullable = false, precision = 10)
    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Column(name = "visivel", nullable = false)
    public boolean isVisivel() {
        return this.visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    @Column(name = "tipo", nullable = false, length = 1)
    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Column(name = "referencia", nullable = false)
    public double getReferencia() {
        return this.referencia;
    }

    public void setReferencia(double referencia) {
        this.referencia = referencia;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "evento_funcionario_id")
    public EventoFuncionario getEventoFuncionario() {
        return eventoFuncionario;
    }

    public void setEventoFuncionario(EventoFuncionario eventoFuncionario) {
        this.eventoFuncionario = eventoFuncionario;
    }

    @Column(nullable = true,name = "nome_evento")
    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

}
