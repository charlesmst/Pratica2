/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.model.response;

/**
 *
 * @author charles
 */
public class EventoFolha {

    private int id;
    private String nome;
    private int referencia;
    private double valorVencimento;
    private double valorDesconto;

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

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
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

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

}
