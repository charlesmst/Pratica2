/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.util;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import org.joda.time.LocalDate;
import org.joda.time.MutableDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author charles
 */
@Component

public class Utilitarios {

    public static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
    private SecurityContext secutiryContext;

    public void setSecutiryContext(SecurityContext secutiryContext) {
        this.secutiryContext = secutiryContext;
    }

    public boolean usuarioTemPermissao(String minimo) {
        return NivelAcesso.getNivel(minimo) <= nivelUsuario();
    }

    public int nivelUsuario() {
        for (int i = 5; i >= 1; i--) {
            if (secutiryContext.isUserInRole(i + "")) {
                return i;
            }
        }
        return 0;
    }

    public int usuario() {
        return Integer.parseInt(secutiryContext.getUserPrincipal().getName());
    }

    public boolean usuarioIs(String role) {
        return secutiryContext.isUserInRole(role);
    }

    public static int usuarioId(SecurityContext context) {
        return 1;
    }

    public static String formataDinheiro(double d) {
        return new DecimalFormat("'R$'###,##0.00").format(d);
    }

    public Date dataPeriodo(int mes, int ano) {
        LocalDate d = new LocalDate();
        d = d.withDayOfMonth(1);
        d = d.withMonthOfYear(mes);
        d = d.withYear(ano);
        return d.toDate();
//        new org.joda.time.DateTime(new Date()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
////                .withDayOfMonth(1)
//                .withMonthOfYear(mes)
////                .withYear(ano)
//                .toDate();
    }

    public Date dataPeriodoInicio(Date data) {
        LocalDate d = new LocalDate(data);
        d = d.withDayOfMonth(1);
        return d.toDate();
    }
     public Date dataPeriodoFim(Date data) {
        LocalDate d = new LocalDate(data);
        d = d.withDayOfMonth(1).plusMonths(1).minusDays(1);
        return d.toDate();
    }
}
