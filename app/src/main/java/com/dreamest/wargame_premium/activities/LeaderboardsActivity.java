package com.dreamest.wargame_premium.activities;

import android.os.Bundle;

import com.dreamest.wargame_premium.R;

public class LeaderboardsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        HideUI.hideSystemUI(this); //Credit : https://developer.android.com/training/system-ui/immersive#java
    }



}