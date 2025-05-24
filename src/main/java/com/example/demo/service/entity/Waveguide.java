package com.example.demo.service.entity;

public class Waveguide {
    private int waveguidesId;
    private int userId;
    private double nEffMin;
    private double nEffMax;
    private java.sql.Timestamp createdAt;

    public Waveguide() {
    }

    public Waveguide(int waveguidesId, int userId, double nEffMin, double nEffMax, java.sql.Timestamp createdAt) {
        this.waveguidesId = waveguidesId;
        this.userId = userId;
        this.nEffMin = nEffMin;
        this.nEffMax = nEffMax;
        this.createdAt = createdAt;
    }

    public int getWaveguidesId() {
        return waveguidesId;
    }

    public void setWaveguidesId(int waveguidesId) {
        this.waveguidesId = waveguidesId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getnEffMin() {
        return nEffMin;
    }

    public void setnEffMin(double nEffMin) {
        this.nEffMin = nEffMin;
    }

    public double getnEffMax() {
        return nEffMax;
    }

    public void setnEffMax(double nEffMax) {
        this.nEffMax = nEffMax;
    }

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

