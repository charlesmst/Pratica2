/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.Evento;
import java.text.DecimalFormat;
import java.util.List;
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
    public void calcula(Parametros parametros, Consulta consulta, EventoCollection eventos, Console console, Utilitarios utilitarios) {
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

        nashornEngine.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);

        // Create a Stock object to evaluate.
        // Create and enter a Context. A Context stores information about the execution environment of a script.
//        Context cx = Context.enter();
        try {
            nashornEngine.eval(evaluationScript);
            // Initialize the standard objects (Object, Function, etc.). This must be done before scripts can be
            // executed. The null parameter tells initStandardObjects
            // to create and return a scope object that we use
            // in later calls.
//            Scriptable scope = cx.initStandardObjects();
//
//            // Pass the Stock Java object to the JavaScript context
//            Object wrappedEvento = Context.javaToJS(this, scope);
//            Object wrappedConsulta = Context.javaToJS(consulta, scope);
//            Object wrappedEventos = Context.javaToJS(eventos, scope);
//
//            ScriptableObject.putProperty(scope, "evento", wrappedEvento);
//            ScriptableObject.putProperty(scope, "consulta", wrappedConsulta);
//            ScriptableObject.putProperty(scope, "eventos", wrappedEventos);

            // Execute the script
//            cx.evaluateString(scope, evaluationScript, "EvaluationScript", 1, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Exit the Context. This removes the association between the Context and the current thread and is an
            // essential cleanup action. There should be a call to exit for every call to enter.
//            Context.exit();
        }
    }

}
