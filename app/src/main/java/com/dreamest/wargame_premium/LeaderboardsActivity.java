package com.dreamest.wargame_premium;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class LeaderboardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HideUI.hideSystemUI(this); //Credit : https://developer.android.com/training/system-ui/immersive#java
    }



}