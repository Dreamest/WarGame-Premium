package com.dreamest.wargame_premium.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.utilities.Utility;

public class ResultsActivity extends BaseActivity {
    private Button results_BTN_restart;
    private Button results_BTN_MainMenu;
    private TextView results_LBL_winner;
    public static final String EXTRA_KEY_WINNER = "EXTRA_KEY_WINNER";
    public static final String EXTRA_KEY_SCORE = "EXTRA_KEY_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);

        results_BTN_restart = findViewById(R.id.results_BTN_restart);
        results_BTN_MainMenu = findViewById(R.id.results_BTN_MainMenu);
        results_LBL_winner = findViewById(R.id.results_LBL_winner);

        results_BTN_MainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(ResultsActivity.this, R.raw.snd_button_click);

                close();
            }
        });

        results_BTN_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(ResultsActivity.this, R.raw.snd_button_click);
                restartGame();
            }
        });

        String winner = getIntent().getStringExtra(EXTRA_KEY_WINNER);
        int score = getIntent().getIntExtra(EXTRA_KEY_SCORE, -1);
        results_LBL_winner.setText(winner + "\nTop Score: " + score);
    }

    private void restartGame() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
        finish();
    }

    private void close() {
        finish();
    }
}