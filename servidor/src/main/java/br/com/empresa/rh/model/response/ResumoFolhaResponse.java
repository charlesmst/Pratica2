/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.model.response;

import java.util.List;

/**
 *
 * @author charles
 */
public class ResumoFolhaResponse {

    private List<EventosSomados> eventosVisiveis;
    private List<EventosSomados> eventosInvisiveis;

    
    public List<EventosSomados> getEventosVisiveis() {
        return eventosVisiveis;
    }

    public void setEventosVisiveis(List<EventosSomados> eventosVisiveis) {
        this.eventosVisiveis = eventosVisiveis;
    }

    public List<EventosSomados> getEventosInvisiveis() {
        return eventosInvisiveis;
    }

    public void setEventosInvisiveis(List<EventosSomados> eventosInvisiveis) {
        this.eventosInvisiveis = eventosInvisiveis;
    }

}
