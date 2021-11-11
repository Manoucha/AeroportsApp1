package com.imene.aeroportsapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
    List<com.imene.aeroportsapp.models.Airport.Datum> listeStations = new List<com.imene.aeroportsapp.models.Airport.Datum>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(@Nullable Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<com.imene.aeroportsapp.models.Airport.Datum> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] ts) {
            return null;
        }

        @Override
        public boolean add(com.imene.aeroportsapp.models.Airport.Datum datum) {
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends com.imene.aeroportsapp.models.Airport.Datum> collection) {
            return false;
        }

        @Override
        public boolean addAll(int i, @NonNull Collection<? extends com.imene.aeroportsapp.models.Airport.Datum> collection) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public com.imene.aeroportsapp.models.Airport.Datum get(int i) {
            return null;
        }

        @Override
        public com.imene.aeroportsapp.models.Airport.Datum set(int i, com.imene.aeroportsapp.models.Airport.Datum datum) {
            return null;
        }

        @Override
        public void add(int i, com.imene.aeroportsapp.models.Airport.Datum datum) {

        }

        @Override
        public com.imene.aeroportsapp.models.Airport.Datum remove(int i) {
            return null;
        }

        @Override
        public int indexOf(@Nullable Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(@Nullable Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<com.imene.aeroportsapp.models.Airport.Datum> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<com.imene.aeroportsapp.models.Airport.Datum> listIterator(int i) {
            return null;
        }

        @NonNull
        @Override
        public List<com.imene.aeroportsapp.models.Airport.Datum> subList(int i, int i1) {
            return null;
        }
    } ;
    final Gson gson = new Gson();

    LinearLayout linearLayoutVFR,laytouWind;

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

        //liste station =

        rvTafs = result.findViewById(R.id.rvTafs);
            listeStations = ((MyApplication) getActivity().getApplication()).getListeStations();
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

       tvIATA.setText(liste.get(position).getStation().getGeometry().getCoordinates()+"");
        tvStatus.setText(liste.get(position).getObserved());
        tvLOCATION.setText(liste.get(position).getStation().getGeometry().getCoordinates().toString());

        //***************** Données Metar

       // linearLayoutVFR,tvVFR,tvTemperature,tvDewpoint,tvpression,tvWind,tvVisibility,tvCloud



        linearLayoutVFR = result.findViewById(R.id.linearLayoutVFR);
        tvVFR = result.findViewById(R.id.tvVFR);

        laytouWind =result.findViewById(R.id.laytouWind);


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
else if (liste.get(position).getFlight_category().equals("LIFR"))
        {
            linearLayoutVFR.setBackgroundColor(Color.MAGENTA);
        }
        else if (liste.get(position).getFlight_category().equals("MVFR"))
        {
            linearLayoutVFR.setBackgroundColor(Color.BLUE);
        }
        if(liste.get(position).getBarometer() != null)
        {
            tvpression.setText(liste.get(position).getBarometer().getHg()+" inches Hg ( "+liste.get(position).getBarometer().getMb()+ " mb )");

        }else {
            tvpression.setVisibility(View.GONE);
        }


        if(liste.get(position).getDewpoint() != null)
        {
            tvDewpoint.setText(liste.get(position).getDewpoint().celsius+" °C"+" ( "+liste.get(position).getDewpoint().fahrenheit+" °F )");

        }else {
            tvDewpoint.setVisibility(View.GONE);
        }

      if(liste.get(position).getTemperature() != null)
      {
          tvTemperature.setText(liste.get(position).getTemperature().celsius+" °C"+" ( "+liste.get(position).getTemperature().fahrenheit+" °F )");

      }else {
          tvTemperature.setVisibility(View.GONE);
      }

        if(liste.get(position).getWind()!=null)
        {
            tvWind.setText(liste.get(position).getWind().getDegrees()+" degrees "+"at "+ liste.get(position).getWind().getSpeed_mph() +" MPH ");

        }
        else
        {
            laytouWind.setVisibility(View.GONE);
        }

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