/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.model.view;

/**
 *
 * @author charles
 */
public class Folha {

    public static class Funcionario {
    }
    
    public static class FuncionarioCargo extends Funcionario{
    }

    public static class FolhaCalculada extends FuncionarioCargo{
    }

    public static class CargosFuncionario extends Funcionario {
    }
    public static class EventoVisualizacao{}
    public static class FuncionarioEvento extends EventoVisualizacao{}
}
