package com.dreamest.wargame_premium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class OpeningActivity extends AppCompatActivity {
    private Button opening_BTN_play;
    private Button opening_BTN_settings;
    private Button opening_BTN_leaderboard;
    private String leftName, rightName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        opening_BTN_play = findViewById(R.id.opening_BTN_play);
        opening_BTN_settings = findViewById(R.id.opening_BTN_settings);
        opening_BTN_leaderboard = findViewById(R.id.opening_BTN_leaderboard);


        opening_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startGame();
                String s = savedInstanceState.getString("p1");
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
        hideSystemUI(); //Credit : https://developer.android.com/training/system-ui/immersive#java
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

}