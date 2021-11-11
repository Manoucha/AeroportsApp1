package com.imene.aeroportsapp.models;

import com.imene.aeroportsapp.models.taf.DatumTaf;

import java.util.List;

public class Aeroport {


    public List<com.imene.aeroportsapp.models.metar.Datum> metarData;

    public List<DatumTaf> tafData;

    public Aeroport() {
    }

    public List<com.imene.aeroportsapp.models.metar.Datum> getMetarData() {
        return metarData;
    }

    public void setMetarData(List<com.imene.aeroportsapp.models.metar.Datum> metarData) {
        this.metarData = metarData;
    }

    public List<DatumTaf> getTafData() {
        return tafData;
    }

    public void setTafData(List<DatumTaf> tafData) {
        this.tafData = tafData;
    }
}
