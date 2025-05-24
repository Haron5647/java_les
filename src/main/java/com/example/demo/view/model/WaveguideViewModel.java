package com.example.demo.view.model;

import java.util.List;

public class WaveguideViewModel {
    private int id;
    private double nEffMin;
    private double nEffMax;
    private String creationDate;
    private List<LayerViewModel> layers;

    public WaveguideViewModel() {
    }

    public WaveguideViewModel(int id, double nEffMin, double nEffMax, String creationDate, List<LayerViewModel> layers) {
        this.id = id;
        this.nEffMin = nEffMin;
        this.nEffMax = nEffMax;
        this.creationDate = creationDate;
        this.layers = layers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNEffMin() {
        return nEffMin;
    }

    public void setNEffMin(double nEffMin) {
        this.nEffMin = nEffMin;
    }

    public double getNEffMax() {
        return nEffMax;
    }

    public void setNEffMax(double nEffMax) {
        this.nEffMax = nEffMax;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<LayerViewModel> getLayers() {
        return layers;
    }

    public void setLayers(List<LayerViewModel> layers) {
        this.layers = layers;
    }
}