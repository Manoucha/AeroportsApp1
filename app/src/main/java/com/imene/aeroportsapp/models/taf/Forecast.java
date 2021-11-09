package com.imene.aeroportsapp.models.taf;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {

    @SerializedName("clouds")
    @Expose
    public List<Cloud> clouds;

    @SerializedName("timestamp")
    @Expose
    public Timestamp timestamp;


    @SerializedName("visibility")
    @Expose
    public Visibility visibility;

    @SerializedName("wind")
    @Expose
    public Wind wind;

    @SerializedName("change")
    @Expose
    public Change change;

    @SerializedName("conditions")
    @Expose
    public List<Condition> conditions;

    @SerializedName("ceiling")
    @Expose
    public Ceiling ceiling;

    public List<Cloud> getClouds() {
        return clouds;
    }

    public void setClouds(List<Cloud> clouds) {
        this.clouds = clouds;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Change getChange() {
        return change;
    }

    public void setChange(Change change) {
        this.change = change;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public Ceiling getCeiling() {
        return ceiling;
    }

    public void setCeiling(Ceiling ceiling) {
        this.ceiling = ceiling;
    }
}
