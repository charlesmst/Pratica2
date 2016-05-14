/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.EventoFuncionario;
import br.com.empresa.rh.model.Ferias;
import br.com.empresa.rh.model.FolhaCalculada;
import br.com.empresa.rh.model.FolhaCalculadaEvento;
import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.Tabela;
import br.com.empresa.rh.service.DependenteService;
import br.com.empresa.rh.service.EventoFuncionarioService;
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
import org.joda.time.Days;
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
    private EventoFuncionarioService eventoFuncionarioService;
    @Autowired
    private br.com.empresa.rh.util.Utilitarios utilitarios;
    @Autowired
    private FolhaCalculadaService folhaCalculadaService;

    public void setEventos(EventoCollection eventos) {
        this.eventos = eventos;
    }

    public void calcula(FuncionarioCargo funcionario, Date data) {
        calcula(funcionario, data, this.eventos, parametrosFuncionario(data, funcionario), new Folha());
    }

    public void calcula(FuncionarioCargo funcionario, Date data, EventoCollection eventos, Parametros parametros, Folha folha) {

        Consulta c = consultas(data, funcionario, parametros);
        Utilitarios u = new Utilitarios(parametros,utilitarios);
        Console console = new Console();
        Stack<IEvento> stack = new Stack<>();//Para não acontecer loop infinito de dependencias
        int[] ordem = new int[]{EventoTipo.BASE, EventoTipo.PROVENTO, EventoTipo.DESCONTO, EventoTipo.BENEFICIO, EventoTipo.FINALIZACAO};
        for (int ordem1 : ordem) {
            for (IEvento evento : eventos.getEventos()) {
                if (ordem1 == evento.getEvento().getTipo() && !evento.isCalculado()) {
                    evento.calcula(folha, parametros, c, eventos, console, u, stack);
                }
            }
        }
        log = console.getLogs();
    }

    public void calcularTodos(List<FuncionarioCargo> funcionarios, int mes, int ano, TipoCalculo tipo) {
        calcularTodos(funcionarios, mes, ano, tipo, true);
    }

    public List<FolhaCalculada> calcularTodos(List<FuncionarioCargo> funcionarios, int mes, int ano, TipoCalculo tipo, boolean salvar) {
        Date data = utilitarios.dataPeriodo(mes, ano);
        List<FolhaCalculada> folhas = new ArrayList<>();
        for (FuncionarioCargo funcionario : funcionarios) {

            Parametros parametros = parametrosFuncionario(data, funcionario);
            EventoCollection eventosFuncionario;
            switch (tipo) {
                case ferias:
                    List<Ferias> f = feriasService.feriasMes(funcionario, mes, ano);
                    //Se não tem férias no mês, não precisa calcular
                    if (f.isEmpty()) {
                        continue;
                    }
                    parametros.setDiasMes(Days.daysBetween(new DateTime(f.get(0).getDataGozoInicio()), new DateTime(f.get(0).getDataGozoFim())).getDays());
                    eventosFuncionario = eventoService.eventosFerias();
                    break;
                case complementar:
                    eventosFuncionario = this.eventos;
                    break;
                case mes:
                default:
                    eventosFuncionario = eventoService.todosEventosFuncionario(funcionario, data);
                    break;
            }
            Folha folha = new Folha();
            calcula(funcionario, data, eventosFuncionario, parametros, folha);

            FolhaCalculada folhaCalculada = salvarEventos(funcionario, data, eventosFuncionario, mes, ano, tipo, folha, salvar);
            folhas.add(folhaCalculada);
        }
        return folhas;
    }

    private FolhaCalculada salvarEventos(FuncionarioCargo funcionario, Date data, EventoCollection eventos, int mes, int ano, TipoCalculo tipo, Folha folhaDados, boolean salvar) {
        FolhaCalculada folha = new FolhaCalculada(0, funcionario, data, new Date());
        folha.setMes(mes);
        folha.setAno(ano);
        folha.setTipo(tipo.getNumero());

        folha.setSalario(folhaDados.getSalarioBase());
        folha.setBaseFgts(folhaDados.getBaseFgts());
        folha.setBaseInss(folhaDados.getBaseInss());
        folha.setBaseIrrf(folhaDados.getBaseIrrf());
        folha.setFgts(folhaDados.getFgts());

        List<EventoFuncionario> eventosFuncionario = null;
        if (tipo == TipoCalculo.mes) {
            eventosFuncionario = eventoFuncionarioService.findForTable(null, funcionario, mes, ano);
        }
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

                if (eventosFuncionario != null) {
                    //Setar os valores certos de relação com o evento do funcionário
                    for (EventoFuncionario eventosFuncionario1 : eventosFuncionario) {
                        if (eventosFuncionario1.getEvento().getId() == evento.getEvento().getId()) {
                            folhaEvento.setEventoFuncionario(eventosFuncionario1);
                            break;
                        }
                    }
                }
            }
        }
        if (salvar) {
            folhaCalculadaService.insert(folha);
        }
        return folha;
    }

    private Parametros parametrosFuncionario(Date data, FuncionarioCargo func) {
        Parametros p = new Parametros(data);
        return p;

    }

    private Consulta consultas(Date data, FuncionarioCargo func, Parametros parametros) {
        return new Consulta(this, tabelaService, data, func, faixaSalarialService, dependenteService, feriasService, parametros, eventoService, folhaCalculadaService);
    }

    public HashMap<String, Object> getLog() {
        return log;
    }

}
