package com.dreamest.wargame_premium.activities;

import android.os.Bundle;

import com.dreamest.wargame_premium.MapCallback;
import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.fragments.MapFragment;
import com.dreamest.wargame_premium.fragments.TopTenFragment;
import com.google.android.gms.maps.model.LatLng;

public class LeaderboardsActivity extends BaseActivity implements MapCallback {
    private TopTenFragment topTenFragment;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        mapFragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.leaderboards_lay_map, mapFragment).commit();

        topTenFragment = new TopTenFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.leaderboards_lay_topTen, topTenFragment).commit();
        topTenFragment.setMapCallback(this);

    }

    @Override
    public void addMarker(String title, LatLng position) {
        mapFragment.addMarker(title, position);
    }

    @Override
    public void focusPosition(LatLng position) {
        mapFragment.focusOn(position);
    }
}