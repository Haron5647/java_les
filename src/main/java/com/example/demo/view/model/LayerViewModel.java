package com.example.demo.view.model;

public class LayerViewModel {
    private int index;
    private double E;
    private double reEps;
    private double imEps;
    private double d;

    public LayerViewModel() {
    }

    public LayerViewModel(int index, double E, double reEps, double imEps, double d) {
        this.index = index;
        this.E = E;
        this.reEps = reEps;
        this.imEps = imEps;
        this.d = d;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getE() {
        return E;
    }

    public void setE(double E) {
        this.E = E;
    }

    public double getReEps() {
        return reEps;
    }

    public void setReEps(double reEps) {
        this.reEps = reEps;
    }

    public double getImEps() {
        return imEps;
    }

    public void setImEps(double imEps) {
        this.imEps = imEps;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }
}