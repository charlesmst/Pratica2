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
public class FolhaResponse {

    private String nome;
    private String cargo;
    private List<EventoFolha> eventos;
    private List<EventoFolha> eventosInvisiveis;
    private String tipo;

   

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public List<EventoFolha> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoFolha> eventos) {
        this.eventos = eventos;
    }

    public List<EventoFolha> getEventosInvisiveis() {
        return eventosInvisiveis;
    }

    public void setEventosInvisiveis(List<EventoFolha> eventosInvisiveis) {
        this.eventosInvisiveis = eventosInvisiveis;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
