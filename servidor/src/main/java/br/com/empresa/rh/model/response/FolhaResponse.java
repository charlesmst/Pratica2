/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.model.response;

import br.com.empresa.rh.util.Utilitarios;
import java.util.Date;
import java.util.List;

/**
 *
 * @author charles
 */
public class FolhaResponse {

    private String titulo;
    private String nome;
    private String competencia;
    private String empresa;
    private String cbo;
    private String cargo;
    private List<EventoFolha> eventos;
    private List<EventoFolha> eventosInvisiveis;
    private String tipo;

    private String salarioBase;
    private String baseFgts;
    private String baseInss;
    private String baseIrrf;
    private String fgts;

    private String conta;
    private String agencia;
    private String unidade;
    private Date admissao;
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCbo() {
        return cbo;
    }

    public void setCbo(String cbo) {
        this.cbo = cbo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public List<EventoFolha> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoFolha> eventos) {
        this.eventos = eventos;
    }

    public List<EventoFolha> getEventosInvisiveis() {
        return eventosInvisiveis;
    }

    public void setEventosInvisiveis(List<EventoFolha> eventosInvisiveis) {
        this.eventosInvisiveis = eventosInvisiveis;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(String salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getBaseFgts() {
        return baseFgts;
    }

    public void setBaseFgts(String baseFgts) {
        this.baseFgts = baseFgts;
    }

    public String getBaseInss() {
        return baseInss;
    }

    public void setBaseInss(String baseInss) {
        this.baseInss = baseInss;
    }

    public String getBaseIrrf() {
        return baseIrrf;
    }

    public void setBaseIrrf(String baseIrrf) {
        this.baseIrrf = baseIrrf;
    }

    public String getFgts() {
        return fgts;
    }

    public void setFgts(String fgts) {
        this.fgts = fgts;
    }

    public String getValorLiquido() {
        return Utilitarios.formataDinheiro(getTotalVencimentoValor() - getTotalDescontoValor());
    }

    public String getTotalVencimento() {

        return Utilitarios.formataDinheiro(getTotalVencimentoValor());
    }

    private double getTotalVencimentoValor() {

        double d = 0;
        for (EventoFolha evento : eventos) {
            d += evento.getValorVencimento();
        }
        return d;
    }

    private double getTotalDescontoValor() {
        double d = 0;
        for (EventoFolha evento : eventos) {
            d += evento.getValorDesconto();
        }
        return d;
    }

    public String getTotalDesconto() {
        return Utilitarios.formataDinheiro(getTotalDescontoValor());
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }


}
