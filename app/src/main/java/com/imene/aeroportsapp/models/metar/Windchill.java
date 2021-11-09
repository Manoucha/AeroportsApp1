package com.imene.aeroportsapp.models.metar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Windchill implements Serializable  {

    @SerializedName("celsius")
    @Expose
    public int celsius;

    @SerializedName("fahrenheit")
    @Expose
    public int fahrenheit;

    public Windchill() {
    }

    public int getCelsius() {
        return celsius;
    }

    public void setCelsius(int celsius) {
        this.celsius = celsius;
    }

    public int getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(int fahrenheit) {
        this.fahrenheit = fahrenheit;
    }
}
