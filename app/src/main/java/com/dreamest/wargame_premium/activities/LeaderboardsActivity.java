package com.dreamest.wargame_premium.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dreamest.wargame_premium.MapCallback;
import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.fragments.MapFragment;
import com.dreamest.wargame_premium.fragments.TopTenFragment;
import com.dreamest.wargame_premium.utilities.Utility;
import com.google.android.gms.maps.model.LatLng;

public class LeaderboardsActivity extends BaseActivity implements MapCallback {
    private TopTenFragment topTenFragment;
    private MapFragment mapFragment;
    private Button leaderboards_BTN_return;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        findViews();

        mapFragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.leaderboards_lay_map, mapFragment).commit();

        topTenFragment = new TopTenFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.leaderboards_lay_topTen, topTenFragment).commit();
        topTenFragment.setMapCallback(this);

        leaderboards_BTN_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(LeaderboardsActivity.this, R.raw.snd_button_click);
                close();
            }
        });
    }

    private void findViews() {
        leaderboards_BTN_return = findViewById(R.id.leaderboards_BTN_return);
    }

    private void close() {
        finish();
    }

    @Override
    public void addMarker(String title, LatLng position) {
        mapFragment.addMarker(title, position);
    }

    @Override
    public void focusPosition(LatLng position) {
        Utility.playSound(this, R.raw.snd_globe);
        mapFragment.focusOn(position);
    }
}