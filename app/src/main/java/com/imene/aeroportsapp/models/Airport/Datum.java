package com.imene.aeroportsapp.models.Airport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.imene.aeroportsapp.models.taf.DatumTaf;

import java.util.List;

public class Datum {

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("iata")
    @Expose
    private String iata;
    @SerializedName("icao")
    @Expose
    private String icao;
    @SerializedName("status")
    @Expose
    private String status;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}