package com.imene.aeroportsapp;

import android.app.Application;

import com.imene.aeroportsapp.models.metar.Datum;
import com.imene.aeroportsapp.models.taf.DatumTaf;

import java.util.List;

public class MyApplication extends Application {

    private List<Datum> liste;
    private List<DatumTaf> listeTaf;

    public List<DatumTaf> getListeTaf() {
        return listeTaf;
    }

    public void setListeTaf(List<DatumTaf> listeTaf) {
        this.listeTaf = listeTaf;
    }

    public List<Datum> getListe() {
        return liste;
    }

    public void setListe(List<Datum> liste) {
        this.liste = liste;
    }
}
