package com.dreamest.wargame_premium.activities;

import android.os.Bundle;

import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.fragments.MapFragment;
import com.dreamest.wargame_premium.fragments.TopTenFragment;

public class LeaderboardsActivity extends BaseActivity {
    private TopTenFragment topTenFragment;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        topTenFragment = new TopTenFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.leaderboards_lay_topTen, topTenFragment).commit();

        mapFragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.leaderboards_lay_map, mapFragment).commit();
    }

}