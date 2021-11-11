package com.imene.aeroportsapp;

import android.app.Application;

import com.imene.aeroportsapp.models.metar.Datum;
import com.imene.aeroportsapp.models.taf.DatumTaf;

import java.util.List;

public class MyApplication extends Application {

    private List<Datum> liste;
    private List<DatumTaf> listeTaf;
    private  List<com.imene.aeroportsapp.models.Airport.Datum> listeStations;

    public List<DatumTaf> getListeTaf() {
        return listeTaf;
    }

    public void setListeTaf(List<DatumTaf> listeTaf) {
        this.listeTaf = listeTaf;
    }

    public List<com.imene.aeroportsapp.models.Airport.Datum> getListeStations() {
        return listeStations;
    }

    public void setListeStations(List<com.imene.aeroportsapp.models.Airport.Datum> listeStations) {
        this.listeStations = listeStations;
    }

    public List<Datum> getListe() {
        return liste;
    }

    public void setListe(List<Datum> liste) {
        this.liste = liste;
    }
}
