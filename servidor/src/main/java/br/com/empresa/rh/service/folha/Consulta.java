/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.Evento;
import br.com.empresa.rh.model.FaixaSalarialValor;
import br.com.empresa.rh.model.Ferias;
import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.Pessoa;
import br.com.empresa.rh.model.Tabela;
import br.com.empresa.rh.model.TabelaValores;
import br.com.empresa.rh.service.DependenteService;
import br.com.empresa.rh.service.EventoService;
import br.com.empresa.rh.service.FaixaSalarialService;
import br.com.empresa.rh.service.FeriasService;
import br.com.empresa.rh.service.TabelaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author charles
 */
public class Consulta {

    
    private TabelaService tabelaService;
    private Date data;
    private FuncionarioCargo funcionarioCargo;
    private FaixaSalarialService faixaSalarialService;
    private DependenteService dependenteService;
    private FeriasService feriasService;
    private EventoService eventoService;
    private Parametros parametros;
    public Consulta(TabelaService tabelaService, Date data, FuncionarioCargo funcionarioCargo,FaixaSalarialService faixaSalarialService,DependenteService dependenteService,FeriasService feriasService,Parametros parametros,EventoService eventoService) {
        this.tabelaService = tabelaService;
        this.data = data;
        this.funcionarioCargo = funcionarioCargo;
        this.faixaSalarialService = faixaSalarialService;
        this.dependenteService = dependenteService;
        this.feriasService = feriasService;
        this.parametros = parametros;
        this.eventoService = eventoService;
    }
    
    public Set<TabelaValores> getTabela(String tipo){
        return tabelaService.tabelaData(data, tipo).getTabelaValoreses();
    }
    
    public FaixaSalarialValor getSalario(){
        return faixaSalarialService.valorDoCargo(funcionarioCargo, data);
    }
    
    public List<Pessoa> getDependentes(){
        return dependenteService.dependentesDe(funcionarioCargo.getFuncionario().getPessoaId(),data);
    }
    
    /**
     * Consulta a quantidade de dias de férias que entram no período do mês, sem considerar o que não é deste período
     **/
    public int getQuantidadeFerias(){
        return feriasService.quantidadeDiasFerias(funcionarioCargo.getId(), parametros.getMes(),parametros.getAno());
    }
    public List<Ferias> getFeriasDoMes(){
        return feriasService.feriasMes(funcionarioCargo, parametros.getMes(), parametros.getAno());
    }
    /**
     * Consulta a quantidade de dias de férias do período que inicia no mês(Leva em consideração o período inteiro)
     */
    public int getQuantidadePeriodoFerias(){
        List<Ferias> ferias = getFeriasDoMes();
        
        int q = 0;
        for (Ferias feria : ferias) {
            q+= Days.daysBetween(new DateTime(feria.getDataGozoInicio()), new DateTime(feria.getDataAquisitivoFim())).getDays();
        }
        return q;
    }
    public double getValorEventoPeriodo(Evento evento,int mesinicio,int anoinicio,int mesfim, int anofim){
        return eventoService.valorPeriodo(funcionarioCargo,evento,mesinicio,anoinicio,mesfim, anofim);
    }
}
