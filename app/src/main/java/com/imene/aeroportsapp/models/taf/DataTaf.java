package com.imene.aeroportsapp.models.taf;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class DataTaf {



        @SerializedName("data")
        @Expose
        private List<DatumTaf> data = null;
        @SerializedName("results")
        @Expose
        private Integer results;

        public List<DatumTaf> getData() {
            return data;
        }

        public void setData(List<DatumTaf> data) {
            this.data = data;
        }

        public Integer getResults() {
            return results;
        }

        public void setResults(Integer results) {
            this.results = results;
        }


}
