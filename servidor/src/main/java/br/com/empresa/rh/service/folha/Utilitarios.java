/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.Pessoa;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

/**
 *
 * @author charles
 */
public class Utilitarios {

    private Parametros parametros;

    public Utilitarios(Parametros parametros) {
        this.parametros = parametros;
    }

    public int idadePessoa(Pessoa p) {
        Calendar a = getCalendar(p.getDataNascimento());
        Calendar b = getCalendar(parametros.getDataReferencia());
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)
                || (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public double descontaDias(double valor, int dias) {
        return valor / dias * parametros.getDiasMes();
    }

}
