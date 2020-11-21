package com.dreamest.wargame_premium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    private Button results_BTN_restart;
    private Button results_BTN_exit;
    private TextView results_LBL_winner;
    public static final String EXTRA_KEY_WINNER = "EXTRA_KEY_WINNER";
    public static final String EXTRA_KEY_SCORE = "EXTRA_KEY_SCORE";
    private HideUI hideUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);

        hideUI = new HideUI();

        results_BTN_restart = findViewById(R.id.results_BTN_restart);
        results_BTN_exit = findViewById(R.id.results_BTN_exit);
        results_LBL_winner = findViewById(R.id.results_LBL_winner);

        results_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeApp();
            }
        });

        results_BTN_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });

        String winner = getIntent().getStringExtra(EXTRA_KEY_WINNER);
        int score = getIntent().getIntExtra(EXTRA_KEY_SCORE, -1);
        results_LBL_winner.setText(winner + "\nTop Score: " + score);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideUI.hideSystemUI(this); //Credit : https://developer.android.com/training/system-ui/immersive#java
    }

    private void restartGame() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
        finish();
    }

    private void closeApp() {
        finish();
    }
}