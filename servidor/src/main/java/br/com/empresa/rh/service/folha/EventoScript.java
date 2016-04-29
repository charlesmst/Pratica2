/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.Evento;
import br.com.empresa.rh.model.EventoDependencia;
import br.com.empresa.rh.util.ApiException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Stack;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.NativeJSON;

/**
 *
 * @author charles
 */
public class EventoScript implements IEvento {

    private Evento evento;
    private double valorCalculado;
    private int referencia;
    private boolean aplicavel = true;
    private boolean calculado = false;

    public EventoScript(Evento evento) {
        this.evento = evento;
    }

    @Override
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public double getValorCalculado() {
        if (!aplicavel) {
            return 0d;
        }
        return valorCalculado;
    }

    public void setValorCalculado(double valorCalculado) {

        this.valorCalculado = Math.round(valorCalculado * 100d) / 100d;
    }

    @Override
    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    @Override
    public boolean isAplicavel() {
        return aplicavel;
    }

    public void setAplicavel(boolean aplicavel) {
        this.aplicavel = aplicavel;
    }

    @Override
    public void calcula(Parametros parametros, Consulta consulta, EventoCollection eventos, Console console, Utilitarios utilitarios, Stack<IEvento> stack) {
        if (stack.contains(this)) {
            throw new ApiException("Dependência mutua detectada em evento " + evento.getId(), null);
        }
        Dependencia dependencia = new Dependencia();
        stack.push(this);
        //calcula as dependencias primeiro se ainda não foi calculada
        for (EventoDependencia eventoDependencia : getEvento().getEventoDependencias()) {
            //Encontra o evento com esse id
            for (IEvento e : eventos.getEventos()) {
                if (e.getEvento().getId() == eventoDependencia.getId().getEventoDependenciaId()) {
                    if (!e.isCalculado()) {
                        e.calcula(parametros, consulta, eventos, console, utilitarios, stack);
                    }
                    dependencia.add(eventoDependencia.getNomeVariavel(), e);
                    break;
                }
            }
        }
        stack.pop();

        String evaluationScript = evento.getScript();
        ScriptEngineManager scriptManager = new ScriptEngineManager();
        ScriptEngine nashornEngine = scriptManager.getEngineByName("nashorn");

        Bindings bindings = new SimpleBindings();
        bindings.put("evento", this);
        bindings.put("consulta", consulta);
        bindings.put("eventos", eventos);
        bindings.put("console", console);
        bindings.put("parametros", parametros);
        bindings.put("utilitarios", utilitarios);
        bindings.put("dependencia", dependencia);

        nashornEngine.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);
        try {
            nashornEngine.eval(evaluationScript);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FolhaException("Erro no cálculo do evento " + evento.getId() + " para colaborador ",e);
        } 
        calculado = true;
    }

    public boolean isCalculado() {
        return calculado;
    }

}
