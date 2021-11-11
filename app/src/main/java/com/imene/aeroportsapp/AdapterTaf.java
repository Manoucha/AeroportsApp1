package com.imene.aeroportsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imene.aeroportsapp.models.metar.Datum;
import com.imene.aeroportsapp.models.taf.DatumTaf;
import com.imene.aeroportsapp.models.taf.Forecast;

import java.util.ArrayList;
import java.util.List;

public class AdapterTaf extends RecyclerView.Adapter<AdapterTaf.ViewHolder> {



    DatumTaf datumTaf ;
        int positioAn;

    public AdapterTaf(DatumTaf df,int positiona) {
            this.datumTaf = df;
            this.positioAn= positiona;
    }

    @NonNull
    @Override
    public AdapterTaf.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.taf_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(AdapterTaf.ViewHolder holder, int position) {
        // Get the data model based on position
        Forecast forcast = datumTaf.getForecast().get(position);

        // Set item views based on your views and data model
        TextView tvtafTxt = holder.tvtafTxt;

        tvtafTxt.setText(datumTaf.getRaw_text());


    }



    @Override
    public int getItemCount() {
        return datumTaf.getForecast().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView tvtafTxt,tvtafperiod,tvtaftype,tvtafwind,tvtafvisibility,tvtafceiling,tvtafclouds,tvtafweather;
        public LinearLayout lltafwinds,lltafvisibility,lltafceiling,lltafclouds,lltafweather;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            tvtafTxt = (TextView) itemView.findViewById(R.id.tvtafTxt);
            tvtafperiod = (TextView) itemView.findViewById(R.id.tvtafperiod);
            tvtaftype = (TextView) itemView.findViewById(R.id.tvtaftype);
            tvtafwind = (TextView) itemView.findViewById(R.id.tvtafwind);
            tvtafvisibility = (TextView) itemView.findViewById(R.id.tvtafvisibility);
            tvtafceiling = (TextView) itemView.findViewById(R.id.tvtafceiling);
            tvtafclouds = (TextView) itemView.findViewById(R.id.tvtafclouds);
            tvtafweather = (TextView) itemView.findViewById(R.id.tvtafweather);

            lltafwinds = (LinearLayout) itemView.findViewById(R.id.lltafwinds);
            lltafvisibility = (LinearLayout) itemView.findViewById(R.id.lltafvisibility);
            lltafceiling = (LinearLayout) itemView.findViewById(R.id.lltafceiling);
            lltafclouds = (LinearLayout) itemView.findViewById(R.id.lltafclouds);
            lltafweather = (LinearLayout) itemView.findViewById(R.id.lltafweather);





        }
    }
}
