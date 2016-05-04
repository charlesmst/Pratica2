/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.model.request;

import br.com.empresa.rh.model.Empresa;
import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.service.folha.EventoScript;
import java.util.List;

/**
 *
 * @author charles
 */
public class CalculoFolhaRequest {
    private List<FuncionarioCargo> funcionarios;
    private Empresa empresa;
    private boolean todosEmpresa;
    private int mes;
    private int ano;
    private int tipo;
    
    private List<EventoScript> eventos;
    public List<FuncionarioCargo> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<FuncionarioCargo> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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

    public boolean isTodosEmpresa() {
        return todosEmpresa;
    }

    public void setTodosEmpresa(boolean todosEmpresa) {
        this.todosEmpresa = todosEmpresa;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public List<EventoScript> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoScript> eventos) {
        this.eventos = eventos;
    }
    
}
