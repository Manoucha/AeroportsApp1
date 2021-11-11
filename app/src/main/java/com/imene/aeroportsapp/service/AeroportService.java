package com.imene.aeroportsapp.service;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AeroportService {

    public AeroportService() {
    }


    OkHttpClient client = new OkHttpClient();


    public void searchAeroportmetar(String icao, Callback callback) {
        Request request = new Request.Builder()
                .url("https://api.checkwx.com/metar/"+icao+"/decoded?x-api-key=49bcf08bee2a4b6fba835769a8")
                .build();

        client.newCall(request).enqueue(callback);


    }

    public void searchAeroporttaf(String icao, Callback callback) {
        Request request = new Request.Builder()
                .url("https://api.checkwx.com/taf/"+icao+"/decoded?x-api-key=49bcf08bee2a4b6fba835769a8")
                .build();

        client.newCall(request).enqueue(callback);


    }

    public void searchAeropor(String icao, Callback callback) {
        Request request = new Request.Builder()
                .url("https://api.checkwx.com/metar/%22"+icao+"%22/decoded?x-api-key=49bcf08bee2a4b6fba835769a8")
                .build();

        client.newCall(request).enqueue(callback);


    }
}
