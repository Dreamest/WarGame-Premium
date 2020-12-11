package com.dreamest.wargame_premium.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dreamest.wargame_premium.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {

    private GoogleMap googleMap;
    private MapView map_MAP_gmap;
    private final int ZOOM = 10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        map_MAP_gmap = view.findViewById(R.id.map_MAP_gmap);
        map_MAP_gmap.onCreate(savedInstanceState);
        map_MAP_gmap.onResume();

        test();


        return view;
    }

    private void test() {


        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void addMarker(String title, LatLng position) {
        map_MAP_gmap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.addMarker(new MarkerOptions().position(position).title(title));
            }
        });

    }

    public void focusOn(LatLng position) {
        map_MAP_gmap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                CameraPosition cameraPosition = new CameraPosition.Builder().target(position).zoom(ZOOM).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

    }
}