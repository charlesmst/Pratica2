/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author charles
 */
public class Console {

    private int numero = 1;
    private HashMap<String, Object> logs = new HashMap<>();

    public void log(Object l) {
        logs.put(numero++ +" "+new Date().toString()+":", l);
    }

    public HashMap<String,Object> getLogs() {
        return logs;
    }
}
