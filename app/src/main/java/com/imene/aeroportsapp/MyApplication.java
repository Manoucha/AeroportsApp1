package com.imene.aeroportsapp;

import android.app.Application;

import com.imene.aeroportsapp.models.metar.Datum;

import java.util.List;

public class MyApplication extends Application {

    private List<Datum> liste;

    public List<Datum> getListe() {
        return liste;
    }

    public void setListe(List<Datum> liste) {
        this.liste = liste;
    }
}
