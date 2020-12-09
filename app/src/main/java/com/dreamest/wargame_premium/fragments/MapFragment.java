package com.dreamest.wargame_premium.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dreamest.wargame_premium.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MapFragment extends Fragment {

    private GoogleMap googleMap;
    private MapView map_MAP_gmap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        map_MAP_gmap = view.findViewById(R.id.map_MAP_gmap);
        map_MAP_gmap.onCreate(savedInstanceState);
        map_MAP_gmap.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        map_MAP_gmap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

//                // For showing a move to my location button
////                googleMap.setMyLocationEnabled(true);
//
//                // For dropping a marker at a point on the Map
//                LatLng sydney = new LatLng(-34, 151);
//                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
//
//                // For zooming automatically to the location of the marker
//                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
//                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        map_MAP_gmap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map_MAP_gmap.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        map_MAP_gmap.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map_MAP_gmap.onLowMemory();
    }

}