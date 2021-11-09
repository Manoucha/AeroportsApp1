package com.imene.aeroportsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imene.aeroportsapp.models.metar.Data;
import com.imene.aeroportsapp.models.metar.Datum;
import com.imene.aeroportsapp.service.AeroportService;
import com.paulrybitskyi.persistentsearchview.PersistentSearchView;
import com.paulrybitskyi.persistentsearchview.listeners.OnSearchConfirmedListener;
import com.paulrybitskyi.persistentsearchview.utils.VoiceRecognitionDelegate;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class SearchFragment extends Fragment {


    Button btnsearch;
    EditText editText;
    final Gson gson = new Gson();
    PersistentSearchView persistentSearchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        btnsearch = view.findViewById(R.id.btnSearch);
        editText = view.findViewById(R.id.oaciEt);
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


                            List<Datum> liste = data.getData();



                            for(Datum station:liste)
                            {
                                Log.d("stattion",station.getStation().name);
                                Log.d("stattion geometry ",station.getStation().getGeometry().getCoordinates().toString());

                                liste.add(station);

                            }

                            ((MyApplication) getActivity().getApplication()).setListe(liste);


                            System.out.println("metar: "+data.getData().toString());


                            Fragment myFragment = new RecylerViewMapFragment();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer ,  myFragment).commit();


                        }

                    }
                });

            }
        });


        //persistant view of search

        persistentSearchView = view.findViewById(R.id.persistentSearchView);
        persistentSearchView.setOnLeftBtnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Handle the left button click
            }

        });

        persistentSearchView.setOnClearInputBtnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Handle the clear input button click
            }

        });

        // Setting a delegate for the voice recognition input
        persistentSearchView.setVoiceRecognitionDelegate(new VoiceRecognitionDelegate(this));

        persistentSearchView.setOnSearchConfirmedListener(new OnSearchConfirmedListener() {

            @Override
            public void onSearchConfirmed(PersistentSearchView searchView, String query) {
                // Handle a search confirmation. This is the place where you'd
                // want to perform a search against your data provider.
                System.out.println("mi clicked here ");

            }

        });

        // Disabling the suggestions since they are unused in
        // the simple implementation
        persistentSearchView.setSuggestionsDisabled(false);
        return  view;
    }
    @Override
    public void onResume() {
        super.onResume();


    }

}