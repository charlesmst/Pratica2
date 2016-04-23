/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.Tabela;
import br.com.empresa.rh.service.EventoService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author charles
 */
public class Parametros {

    private double salario;
    private List<Tabela> tabelas;
    private double fgts;

    public Parametros(double salario, List<Tabela> tabelas, double fgts) {
        this.salario = salario;
        this.tabelas = tabelas;
        this.fgts = fgts;
    }

    public double getSalario() {
        return salario;
    }

    public List<Tabela> getTabelas() {
        return tabelas;
    }

    public double getFgts() {
        return fgts;
    }
}
