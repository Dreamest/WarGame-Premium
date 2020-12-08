package com.dreamest.wargame_premium.activities;

import android.os.Bundle;

import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.fragments.TopTenFragment;

public class LeaderboardsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        TopTenFragment topTenFragment = new TopTenFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.leaderboards_lay_topTen, topTenFragment).commit();
    }

}