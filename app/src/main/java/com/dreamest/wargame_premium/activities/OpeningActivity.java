package com.dreamest.wargame_premium.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.utilities.Utility;

public class OpeningActivity extends BaseActivity {
    private Button opening_BTN_play;
    private Button opening_BTN_settings;
    private Button opening_BTN_leaderboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        opening_BTN_play = findViewById(R.id.opening_BTN_play);
        opening_BTN_settings = findViewById(R.id.opening_BTN_settings);
        opening_BTN_leaderboard = findViewById(R.id.opening_BTN_leaderboard);


        //Enable these lines to clear data from the app.
//        Log.d("dddd", "WARNING - DATA REMOVER ACTIVE");
//        MySharedPreferences.getMsp().resetData();


        opening_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(OpeningActivity.this, R.raw.snd_button_click);
                startGame();
            }
        });

        opening_BTN_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(OpeningActivity.this, R.raw.snd_button_click);
                openSettings();
            }
        });

        opening_BTN_leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(OpeningActivity.this, R.raw.snd_button_click);
                openLeaderboards();
            }
        });
    }

//    private void ensureLeaderboards() {// TODO: 12/8/20 check if this function is needed.
//        Leaderboards leaderboards = (Leaderboards) MySharedPreferences.getMsp().getObject(MySharedPreferences.KEYS.LEADERBOARDS_KEY, new Leaderboards());
//        MySharedPreferences.getMsp().putObject(MySharedPreferences.KEYS.LEADERBOARDS_KEY, leaderboards);
//    }

    private void openLeaderboards() {
        Intent myIntent = new Intent(this, LeaderboardsActivity.class);
        startActivity(myIntent);
    }

    private void openSettings() {
        Intent myIntent = new Intent(this, SettingsActivity.class);
        startActivity(myIntent);
    }

    private void startGame() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}