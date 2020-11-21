package com.dreamest.wargame_premium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class OpeningActivity extends AppCompatActivity {
    private Button opening_BTN_play;
    private Button opening_BTN_settings;
    private Button opening_BTN_leaderboard;
    private String leftName, rightName;
    private HideUI hideUI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        hideUI = new HideUI();

        opening_BTN_play = findViewById(R.id.opening_BTN_play);
        opening_BTN_settings = findViewById(R.id.opening_BTN_settings);
        opening_BTN_leaderboard = findViewById(R.id.opening_BTN_leaderboard);


        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        opening_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startGame();
                String s = settings.getString("P1", "Player One");
                Log.d("dddd", s);

            }
        });

        opening_BTN_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAvatars();
            }
        });

        opening_BTN_leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeaderboards();
            }
        });
    }

    private void openLeaderboards() {
        Intent myIntent = new Intent(this, LeaderboardsActivity.class);
        startActivity(myIntent);
    }

    private void changeAvatars() {
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
        hideUI.hideSystemUI(this);
    }
}