/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.FolhaCalculada;
import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.FuncionarioCargo;
import java.util.List;

/**
 *
 * @author charles
 */
public interface ReportProgress {
    void setCurrent(FuncionarioCargo current);
    void increment();
    void setTotal(int total);
    void reportError(Exception ex);
    void salvo(List<FolhaCalculada> folhas);
}
