/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.util;

import java.util.Calendar;
import java.util.Date;
import org.joda.time.LocalTime;

/**
 *
 * @author charles
 */
public class JavascriptDate extends java.util.Date {

    public JavascriptDate() {
    }

    public JavascriptDate(long date) {
        super(date);
    }

    public LocalTime getJodaTime() {
        return new LocalTime(this);
    }

    public static JavascriptDate fromDate(Date d){
        if(d == null)
            return null;
        return new JavascriptDate(d.getTime());
    }
    public int getMes() {
        Calendar c = Calendar.getInstance();
        c.setTime(this);
        return c.get(Calendar.MONTH) + 1;
    }

    public int getAno() {
        Calendar c = Calendar.getInstance();
        c.setTime(this);
        return c.get(Calendar.YEAR);
    }
}
