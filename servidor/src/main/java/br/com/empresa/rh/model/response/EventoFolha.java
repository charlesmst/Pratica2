/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.model.response;

import br.com.empresa.rh.util.Utilitarios;

/**
 *
 * @author charles
 */
public class EventoFolha {

    private int id;
    private String nome;
    private double referencia;
    private double valorVencimento;
    private double valorDesconto;
    private int tipo;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getReferencia() {
        return referencia;
    }

    public void setReferencia(double referencia) {
        this.referencia = referencia;
    }

    public double getValorVencimento() {
        return valorVencimento;
    }

    public void setValorVencimento(double valorVencimento) {
        this.valorVencimento = valorVencimento;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public String getValorDescontoFormatado() {
        if (tipo > 0) {
            return "";
        }
        return Utilitarios.formataDinheiro(getValorDesconto());
    }

    public String getValorVencimentoFormatado() {
        if (tipo < 0) {
            return "";
        }
        return Utilitarios.formataDinheiro(getValorVencimento());
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

}
