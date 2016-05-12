/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service.folha;

/**
 *
 * @author charles
 */
public class Folha {

    private double salarioBase;
    private double baseFgts;
    private double baseInss;
    private double baseIrrf;
    private double fgts;

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getBaseFgts() {
        return baseFgts;
    }

    public void setBaseFgts(double baseFgts) {
        this.baseFgts = baseFgts;
    }

    public double getBaseInss() {
        return baseInss;
    }

    public void setBaseInss(double baseInss) {
        this.baseInss = baseInss;
    }

    public double getBaseIrrf() {
        return baseIrrf;
    }

    public void setBaseIrrf(double baseIrrf) {
        this.baseIrrf = baseIrrf;
    }

    public double getFgts() {
        return fgts;
    }

    public void setFgts(double fgts) {
        this.fgts = fgts;
    }

}
