package com.imene.aeroportsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HistoriqueFragment extends Fragment {



    RecyclerView rvHist ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_historique, container, false);
        rvHist = view.findViewById(R.id.rvHist);

        AdapterAirportHist adapter = new AdapterAirportHist(((MyApplication) getActivity().getApplication()).getListe());

        // Attach the adapter to the recyclerview to populate items
        rvHist.setAdapter(adapter);

        // Set layout manager to position the items
        rvHist.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}