/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.Pessoa;
import br.com.empresa.rh.util.JavascriptDate;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.MutableDateTime;
import sun.print.resources.serviceui;

/**
 *
 * @author charles
 */
public class Utilitarios {

    private Parametros parametros;
    private br.com.empresa.rh.util.Utilitarios utilitarios;

    public Utilitarios(Parametros parametros, br.com.empresa.rh.util.Utilitarios utilitarios) {
        this.parametros = parametros;
        this.utilitarios = utilitarios;
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

    public double descontaDias(double valor) {
        return valor / 30 * parametros.getDiasMes();
    }

    public double descontaDiasProporcional(double valor) {
        return valor / diasMes(parametros.getDataReferencia()) * parametros.getDiasMes();
    }

    public double descontaConformeParametros(double valor){
        if(parametros.isProporcional())
            return descontaDiasProporcional(valor);
        else
            return descontaDias(valor);
    }
    public double diasMes(Date data) {
        return new LocalDate(data).withDayOfMonth(1).plusMonths(1).minusDays(1).getDayOfMonth();
    }

    public Date dateMin(Date d1, Date d2) {
        if (d1.compareTo(d2) > 0) {
            return d2;
        }
        return d1;
    }

    public Date dateMax(Date d1, Date d2) {
        if (d1.compareTo(d2) < 0) {
            return d2;
        }
        return d1;
    }

    public JavascriptDate dataPeriodo(int mes, int ano) {
        return utilitarios.dataPeriodo(mes, ano);
    }
}
