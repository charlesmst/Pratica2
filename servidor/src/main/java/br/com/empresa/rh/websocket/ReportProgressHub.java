/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.websocket;

import br.com.empresa.rh.model.FolhaCalculada;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.service.folha.ReportProgress;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

/**
 *
 * @author charles
 */
public class ReportProgressHub implements ReportProgress {

    @JsonIgnore
    private FolhaHubResolve resolver;
    private int[] prontosDownload;

    public void setResolver(FolhaHubResolve resolver) {
        this.resolver = resolver;
    }

    @Override
    public void salvo(List<FolhaCalculada> folhas) {
        prontosDownload = new int[folhas.size()];
        for (int i = 0; i < folhas.size(); i++) {
            prontosDownload[i] = folhas.get(i).getId();
        }        
        setStatusText("Calculado com sucesso", true);

    }

    public ReportProgressHub(String nomeFolha) {
        this.nomeFolha = nomeFolha;
        this.id = "a" + java.util.UUID.randomUUID().toString().replace("-", "");
    }
    private String id;

    private String nomeFolha;
    private int total;
    private int atual;
    @JsonIgnore
    private FuncionarioCargo current;
    private boolean finalizado;
    private String statusText;

    public String getStatusText() {
        return statusText;
    }

    public String getId() {
        return id;
    }

    public String getNomeFolha() {
        return nomeFolha;
    }

    public void setNomeFolha(String nomeFolha) {
        this.nomeFolha = nomeFolha;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public void setTotal(int total) {
        this.total = total;
    }

    public FuncionarioCargo getCurrent() {
        return current;
    }

    @Override
    public void setCurrent(FuncionarioCargo current) {
        this.current = current;
    }

    @Override
    public void increment() {
        atual++;
        resolver.changeProgress(this);
        if (atual >= total) {
            setStatusText("Salvando...", false);
        }

    }

    public double getProgress() {
        if (total == 0d) {
            return 0d;
        }
        return atual / total;
    }

    private void setStatusText(String text, boolean remove) {
        statusText = text;
        resolver.changeStatusText(this, remove);
    }

    @Override
    public void reportError(Exception ex) {
        setStatusText("Ocorreu um erro no cálculo:" + ex.getMessage(), true);
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public int[] getProntosDownload() {
        return prontosDownload;
    }

}
