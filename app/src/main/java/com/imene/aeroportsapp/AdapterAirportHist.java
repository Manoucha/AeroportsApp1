package com.imene.aeroportsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imene.aeroportsapp.models.metar.Datum;

import java.util.List;

public class AdapterAirportHist extends RecyclerView.Adapter<AdapterAirportHist.ViewHolder> {


    List<Datum> liste ;

    public AdapterAirportHist(List<Datum> liste) {
        this.liste = liste;
    }

    @NonNull
    @Override
    public AdapterAirportHist.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.airport, parent, false);

        // Return a new holder instance
        AdapterAirportHist.ViewHolder viewHolder = new AdapterAirportHist.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAirportHist.ViewHolder holder, int position) {
        Datum d = liste.get(position);

        TextView tvname = holder.tvname;
        TextView tvlocation = holder.tvlocation;

        tvname.setText(d.getStation().getName());
        tvlocation.setText(d.getStation().getLocation());

    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {

        public TextView tvname,tvlocation;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            tvname = (TextView) itemView.findViewById(R.id.nameAirport);
            tvlocation = (TextView) itemView.findViewById(R.id.locationAirport);



        }

    }
}
