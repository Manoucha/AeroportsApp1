package com.imene.aeroportsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_main, container, false);
        // 1 - Get ViewPager from layout
        ViewPager pager = (ViewPager)v.findViewById(R.id.activity_main_viewpager);

        pager.setAdapter(new PageAdapter(getActivity().getSupportFragmentManager(), getResources().getIntArray(R.array.colorPagesViewPager),((MyApplication) getActivity().getApplication()).getListe()) {
        });
        return v;
    }


}