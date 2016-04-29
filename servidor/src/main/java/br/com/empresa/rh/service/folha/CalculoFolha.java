/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.Tabela;
import br.com.empresa.rh.service.DependenteService;
import br.com.empresa.rh.service.FaixaSalarialService;
import br.com.empresa.rh.service.FeriasService;
import br.com.empresa.rh.service.TabelaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author charles
 */
@Service
public class CalculoFolha {

    private EventoCollection eventos;
    private HashMap<String,Object> log;
    @Autowired
    private TabelaService tabelaService;
    @Autowired
    private FaixaSalarialService faixaSalarialService;
    @Autowired
    private DependenteService dependenteService;
    @Autowired
    private FeriasService feriasService;
    public void setEventos(EventoCollection eventos) {
        this.eventos = eventos;
    }

    public void calcula(FuncionarioCargo funcionario,Date data) {
        Parametros parametros = parametrosFuncionario(data,funcionario);
        Consulta c = consultas(data,funcionario,parametros);
        Utilitarios u = new Utilitarios(parametros);
        Console console = new Console();
        Stack<IEvento> stack = new Stack<>();//Para não acontecer loop infinito de dependencias
        int[] ordem = new int[]{EventoTipo.BASE, EventoTipo.PROVENTO, EventoTipo.DESCONTO, EventoTipo.BENEFICIO,EventoTipo.FINALIZACAO};
        for (int ordem1 : ordem) {
            for (IEvento evento : eventos.getEventos()) {
                if (ordem1 == evento.getEvento().getTipo() && !evento.isCalculado()) {
                    evento.calcula(parametros,c,eventos,console,u,stack);
                }
            }
        }
        log = console.getLogs();
    }
    
    private Parametros parametrosFuncionario(Date data,FuncionarioCargo func){
        Parametros p = new Parametros(data);
        return p;
        
    }
    private Consulta consultas(Date data,FuncionarioCargo func,Parametros parametros){
        return new Consulta(tabelaService, data,func,faixaSalarialService,dependenteService,feriasService,parametros);
    }

    public HashMap<String,Object> getLog() {
        return log;
    }
    
    
}
