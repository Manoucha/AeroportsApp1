package com.imene.aeroportsapp.models.metar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Barometer implements Serializable  {

    @SerializedName("hg")
    @Expose
    public double hg;
    @SerializedName("hpa")
    @Expose
    public int hpa;
    @SerializedName("kpa")
    @Expose
    public double kpa;
    @SerializedName("mb")
    @Expose
    public double mb;

    public Barometer() {
    }

    public double getHg() {
        return hg;
    }

    public void setHg(double hg) {
        this.hg = hg;
    }

    public int getHpa() {
        return hpa;
    }

    public void setHpa(int hpa) {
        this.hpa = hpa;
    }

    public double getKpa() {
        return kpa;
    }

    public void setKpa(double kpa) {
        this.kpa = kpa;
    }

    public double getMb() {
        return mb;
    }

    public void setMb(double mb) {
        this.mb = mb;
    }
}
