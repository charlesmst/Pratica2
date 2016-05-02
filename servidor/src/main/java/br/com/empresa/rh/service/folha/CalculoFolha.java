/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.FolhaCalculada;
import br.com.empresa.rh.model.FolhaCalculadaEvento;
import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.Tabela;
import br.com.empresa.rh.service.DependenteService;
import br.com.empresa.rh.service.EventoService;
import br.com.empresa.rh.service.FaixaSalarialService;
import br.com.empresa.rh.service.FeriasService;
import br.com.empresa.rh.service.FolhaCalculadaService;
import br.com.empresa.rh.service.TabelaService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author charles
 */
@Service
public class CalculoFolha {

    private EventoCollection eventos;
    private HashMap<String, Object> log;
    @Autowired
    private TabelaService tabelaService;
    @Autowired
    private FaixaSalarialService faixaSalarialService;
    @Autowired
    private DependenteService dependenteService;
    @Autowired
    private FeriasService feriasService;
    @Autowired
    private EventoService eventoService;

    @Autowired
    private FolhaCalculadaService folhaCalculadaService;

    public void setEventos(EventoCollection eventos) {
        this.eventos = eventos;
    }

    public void calcula(FuncionarioCargo funcionario, Date data) {
        calcula(funcionario, data, this.eventos);
    }

    public void calcula(FuncionarioCargo funcionario, Date data, EventoCollection eventos) {
        Parametros parametros = parametrosFuncionario(data, funcionario);
        Consulta c = consultas(data, funcionario, parametros);
        Utilitarios u = new Utilitarios(parametros);
        Console console = new Console();
        Stack<IEvento> stack = new Stack<>();//Para não acontecer loop infinito de dependencias
        int[] ordem = new int[]{EventoTipo.BASE, EventoTipo.PROVENTO, EventoTipo.DESCONTO, EventoTipo.BENEFICIO, EventoTipo.FINALIZACAO};
        for (int ordem1 : ordem) {
            for (IEvento evento : eventos.getEventos()) {
                if (ordem1 == evento.getEvento().getTipo() && !evento.isCalculado()) {
                    evento.calcula(parametros, c, eventos, console, u, stack);
                }
            }
        }
        log = console.getLogs();
    }

    public void calcularTodos(List<FuncionarioCargo> funcionarios, int mes, int ano, TipoCalculo tipo) {
        Date data = new DateTime().withYear(ano).withMonthOfYear(mes).withDayOfMonth(1).toDate();

        for (FuncionarioCargo funcionario : funcionarios) {

            EventoCollection eventosFuncionario;
            switch (tipo) {
                case ferias:
                    eventosFuncionario = eventoService.eventosFerias();
                    break;
                case mes:
                default:
                    eventosFuncionario = eventoService.todosEventosFuncionario(funcionario, data);
                    break;
            }
            calcula(funcionario, data, eventosFuncionario);
            salvarEventos(funcionario, data, eventosFuncionario, mes, ano, tipo);
        }
    }

    private void salvarEventos(FuncionarioCargo funcionario, Date data, EventoCollection eventos, int mes, int ano, TipoCalculo tipo) {
        FolhaCalculada folha = new FolhaCalculada(0, funcionario, data, new Date());
        folha.setMes(mes);
        folha.setAno(ano);
        folha.setTipo(tipo.getNumero());

        for (IEvento evento : eventos.getEventos()) {
            if (evento.isAplicavel()) {
                FolhaCalculadaEvento folhaEvento = new FolhaCalculadaEvento();
                folhaEvento.setEvento(evento.getEvento());
                folhaEvento.setFolhaCalculada(folha);
                folhaEvento.setReferencia(evento.getReferencia());
                folhaEvento.setTipo(evento.getEvento().getTipo());
                folhaEvento.setValor(evento.getValorCalculado());
                folhaEvento.setVisivel(evento.getEvento().isVisivelFolha());
                folha.getFolhaCalculadaEventos().add(folhaEvento);
            }
        }
        folhaCalculadaService.insert(folha);
    }

    private Parametros parametrosFuncionario(Date data, FuncionarioCargo func) {
        Parametros p = new Parametros(data);
        return p;

    }

    private Consulta consultas(Date data, FuncionarioCargo func, Parametros parametros) {
        return new Consulta(tabelaService, data, func, faixaSalarialService, dependenteService, feriasService, parametros);
    }

    public HashMap<String, Object> getLog() {
        return log;
    }

}
