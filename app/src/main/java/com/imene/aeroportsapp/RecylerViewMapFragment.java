package com.imene.aeroportsapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;

public class RecylerViewMapFragment extends Fragment implements OnMapReadyCallback{

    private static final String SYMBOL_ICON_ID = "SYMBOL_ICON_ID";
    private static final String SOURCE_ID = "SOURCE_ID";
    private static final String LAYER_ID = "LAYER_ID";
    private static final String ICON_ID = "ICON_ID";

    public MapboxMap mapboxMap;
    private MapView mapView;
    private FeatureCollection featureCollection;

    ArrayList<LatLng> coordinates = new ArrayList();
    List<Feature> symbolLayerIconFeatureList = new ArrayList<>();
    List<Datum> liste;
    View v;


    public static Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        Mapbox.getInstance(getActivity(), getString(R.string.mapbox_access_token));

        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_recyler_view_map, container, false);
        Mapbox.getInstance(getContext(), getString(R.string.mapbox_access_token));

// This contains the MapView in XML and needs to be called after the access token is configured.
            //get the liste
        liste = ((MyApplication) getActivity().getApplication()).getListe();

        Log.d("liste: ", String.valueOf(liste.size()));
        //la 2eme c la latitude
        // Initialize the map view
        mapView = v.findViewById(R.id.mapView1);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        context = getContext();

        for(Datum d : liste)
        {
            coordinates.add(new LatLng(d.getStation().geometry.coordinates.get(1),d.getStation().geometry.coordinates.get(0)));
            symbolLayerIconFeatureList.add(Feature.fromGeometry(
                    Point.fromLngLat(d.getStation().geometry.coordinates.get(0), d.getStation().geometry.coordinates.get(1))));
        }

        return v;
    }

    public void SeemoreFn(View v)
    {
        Toast.makeText(getContext(), "Clicked on Button", Toast.LENGTH_LONG).show();
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
    private void initRecyclerView() {
        RecyclerView recyclerView = v.findViewById(R.id.rv_on_top_of_map);
        LocationRecyclerViewAdapter locationAdapter =
                new LocationRecyclerViewAdapter(createRecyclerViewLocations(), mapboxMap);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(locationAdapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }
    private void initMarkerIcons(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addImage(SYMBOL_ICON_ID, BitmapFactory.decodeResource(
                getContext().getResources(), R.drawable.mapbox_marker_icon_default));
        loadedMapStyle.addSource(new GeoJsonSource(SOURCE_ID, featureCollection));
        loadedMapStyle.addLayer(new SymbolLayer(LAYER_ID, SOURCE_ID).withProperties(
                iconImage(SYMBOL_ICON_ID),
                iconAllowOverlap(true),
                iconOffset(new Float[] {0f, -4f})
        ));
    }
    private List<SingleRecyclerViewLocation> createRecyclerViewLocations() {
        ArrayList<SingleRecyclerViewLocation> locationList = new ArrayList<>();
        for (int x = 0; x < liste.size(); x++) {
            SingleRecyclerViewLocation singleLocation = new SingleRecyclerViewLocation();
            singleLocation.setName(liste.get(x).getStation().getName());
            singleLocation.setLocation(liste.get(x).getStation().getLocation());
            singleLocation.setCoord(liste.get(x).getStation().getGeometry().getCoordinates().toString());


                    new Random().nextInt(coordinates.size());
            singleLocation.setLocationCoordinates(coordinates.get(x));
            locationList.add(singleLocation);
        }
        return locationList;
    }
    // Add the mapView lifecycle to the activity's lifecycle methods
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
        super.onStart();

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

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        mapboxMap.setStyle((new Style.Builder().fromUri("mapbox://styles/mapbox/cjf4m44iw0uza2spb3q0a7s41")

// Add the SymbolLayer icon image to the map style
                .withImage(ICON_ID, BitmapFactory.decodeResource(
                        getActivity().getResources(), R.drawable.mapbox_marker_icon_default))

// Adding a GeoJson source for the SymbolLayer icons.
                .withSource(new GeoJsonSource(SOURCE_ID,
                        FeatureCollection.fromFeatures(symbolLayerIconFeatureList)))


// Adding the actual SymbolLayer to the map style. An offset is added that the bottom of the red
// marker icon gets fixed to the coordinate, rather than the middle of the icon being fixed to
// the coordinate point. This is offset is not always needed and is dependent on the image
// that you use for the SymbolLayer icon.
                .withLayer(new SymbolLayer(LAYER_ID, SOURCE_ID)
                        .withProperties(
                                iconImage(ICON_ID),
                                iconAllowOverlap(true),
                                iconIgnorePlacement(true)
                        )
                )), new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {

// Map is set up and the style has loaded. Now you can add additional data or make other map adjustments.
                initFeatureCollection();
               // initMarkerIcons(style);
                initRecyclerView();
                Toast.makeText(getActivity(), R.string.toast_instruction, Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * POJO model class for a single location in the recyclerview
     */
    class SingleRecyclerViewLocation {

        private String name;
        private String bedInfo;
        private LatLng locationCoordinates;
        private  String location;
        private  String coord;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCoord() {
            return coord;
        }

        public void setCoord(String coord) {
            this.coord = coord;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBedInfo() {
            return bedInfo;
        }

        public void setBedInfo(String bedInfo) {
            this.bedInfo = bedInfo;
        }

        public LatLng getLocationCoordinates() {
            return locationCoordinates;
        }

        public void setLocationCoordinates(LatLng locationCoordinates) {
            this.locationCoordinates = locationCoordinates;
        }
    }

    static class LocationRecyclerViewAdapter extends
            RecyclerView.Adapter<LocationRecyclerViewAdapter.MyViewHolder> {

        private List<SingleRecyclerViewLocation> locationList;
        private MapboxMap map;

        public LocationRecyclerViewAdapter(List<SingleRecyclerViewLocation> locationList, MapboxMap mapBoxMap) {
            this.locationList = locationList;
            this.map = mapBoxMap;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.rv_on_top_of_map_card, parent, false);


            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            SingleRecyclerViewLocation singleRecyclerViewLocation = locationList.get(position);
            holder.name.setText(singleRecyclerViewLocation.getName());
            holder.Tvlocation.setText(singleRecyclerViewLocation.getLocation());
            holder.TvCoordinates.setText(singleRecyclerViewLocation.getCoord());

            holder.btn_seemore.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    System.out.println("heeeeeeeeee"+position);

                    MainActivity activity = (MainActivity)view.getContext();
                    FragmentManager manager = activity.getSupportFragmentManager();

                    MainFragment nextFrag= new MainFragment();

                    manager.beginTransaction()
                            .replace(R.id.contentContainer, nextFrag, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }
            });

            holder.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    LatLng selectedLocationLatLng = locationList.get(position).getLocationCoordinates();
                    CameraPosition newCameraPosition = new CameraPosition.Builder()
                            .target(selectedLocationLatLng)
                            .zoom(12)
                            .build();

                    map.easeCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition));



                }
            });
        }

        @Override
        public int getItemCount() {
            return locationList.size();
        }

        static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView name,Tvlocation,TvCoordinates;
            CardView singleCard;
            ItemClickListener clickListener,clickListener1;
            Button btn_seemore;
            MyViewHolder(View view) {
                super(view);
                name = view.findViewById(R.id.location_title_tv);
                Tvlocation = view.findViewById(R.id.Tvlocation);
                TvCoordinates = view.findViewById(R.id.Tvcoordinates);
                singleCard = view.findViewById(R.id.single_location_cardview);
                btn_seemore = view.findViewById(R.id.btn_seemore);
                singleCard.setOnClickListener(this);
            }

            public void setClickListener(ItemClickListener itemClickListener) {
                this.clickListener = itemClickListener;
            }



            @Override
            public void onClick(View view) {
                clickListener.onClick(view, getLayoutPosition());
            }

        }
    }


    public interface ItemClickListener {
        void onClick(View view, int position);
    }

}