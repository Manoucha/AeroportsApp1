package com.imene.aeroportsapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
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


public class SearchFragment extends Fragment  {


    Button btnsearch,addbtn;
    EditText editText;
    final Gson gson = new Gson();

    //array of oaci

    RecyclerViewAdapter mAdapter;
    ArrayList<String> oaciListe;

    RecyclerView recyclerView;
    ArrayList<String> stringArrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        btnsearch = view.findViewById(R.id.btnSearch);
        addbtn = view.findViewById(R.id.addbtn);
        editText = view.findViewById(R.id.oaciEt);



        // set up the RecyclerView
         recyclerView = view.findViewById(R.id.recyclerViewoaci);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        enableSwipeToDeleteAndUndo();

        mAdapter = new RecyclerViewAdapter(stringArrayList);



        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String icao = editText.getText().toString();
                stringArrayList.add(icao);
                editText.getText().clear();

                recyclerView.setAdapter(mAdapter);

                if(stringArrayList.size()==0)
                    btnsearch.setVisibility(View.INVISIBLE);
                else
                    btnsearch.setVisibility(View.VISIBLE);

            }
        });
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String icao = editText.getText().toString();


                AeroportService service = new AeroportService();


                service.searchAeroportmetar(icao, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {

                            System.out.println("name : "+icao);

                            Data data = gson.fromJson(response.body().string(), Data.class);

                            List<Datum> liste = new ArrayList<>();


                                for (Datum d : data.getData())
                                {
                                    liste.add(d);
                                    Log.d("stattion",d.getStation().name);
                                    Log.d("stattion geometry ",d.getStation().getGeometry().getCoordinates().toString());

                                }




                            ((MyApplication) getActivity().getApplication()).setListe(liste);


                            System.out.println("metar: "+data.getData().toString());


                            Fragment myFragment = new RecylerViewMapFragment();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer ,  myFragment).commit();


                        }

                    }
                });

                service.searchAeroporttaf(icao, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {


                            DataTaf data = gson.fromJson(response.body().string(), DataTaf.class);

                            List<DatumTaf> liste = new ArrayList<>();


                            for (DatumTaf d : data.getData())
                            {
                                liste.add(d);
                                Log.d("visibility ",d.getForecast().get(0).getVisibility().toString());
                                Log.d("stattion from taf ",d.getStation().getName());

                            }

                            System.out.println("taf : "+data.getData().toString());


                            Fragment myFragment = new RecylerViewMapFragment();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer ,  myFragment).commit();


                        }

                    }
                });

            }
        });



        return  view;
    }
    @Override
    public void onResume() {
        super.onResume();


    }
    private void populateRecyclerView() {
        stringArrayList.add("Item 1");
        stringArrayList.add("Item 2");
        stringArrayList.add("Item 3");
        stringArrayList.add("Item 4");
        stringArrayList.add("Item 5");
        stringArrayList.add("Item 6");
        stringArrayList.add("Item 7");
        stringArrayList.add("Item 8");
        stringArrayList.add("Item 9");
        stringArrayList.add("Item 10");

        mAdapter = new RecyclerViewAdapter(stringArrayList);
        recyclerView.setAdapter(mAdapter);


    }
    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final String item = mAdapter.getData().get(position);

                mAdapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(getView(), "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }


}