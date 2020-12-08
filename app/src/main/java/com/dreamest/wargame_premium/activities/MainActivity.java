package com.dreamest.wargame_premium.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.game.GameManager;
import com.dreamest.wargame_premium.game.Leaderboards;
import com.dreamest.wargame_premium.utilities.MySharedPreferences;
import com.dreamest.wargame_premium.utilities.Utility;
import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
    private ImageButton main_BTN_deal;
    private ImageView main_IMG_leftCard;
    private ImageView main_IMG_rightCard;
    private ImageView main_IMG_leftIcon;
    private ImageView main_IMG_rightIcon;
    private TextView main_LBL_leftScore;
    private TextView main_LBL_rightScore;
    private TextView main_LBL_rightName;
    private TextView main_LBL_leftName;
    private ProgressBar main_BAR_progress;
    private ImageView main_IMG_background;

    private final int DELAY = 1000;

    private Timer carousalTimer;
    private GameManager gm;
    private boolean running;
    private boolean cardFacingUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gm = new GameManager(this);

        running = false;

        main_BTN_deal = findViewById(R.id.main_BTN_deal);
        main_IMG_leftCard = findViewById(R.id.main_IMG_leftCard);
        main_IMG_rightCard = findViewById(R.id.main_IMG_rightCard);
        main_LBL_leftScore = findViewById(R.id.main_LBL_leftScore);
        main_LBL_rightScore = findViewById(R.id.main_LBL_rightScore);
        main_LBL_rightName = findViewById(R.id.main_LBL_rightName);
        main_LBL_leftName = findViewById(R.id.main_LBL_leftName);
        main_IMG_rightIcon = findViewById(R.id.main_IMG_rightIcon);
        main_IMG_leftIcon = findViewById(R.id.main_IMG_leftIcon);
        main_BAR_progress = findViewById(R.id.main_BAR_progress);
        main_IMG_background = findViewById(R.id.main_IMG_background);
        initGame();

        main_BTN_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    running = true;
                    startAutoPlay();
                } else {
                    stopAutoPlay();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (running)
            stopAutoPlay();
    }

    private void turn() {
        if(!cardFacingUp) {
            gm.play();
            cardFacingUp = true;
            Utility.playSound(this, R.raw.snd_card_flip);
            main_IMG_leftCard.setImageResource(gm.getLeftCard().getId());
            main_IMG_rightCard.setImageResource(gm.getRightCard().getId());
            updateScore();
        } else
            faceDownCards();
    }

    private void startAutoPlay() {
        carousalTimer = new Timer();
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (gm.getCounter() == gm.DECK_SIZE) {
                            stopAutoPlay();
                            endMatch();
                        } else
                            turn();
                    }
                });
            }
        }, 0, DELAY);
        main_BTN_deal.setBackgroundResource(R.drawable.ic_pause_button);
    }

    private void stopAutoPlay() {
        if (carousalTimer != null) {
            carousalTimer.cancel();
            main_BTN_deal.setBackgroundResource(R.drawable.ic_play_button);
            running = false;
        }
    }

    private void endMatch() {
        Intent myIntent = new Intent(this, ResultsActivity.class);
        Gson gson = new Gson();
        String winner;
        Leaderboards leaderboards = (Leaderboards) MySharedPreferences.getMsp().getObject(MySharedPreferences.KEYS.LEADERBOARDS_KEY, new Leaderboards());
        winner = gm.determineWinner(leaderboards); //Also updates the leaderboards
        MySharedPreferences.getMsp().putObject(MySharedPreferences.KEYS.LEADERBOARDS_KEY, leaderboards);

        if (winner.equals(GameManager.TIE))
            Utility.playSound(this, R.raw.snd_awww);
        else
            Utility.playSound(this, R.raw.snd_applause);

        myIntent.putExtra(ResultsActivity.EXTRA_KEY_SCORE, Math.max(gm.getRightPlayer().getScore(), gm.getLeftPlayer().getScore()));
        myIntent.putExtra(ResultsActivity.EXTRA_KEY_WINNER, winner);
        startActivity(myIntent);
        finish();
    }


    private void initGame() {
        main_BTN_deal.setBackgroundResource(R.drawable.ic_play_button);
        faceDownCards();
        Glide.with(this).load(R.drawable.game_background).into(main_IMG_background);
        updatePlayerInfo();
        updateScore();
    }

    private void faceDownCards() {
        main_IMG_leftCard.setImageResource(R.drawable.ic_card_back);
        main_IMG_rightCard.setImageResource(R.drawable.ic_card_back);
        cardFacingUp = false;
    }

    private void updatePlayerInfo() {
        main_IMG_leftIcon.setImageResource(gm.getLeftPlayer().getImageID());
        main_LBL_leftName.setText(gm.getLeftPlayer().getName());

        main_IMG_rightIcon.setImageResource(gm.getRightPlayer().getImageID());
        main_LBL_rightName.setText(gm.getRightPlayer().getName());
    }

    private void updateScore() {
        main_LBL_leftScore.setText(gm.getLeftPlayer().getScore() + "");
        main_LBL_rightScore.setText(gm.getRightPlayer().getScore() + "");

        main_BAR_progress.setProgress(gm.getCounter());
    }
}