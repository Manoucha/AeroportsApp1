package com.imene.aeroportsapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.imene.aeroportsapp.models.metar.Data;
import com.imene.aeroportsapp.models.metar.Datum;
import com.imene.aeroportsapp.models.taf.DataTaf;
import com.imene.aeroportsapp.models.taf.DatumTaf;
import com.imene.aeroportsapp.service.AeroportService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PageFragment extends Fragment {
    // 1 - Create keys for our Bundle
    private static final String KEY_POSITION="position";
    private static final String KEY_COLOR="color";

    //recyclerview tafs

    RecyclerView rvTafs ;
    //pager dans le fragment
    FrameLayout simpleFrameLayout;
    TextView tv,tvCountry,tvICAO,tvIATA,tvStatus,tvLOCATION,tvVFR,tvTemperature,tvDewpoint,tvpression,tvWind,tvVisibility,tvCloud,tvTafTextG;
    List<Datum> liste;
    final Gson gson = new Gson();

    LinearLayout linearLayoutVFR;

    public PageFragment() { }


    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static PageFragment newInstance(int position, int color) {

        // 2.1 Create new fragment
        PageFragment frag = new PageFragment();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);
        frag.setArguments(args);

        return(frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 3 - Get layout of PageFragment
        View result = inflater.inflate(R.layout.fragment_page, container, false);
        liste = ((MyApplication) getActivity().getApplication()).getListe();
        //RV TAFS

        rvTafs = result.findViewById(R.id.rvTafs);

        // 4 - Get widgets from layout and serialise it
        ScrollView rootView= (ScrollView) result.findViewById(R.id.fragment_page_rootview);

        // 5 - Get data from Bundle (created in method newInstance)
        int position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);



        //***********************get taf ************************

        AeroportService service = new AeroportService();


        //***********************************************



        // 6 - Update widgets with it
        rootView.setBackgroundColor(color);
        tv = result.findViewById(R.id.textViexTest);

        tvCountry = result.findViewById(R.id.tvCountry);
        tvICAO = result.findViewById(R.id.tvICAO);
        tvIATA = result.findViewById(R.id.tvIATA);
        tvStatus = result.findViewById(R.id.tvStatus);
        tvLOCATION = result.findViewById(R.id.tvLOCATION);


        tv.setText(liste.get(position).getStation().getName());

        //***********Données Station
        tvCountry.setText(liste.get(position).getStation().getLocation());
        tvICAO.setText(liste.get(position).getIcao());
        tvIATA.setText("pas encore");
        tvStatus.setText("pas encore");
        tvLOCATION.setText(liste.get(position).getStation().getGeometry().getCoordinates().toString());

        //***************** Données Metar

       // linearLayoutVFR,tvVFR,tvTemperature,tvDewpoint,tvpression,tvWind,tvVisibility,tvCloud



        linearLayoutVFR = result.findViewById(R.id.linearLayoutVFR);
        tvVFR = result.findViewById(R.id.tvVFR);




        tvTemperature = result.findViewById(R.id.tvTemperature);
        tvDewpoint = result.findViewById(R.id.tvDewpoint);
        tvpression = result.findViewById(R.id.tvpression);
        tvWind = result.findViewById(R.id.tvWind);
        tvVisibility = result.findViewById(R.id.tvVisibility);
        tvCloud = result.findViewById(R.id.tvCloud);
        tvTafTextG = result.findViewById(R.id.tvTafTextG);


        tvTafTextG.setText(((MyApplication) getActivity().getApplication()).getListeTaf().get(position).getRaw_text());
        tvVFR.setText(liste.get(position).getFlight_category());
        if(liste.get(position).getFlight_category().equals("MVFR"))
        {
            linearLayoutVFR.setBackgroundColor(Color.RED);
        }
        tvTemperature.setText(liste.get(position).getTemperature().celsius+" °C"+" ( "+liste.get(position).getTemperature().fahrenheit+" °F )");
        tvDewpoint.setText(liste.get(position).getDewpoint().celsius+" °C"+" ( "+liste.get(position).getDewpoint().fahrenheit+" °F )");
        tvpression.setText(liste.get(position).getBarometer().getHg()+" inches Hg ( "+liste.get(position).getBarometer().getMb()+ " mb )");
        tvWind.setText(liste.get(position).getWind().getDegrees()+" degrees "+"at "+ liste.get(position).getWind().getSpeed_mph() +" MPH ");
        //30 sm ( 48 km)

        String beforeFirstDot = liste.get(position).getVisibility().getMeters().split("\\,")[0];

        tvVisibility.setText(liste.get(position).getVisibility().getMiles()+" sm ( "+beforeFirstDot+" km )");

        //few clouds at 200 feet AGL

        tvCloud.setText(liste.get(position).getClouds().get(0).getCode()+" clouds at "+liste.get(position).getClouds().get(0).getBase_feet_agl()+" feet AGL");


        //***********************************************



        AdapterTaf adapter = new AdapterTaf(((MyApplication) getActivity().getApplication()).getListeTaf().get(position),position);
        // Attach the adapter to the recyclerview to populate items
        rvTafs.setAdapter(adapter);
        // Set layout manager to position the items
        rvTafs.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.e(getClass().getSimpleName(), "onCreateView called for fragment number "+position);

        return result;
    }

}