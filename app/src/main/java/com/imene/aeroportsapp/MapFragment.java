package com.imene.aeroportsapp;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imene.aeroportsapp.models.metar.Datum;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.maps.Style;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import java.util.ArrayList;
import java.util.List;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;

public class MapFragment extends Fragment {



    private static final String SOURCE_ID = "SOURCE_ID";
    private static final String ICON_ID = "ICON_ID";
    private static final String LAYER_ID = "LAYER_ID";
    private static final String SYMBOL_ICON_ID = "SYMBOL_ICON_ID";

    private MapView mapView;

    List<Datum> liste;

    ArrayList<LatLng> coordinates = new ArrayList();
    List<Feature> symbolLayerIconFeatureList = new ArrayList<>();
    private FeatureCollection featureCollection;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Mapbox.getInstance(getActivity(), getString(R.string.mapbox_access_token));

        View v = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);


        liste = ((MyApplication) getActivity().getApplication()).getListe();
        for(Datum d : liste)
        {
            coordinates.add(new LatLng(d.getStation().geometry.coordinates.get(1),d.getStation().geometry.coordinates.get(0)));

            symbolLayerIconFeatureList.add(Feature.fromGeometry(
                    Point.fromLngLat(d.getStation().geometry.coordinates.get(0), d.getStation().geometry.coordinates.get(1))));
        }



        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {



                mapboxMap.setStyle(Style.SATELLITE_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {


                        featureCollection = FeatureCollection.fromFeatures(symbolLayerIconFeatureList);

                        initMarkerIcons(style);
                        //mapboxMap.getMarkers().get(0).getPosition();

                        mapboxMap.setCameraPosition(new CameraPosition.Builder()
                                .target(coordinates.get(0))
                                .zoom(10)
                                .build());

                    }
                });


            }
        });

        return  v;
    }


    private void initFeatureCollection() {
        List<Feature> featureList = new ArrayList<>();
        if (featureCollection != null) {
            for (LatLng latLng : coordinates) {
                featureList.add(Feature.fromGeometry(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude())));
            }
            featureCollection = FeatureCollection.fromFeatures(featureList);
        }
    }

    private void initMarkerIcons(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addImage(SYMBOL_ICON_ID, BitmapFactory.decodeResource(
                getContext().getResources(),  R.drawable.mapbox_marker_icon_default));

        loadedMapStyle.addSource(new GeoJsonSource(SOURCE_ID, featureCollection));
        loadedMapStyle.addLayer(new SymbolLayer(LAYER_ID, SOURCE_ID).withProperties(
                iconImage(SYMBOL_ICON_ID),
                iconAllowOverlap(true),
                iconOffset(new Float[] {0f, -4f})
        ));
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}