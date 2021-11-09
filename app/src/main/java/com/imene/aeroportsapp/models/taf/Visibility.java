package com.imene.aeroportsapp.models.taf;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Visibility {


    @SerializedName("meters")
    @Expose
    public String meters;

    @SerializedName("meters_float")
    @Expose
    public int meters_float;


    @SerializedName("miles")
    @Expose
    public String miles;


    @SerializedName("miles_float")
    @Expose
    public double miles_float;

    public Visibility() {
    }

    public String getMeters() {
        return meters;
    }

    public void setMeters(String meters) {
        this.meters = meters;
    }

    public int getMeters_float() {
        return meters_float;
    }

    public void setMeters_float(int meters_float) {
        this.meters_float = meters_float;
    }

    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }

    public double getMiles_float() {
        return miles_float;
    }

    public void setMiles_float(double miles_float) {
        this.miles_float = miles_float;
    }
}
