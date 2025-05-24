package com.example.demo.service.entity;

public class Layer {
    private int layerId;
    private int waveguidesId;
    private int layerIndex;
    private double E;
    private double reEps;
    private double imEps;
    private double d;

    public Layer() {
    }

    public Layer(int layerId, int waveguidesId, int layerIndex, double E, double reEps, double imEps, double d) {
        this.layerId = layerId;
        this.waveguidesId = waveguidesId;
        this.layerIndex = layerIndex;
        this.E = E;
        this.reEps = reEps;
        this.imEps = imEps;
        this.d = d;
    }

    public int getLayerId() {
        return layerId;
    }

    public void setLayerId(int layerId) {
        this.layerId = layerId;
    }

    public int getWaveguidesId() {
        return waveguidesId;
    }

    public void setWaveguidesId(int waveguidesId) {
        this.waveguidesId = waveguidesId;
    }

    public int getLayerIndex() {
        return layerIndex;
    }

    public void setLayerIndex(int layerIndex) {
        this.layerIndex = layerIndex;
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

