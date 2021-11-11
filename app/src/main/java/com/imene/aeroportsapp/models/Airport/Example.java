package com.imene.aeroportsapp.models.Airport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("results")
    @Expose
    private Integer results;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

}