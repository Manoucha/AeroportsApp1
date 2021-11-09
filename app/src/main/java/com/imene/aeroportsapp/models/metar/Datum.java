package com.imene.aeroportsapp.models.metar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Datum implements Serializable {


    //pression
    @SerializedName("barometer")
    @Expose
    public Barometer barometer;

    @SerializedName("clouds")
    @Expose
    public List<Cloud> clouds;

    @SerializedName("dewpoint")
    @Expose
    public Dewpoint dewpoint;


    @SerializedName("elevation")
    @Expose
    public Elevation elevation;

    @SerializedName("flight_category")
    @Expose
    public String flight_category;

    @SerializedName("humidity")
    @Expose
    public Humidity humidity;

    @SerializedName("icao")
    @Expose
    public String icao;

    @SerializedName("observed")
    @Expose
    public String observed;

    @SerializedName("raw_text")
    @Expose
    public String raw_text;

    @SerializedName("station")
    @Expose
    public Station station;

    @SerializedName("temperature")
    @Expose
    public Temperature temperature;

    @SerializedName("visibility")
    @Expose
    public Visibility visibility;

    @SerializedName("wind")
    @Expose
    public Wind wind;

    @SerializedName("windchill")
    @Expose
    public Windchill windchill;

    public Barometer getBarometer() {
        return barometer;
    }

    public void setBarometer(Barometer barometer) {
        this.barometer = barometer;
    }

    public List<Cloud> getClouds() {
        return clouds;
    }

    public void setClouds(List<Cloud> clouds) {
        this.clouds = clouds;
    }

    public Dewpoint getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(Dewpoint dewpoint) {
        this.dewpoint = dewpoint;
    }

    public Elevation getElevation() {
        return elevation;
    }

    public void setElevation(Elevation elevation) {
        this.elevation = elevation;
    }

    public String getFlight_category() {
        return flight_category;
    }

    public void setFlight_category(String flight_category) {
        this.flight_category = flight_category;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getObserved() {
        return observed;
    }

    public void setObserved(String observed) {
        this.observed = observed;
    }

    public String getRaw_text() {
        return raw_text;
    }

    public void setRaw_text(String raw_text) {
        this.raw_text = raw_text;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
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

    public Windchill getWindchill() {
        return windchill;
    }

    public void setWindchill(Windchill windchill) {
        this.windchill = windchill;
    }
}
