/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.util;

/**
 *
 * @author charles
 */
public class DataComercial {

    private int dia;
    private int mes;
    private int ano;

    public DataComercial(int dia, int mes, int ano) {
        setDia(dia);
        this.mes = mes;
        this.ano = ano;
    }

    public void nextMonth() {
        mes++;
        if (mes > 12) {
            ano++;
        }
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        if (dia > 30) {
            this.dia = 30;
        } else {
            this.dia = dia;
        }
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

}
