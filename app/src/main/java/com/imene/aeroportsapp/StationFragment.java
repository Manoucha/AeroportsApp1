package com.imene.aeroportsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StationFragment extends Fragment {

    int position;
    public StationFragment(int p) {
        // Required empty public constructor
        this.position = p;
    }

    TextView tv ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_station, container, false);
        tv = v.findViewById(R.id.idstation);
        tv.setText("khraaaaa "+position);
        return v;
    }
}