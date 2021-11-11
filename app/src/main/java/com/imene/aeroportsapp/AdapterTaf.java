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
        TextView tvtafperiod = holder.tvtafperiod;
        TextView tvtaftype = holder.tvtaftype;
        TextView tvtafwind = holder.tvtafwind;
        TextView tvtafvisibility = holder.tvtafvisibility;
        TextView tvtafceiling = holder.tvtafceiling;
        TextView tvtafclouds = holder.tvtafclouds;
        TextView tvtafweather = holder.tvtafweather;

        LinearLayout lltaftype = holder.lltaftype;
        LinearLayout lltafwinds = holder.lltafwinds;

        LinearLayout lltafvisibility = holder.lltafvisibility;
        LinearLayout lltafceiling = holder.lltafceiling;
        LinearLayout lltafclouds = holder.lltafclouds;
        LinearLayout lltafweather = holder.lltafweather;



        tvtafperiod.setText(forcast.getTimestamp().getFrom()+" TO "+forcast.getTimestamp().getTo());

        if(forcast.getChange() != null )
        {
            tvtaftype.setText(forcast.getChange().getIndicator().getDesc());

        }else if (forcast.getChange() == null )
        {
            lltaftype.setVisibility(View.GONE);
        }

        if(forcast.getWind() != null )
        {
            tvtafwind.setText(forcast.getWind().getDegrees()+" AT "+forcast.getWind().getSpeed_mph()+" MPH ("+forcast.getWind().getSpeed_kts()+" knots )");

        }else if (forcast.getWind() == null )
        {
            lltafwinds.setVisibility(View.GONE);
        }

        if(forcast.getVisibility() != null )
        {
            tvtafvisibility.setText(forcast.getVisibility().getMiles()+" ( "+forcast.getVisibility().getMeters()+" m )");

        }else if (forcast.getVisibility() == null )
        {
            lltafvisibility.setVisibility(View.GONE);
        }


        if(forcast.getCeiling() != null )
        {
            tvtafceiling.setText(forcast.getCeiling().getBase_feet_agl()+" feet AGL )");

        }else if (forcast.getCeiling() == null )
        {
            lltafceiling.setVisibility(View.GONE);
        }


        if(forcast.getClouds() == null )
        {
            lltafclouds.setVisibility(View.GONE);


        }else if (forcast.getClouds() != null )
        {
            if(forcast.getClouds().size()!=0)
            tvtafclouds.setText(forcast.getClouds().get(0).getText());
            else
                lltafclouds.setVisibility(View.GONE);


        }

        if(forcast.getConditions() != null )
        {
            tvtafweather.setText(forcast.getConditions().get(0).getText());

        }else if (forcast.getConditions() == null )
        {
            lltafweather.setVisibility(View.GONE);
        }


    }



    @Override
    public int getItemCount() {
        return datumTaf.getForecast().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView tvtafperiod,tvtaftype,tvtafwind,tvtafvisibility,tvtafceiling,tvtafclouds,tvtafweather;
        public LinearLayout lltafwinds,lltafvisibility,lltafceiling,lltafclouds,lltafweather,lltaftype;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

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
            lltaftype = (LinearLayout) itemView.findViewById(R.id.lltaftype);





        }
    }
}
