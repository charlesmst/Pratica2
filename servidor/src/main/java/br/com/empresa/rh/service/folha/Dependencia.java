/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author charles
 */
public class Dependencia {

    private HashMap<String, IEvento> eventos;

    public Dependencia() {
        this.eventos = new HashMap<>();
    }

    public void add(String nome, IEvento valor) {
        eventos.put(nome, valor);
    }

    public IEvento get(String identificador) {
        if (eventos.containsKey(identificador)) {
            return eventos.get(identificador);
        } else {
            throw new FolhaException("Evento dependente com o identificador de "+identificador+" não encontrado para funcionário");
        }
    }
     public IEvento getOpcional(String identificador) {
        if (eventos.containsKey(identificador)) {
            return eventos.get(identificador);
        } else {
            return null;
        }
    }
}
