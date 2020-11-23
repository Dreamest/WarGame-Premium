package com.dreamest.wargame_premium;

import android.os.Bundle;

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