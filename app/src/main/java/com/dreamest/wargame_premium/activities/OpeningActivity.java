package com.dreamest.wargame_premium.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.game.Leaderboards;
import com.dreamest.wargame_premium.utilities.Utility;
import com.google.gson.Gson;

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

        ensureLeaderboards();

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

    private void ensureLeaderboards() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        String s = settings.getString(Leaderboards.LEADERBOARDS_KEY, Leaderboards.NO_LEADERBOARDS);
        Leaderboards leaderboards;
        Gson gson = new Gson();
        if(s.equals(Leaderboards.NO_LEADERBOARDS))
            leaderboards = new Leaderboards();
        else {
            leaderboards = gson.fromJson(s, Leaderboards.class);
        }
        editor.putString(Leaderboards.LEADERBOARDS_KEY, gson.toJson(leaderboards));
        editor.apply();
    }

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

    @Override
    protected void onResume() {
        super.onResume();
    }


}