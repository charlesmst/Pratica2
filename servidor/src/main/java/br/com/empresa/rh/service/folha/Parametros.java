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
import java.util.Calendar;
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
    private int mes;
    private int ano;

    public Parametros(Date dataReferencia) {
        this.dataReferencia = dataReferencia;

        Calendar c = Calendar.getInstance();
        c.setTime(dataReferencia);
        mes = c.get(Calendar.MONTH);
        ano = c.get(Calendar.YEAR);
    }

    public Date getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(Date dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public int getMes() {
        return mes;
    }

    public int getDiasMes(){
        return 30;
    }
    public int getAno() {
        return ano;
    }

}
