package com.dreamest.wargame_premium.activities;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.game.GameManager;
import com.dreamest.wargame_premium.game.Leaderboards;
import com.dreamest.wargame_premium.game.Player;
import com.dreamest.wargame_premium.utilities.MySharedPreferences;
import com.dreamest.wargame_premium.utilities.OnSwipeTouchListener;
import com.dreamest.wargame_premium.utilities.Utility;

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

    private final int DELAY = 500;
    private RelativeLayout main_lay_view;

    private Timer carousalTimer;
    private GameManager gm;
    private boolean autoPlayActive;
    private boolean cardFacingUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initGame();
        requestPermission(android.Manifest.permission.ACCESS_FINE_LOCATION);

        main_lay_view.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeRight() {
                if (autoPlayActive)
                    Toast.makeText(MainActivity.this, "Swiped right. Autoplay stopped.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Swiped right.", Toast.LENGTH_SHORT).show();
                turn(true);
                stopAutoPlay();

            }

            public void onSwipeLeft() {
                if (autoPlayActive)
                    Toast.makeText(MainActivity.this, "Swiped left. Autoplay stopped.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Swiped left.", Toast.LENGTH_SHORT).show();
                backwardTurn();
                stopAutoPlay();
            }
        });

        main_BTN_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(MainActivity.this, R.raw.snd_click2);
                if (!autoPlayActive) {
                    autoPlayActive = true;
                    startAutoPlay();
                } else {
                    stopAutoPlay();
                }
            }
        });

    }


    /**
     * Reset the game
     */
    private void setToFirstMove() {
        gm.setFirstMove();
        playAudioVideo();
        faceDownCards();
        updateScore();
    }

    private void requestPermission(String permission) {
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{permission}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findViews() {
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
        main_lay_view = findViewById(R.id.main_lay_view);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (autoPlayActive)
            stopAutoPlay();
    }

    private void turn(boolean quickFlip) {
        if (gm.getCounter() == gm.DECK_SIZE - 1) {
            if (autoPlayActive)
                stopAutoPlay();
            endMatch();
            return;
        }
        if (!cardFacingUp) {
            gm.play();
            if (!quickFlip)
                cardFacingUp = true;
            playAudioVideo();
            updateScore();
        } else
            faceDownCards();
    }

    /**
     * Reverts the game to one turn before
     */
    private void backwardTurn() {
        if (gm.getCounter() == 0) {
            setToFirstMove();
            return;
        } else if (gm.getCounter() != -1) {
            gm.playBackward();
            playAudioVideo();
            updateScore();
        }
    }

    private void playAudioVideo() {
        Utility.playSound(this, R.raw.snd_card_flip);
        main_IMG_leftCard.setImageResource(getResources().getIdentifier(gm.getLeftCard().getName(), "drawable", getPackageName()));
        main_IMG_rightCard.setImageResource(getResources().getIdentifier(gm.getRightCard().getName(), "drawable", getPackageName()));
    }

    private void startAutoPlay() {
        carousalTimer = new Timer();
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        turn(false);
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
            autoPlayActive = false;
        }
    }

    private void endMatch() {
        Intent myIntent = new Intent(this, ResultsActivity.class);
        Player winner = gm.determineWinner();
        if (winner != null) {
            winner.updateLocation(this);
            Leaderboards leaderboards = (Leaderboards) MySharedPreferences.getMsp().getObject(MySharedPreferences.KEYS.LEADERBOARDS_KEY, new Leaderboards());
            leaderboards.updateLeaderboards(winner);
            MySharedPreferences.getMsp().putObject(MySharedPreferences.KEYS.LEADERBOARDS_KEY, leaderboards);
            myIntent.putExtra(ResultsActivity.EXTRA_KEY_WINNER, winner.getName());
            Utility.playSound(this, R.raw.snd_applause);
        } else {
            Utility.playSound(this, R.raw.snd_awww);
            myIntent.putExtra(ResultsActivity.EXTRA_KEY_WINNER, GameManager.TIE);
        }
        myIntent.putExtra(ResultsActivity.EXTRA_KEY_SCORE, Math.max(gm.getRightPlayer().getScore(), gm.getLeftPlayer().getScore()));
        startActivity(myIntent);
        finish();
    }

    private void initGame() {
        gm = new GameManager();
        autoPlayActive = false;
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

        main_BAR_progress.setProgress(gm.getCounter() + 1);
    }

}