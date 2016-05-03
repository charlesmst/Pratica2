/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author charles
 */
public class Dependencia {
    private Consulta consulta;
    private HashMap<String, IEvento> eventos;

    public Dependencia(Consulta consulta) {
        this.eventos = new HashMap<>();
        this.consulta = consulta;
    }

    public void add(String nome, IEvento valor) {
        eventos.put(nome, valor);
    }
    
    public IEvento get(String identificador) {
        if (eventos.containsKey(identificador) && eventos.get(identificador).isCalculado()) {
            return eventos.get(identificador);
        } else {
            throw new FolhaException("Evento dependente com o identificador de " + identificador + " não encontrado para funcionário");
        }
    }

    public IEvento getOpcional(String identificador) {
        if (eventos.containsKey(identificador) && eventos.get(identificador).isCalculado()) {
            return eventos.get(identificador);
        } else {
            return null;
        }
    }
    public double getValorPeriodo(String identificador,int mesinicio,int anoinicio,int mesfim, int anofim){
        if(eventos.containsKey(identificador)){
            return consulta.getValorEventoPeriodo(eventos.get(identificador).getEvento(), mesinicio, anoinicio, mesfim, anofim);
        }else{
            throw new FolhaException("Evento do identificador não encontrado");
        }
    }
    public double getValorPeriodo(String identificador,Date dinicio,Date dfim){
        DateTime dini = new DateTime(dinicio);
        DateTime df = new DateTime(dfim);
        return getValorPeriodo(identificador, dini.getMonthOfYear(), dini.getYear(),df.getMonthOfYear(),df.getYear());
    }
}
