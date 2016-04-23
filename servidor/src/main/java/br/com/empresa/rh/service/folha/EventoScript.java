/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

import br.com.empresa.rh.model.Evento;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

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
        return valorCalculado;
    }

    public void setValorCalculado(double valorCalculado) {
        this.valorCalculado = valorCalculado;
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
    public void calcula(Parametros parametros) {
        String evaluationScript = evento.getScript();

        // Create a Stock object to evaluate.
        // Create and enter a Context. A Context stores information about the execution environment of a script.
        Context cx = Context.enter();
        try {
            // Initialize the standard objects (Object, Function, etc.). This must be done before scripts can be
            // executed. The null parameter tells initStandardObjects
            // to create and return a scope object that we use
            // in later calls.
            Scriptable scope = cx.initStandardObjects();

            // Pass the Stock Java object to the JavaScript context
            Object wrappedEvento = Context.javaToJS(this, scope);
           
            ScriptableObject.putProperty(scope, "evento", wrappedEvento);
           
            // Execute the script
            cx.evaluateString(scope, evaluationScript, "EvaluationScript", 1, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Exit the Context. This removes the association between the Context and the current thread and is an
            // essential cleanup action. There should be a call to exit for every call to enter.
            Context.exit();
        }
    }

}
