package com.imene.aeroportsapp.models.taf;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("degrees")
    @Expose
    public int degrees;

    @SerializedName("speed_kph")
    @Expose
    public int speed_kph;

    @SerializedName("speed_kts")
    @Expose
    public int speed_kts;

    @SerializedName("speed_mph")
    @Expose
    public int speed_mph;

    @SerializedName("speed_mps")
    @Expose
    public int speed_mps;

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

    public int getSpeed_kph() {
        return speed_kph;
    }

    public void setSpeed_kph(int speed_kph) {
        this.speed_kph = speed_kph;
    }

    public int getSpeed_kts() {
        return speed_kts;
    }

    public void setSpeed_kts(int speed_kts) {
        this.speed_kts = speed_kts;
    }

    public int getSpeed_mph() {
        return speed_mph;
    }

    public void setSpeed_mph(int speed_mph) {
        this.speed_mph = speed_mph;
    }

    public int getSpeed_mps() {
        return speed_mps;
    }

    public void setSpeed_mps(int speed_mps) {
        this.speed_mps = speed_mps;
    }
}
