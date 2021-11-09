package com.imene.aeroportsapp.models;

import com.imene.aeroportsapp.models.metar.Datum;
import com.imene.aeroportsapp.models.taf.Taf;

import java.util.List;

public class Aeroport {


    public List<Datum> metarData;

    public List<Taf> tafData;

    public Aeroport() {
    }

    public List<Datum> getMetarData() {
        return metarData;
    }

    public void setMetarData(List<Datum> metarData) {
        this.metarData = metarData;
    }

    public List<Taf> getTafData() {
        return tafData;
    }

    public void setTafData(List<Taf> tafData) {
        this.tafData = tafData;
    }
}
