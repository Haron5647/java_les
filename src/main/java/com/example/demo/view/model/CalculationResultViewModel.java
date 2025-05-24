package com.example.demo.view.model;

public class CalculationResultViewModel {
    private double neffTM;
    private double alphaTM;
    private double alphaTMWG;
    private double alpha4TMWG;
    private String plotBase64;

    public CalculationResultViewModel() {
    }

    public CalculationResultViewModel(double neffTM, double alphaTM, double alphaTMWG, double alpha4TMWG, String plotBase64) {
        this.neffTM = neffTM;
        this.alphaTM = alphaTM;
        this.alphaTMWG = alphaTMWG;
        this.alpha4TMWG = alpha4TMWG;
        this.plotBase64 = plotBase64;
    }

    public double getNeffTM() {
        return neffTM;
    }

    public void setNeffTM(double neffTM) {
        this.neffTM = neffTM;
    }

    public double getAlphaTM() {
        return alphaTM;
    }

    public void setAlphaTM(double alphaTM) {
        this.alphaTM = alphaTM;
    }

    public double getAlphaTMWG() {
        return alphaTMWG;
    }

    public void setAlphaTMWG(double alphaTMWG) {
        this.alphaTMWG = alphaTMWG;
    }

    public double getAlpha4TMWG() {
        return alpha4TMWG;
    }

    public void setAlpha4TMWG(double alpha4TMWG) {
        this.alpha4TMWG = alpha4TMWG;
    }

    public String getPlotBase64() {
        return plotBase64;
    }

    public void setPlotBase64(String plotBase64) {
        this.plotBase64 = plotBase64;
    }
}