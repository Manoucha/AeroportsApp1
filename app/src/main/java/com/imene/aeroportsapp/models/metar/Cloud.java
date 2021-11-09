package com.imene.aeroportsapp.models.metar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cloud implements Serializable  {

    @SerializedName("base_feet_agl")
    @Expose
    public int base_feet_agl;
    @SerializedName("base_meters_agl")
    @Expose
    public int base_meters_agl;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("feet")
    @Expose
    public int feet;
    @SerializedName("meters")
    @Expose
    public int meters;
    @SerializedName("text")
    @Expose
    public String text;

    public Cloud() {
    }

    public int getBase_feet_agl() {
        return base_feet_agl;
    }

    public void setBase_feet_agl(int base_feet_agl) {
        this.base_feet_agl = base_feet_agl;
    }

    public int getBase_meters_agl() {
        return base_meters_agl;
    }

    public void setBase_meters_agl(int base_meters_agl) {
        this.base_meters_agl = base_meters_agl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFeet() {
        return feet;
    }

    public void setFeet(int feet) {
        this.feet = feet;
    }

    public int getMeters() {
        return meters;
    }

    public void setMeters(int meters) {
        this.meters = meters;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
