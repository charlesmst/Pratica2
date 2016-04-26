/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.FaixaSalarialValor;
import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.Pessoa;
import br.com.empresa.rh.model.Tabela;
import br.com.empresa.rh.model.TabelaValores;
import br.com.empresa.rh.service.DependenteService;
import br.com.empresa.rh.service.FaixaSalarialService;
import br.com.empresa.rh.service.FeriasService;
import br.com.empresa.rh.service.TabelaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private Parametros parametros;
    public Consulta(TabelaService tabelaService, Date data, FuncionarioCargo funcionarioCargo,FaixaSalarialService faixaSalarialService,DependenteService dependenteService,FeriasService feriasService,Parametros parametros) {
        this.tabelaService = tabelaService;
        this.data = data;
        this.funcionarioCargo = funcionarioCargo;
        this.faixaSalarialService = faixaSalarialService;
        this.dependenteService = dependenteService;
        this.feriasService = feriasService;
        this.parametros = parametros;
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
    
    public int getQuantidadeFerias(){
        return feriasService.quantidadeDiasFerias(funcionarioCargo.getId(), parametros.getMes(),parametros.getAno());
    }
    
}
