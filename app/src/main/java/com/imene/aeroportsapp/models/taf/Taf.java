package com.imene.aeroportsapp.models.taf;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Taf {

    @SerializedName("forecast")
    @Expose
    public List<Forecast> forecast;

    @SerializedName("icao")
    @Expose
    public String icao;

    @SerializedName("raw_text")
    @Expose
    public String raw_text;

    @SerializedName("station")
    @Expose
    public Station station;


    @SerializedName("timestamp")
    @Expose
    public Timestamp timestamp;

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
