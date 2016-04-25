/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.Tabela;
import br.com.empresa.rh.service.EventoService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author charles
 */
public class Parametros {

    private Date dataReferencia;

    public Parametros(Date dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public Date getDataReferencia() {
        return dataReferencia;
    }
}
