/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.model.response;

import br.com.empresa.rh.service.folha.EventoCollection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author charles
 */
public class EventoTesteResponse {
    private HashMap<String,Object> logs;
    private EventoCollection eventos;

    public HashMap<String, Object> getLogs() {
        return logs;
    }

    public void setLogs(HashMap<String, Object> logs) {
        this.logs = logs;
    }

    public EventoCollection getEventos() {
        return eventos;
    }

    public void setEventos(EventoCollection eventos) {
        this.eventos = eventos;
    }


    
    
}
