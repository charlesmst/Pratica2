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
public class ResumoFolhaItem {
    private String titulo;
    private List<EventosSomados> eventos;

    public ResumoFolhaItem(String titulo, List<EventosSomados> eventos) {
        this.titulo = titulo;
        this.eventos = eventos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<EventosSomados> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventosSomados> eventos) {
        this.eventos = eventos;
    }
    
    
    
}
