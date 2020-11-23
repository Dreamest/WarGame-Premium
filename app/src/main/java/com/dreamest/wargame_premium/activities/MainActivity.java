package com.dreamest.wargame_premium.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dreamest.wargame_premium.game.Card;
import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private List<Card> deckOfCards;
    private List<Card> player1Deck;
    private List<Card> player2Deck;
    private int p1Score, p2Score;
    private int counter;

    private Handler handler;
    private Runnable runnable;

    private Timer carousalTimer;

    private final int DELAY = 2000;
    private boolean running;
    //This is a test update to confirm the repository was forked properly. V2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        initGame();
        main_BTN_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    startAutoPlay();
                    main_BTN_deal.setBackgroundResource(R.drawable.ic_pause_button);
                    running = true;
                } else {
                    stopAutoPlay();
                    main_BTN_deal.setBackgroundResource(R.drawable.ic_play_button);
                    running = false;
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopAutoPlay();
    }

    private void turn() {
        Card p1Card = player1Deck.get(counter);
        Card p2Card = player2Deck.get(counter);
        int playerOneID = getResources().getIdentifier(p1Card.getName(), "drawable", getPackageName());
        int playerTwoID = getResources().getIdentifier(p2Card.getName(), "drawable", getPackageName());
        Utility.playSound(this, R.raw.snd_card_flip);
        main_IMG_leftCard.setImageResource(playerOneID);
        main_IMG_rightCard.setImageResource(playerTwoID);
        counter++;
        if (p1Card.compareTo(p2Card) > 0)
            p1Score++;
        else if (p1Card.compareTo(p2Card) < 0)
            p2Score++;
        updateScore();
    }

    private void startAutoPlay() {
        carousalTimer = new Timer();
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (counter == player1Deck.size()) {
                            endMatch();
                            stopAutoPlay();
                        } else {
                            turn();
                            carousalTimer.scheduleAtFixedRate(new TimerTask() {
                                @Override
                                public void run() {
                                    faceDownCards();
                                }
                            }, 3*DELAY/4, DELAY);
                        }

                    }
                });
            }
        }, 0, DELAY);
    }

    private void stopAutoPlay() {
        carousalTimer.cancel();
    }

    private void endMatch() {
        Intent myIntent = new Intent(this, ResultsActivity.class);
        String winner;
        if (p1Score > p2Score)
            winner = "Left Player Wins!";
        else if (p1Score < p2Score)
            winner = "Right Player Wins!";
        else
            winner = "It's a tie!";
        if(p1Score - p2Score != 0)
            Utility.playSound(this, R.raw.snd_applause);
        else
            Utility.playSound(this, R.raw.snd_awww);

        myIntent.putExtra(ResultsActivity.EXTRA_KEY_SCORE, Math.max(p1Score, p2Score));
        myIntent.putExtra(ResultsActivity.EXTRA_KEY_WINNER, winner);
        startActivity(myIntent);
        finish();
    }


    private void initGame() {
        main_BTN_deal.setBackgroundResource(R.drawable.ic_play_button);
        faceDownCards();

        p1Score = 0;
        p2Score = 0;

        updateSettings();
        updateScore();
        create_deck();
        dealCards();
    }

    private void faceDownCards() {
        main_IMG_leftCard.setImageResource(R.drawable.ic_card_back);
        main_IMG_rightCard.setImageResource(R.drawable.ic_card_back);
    }

    private void updateSettings() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String s;
        s = settings.getString(AvatarActivity.PLAYER_1_AVATAR, AvatarActivity.CHARACTER_1);
        int playerOneID = getResources().getIdentifier(s, "drawable", getPackageName());
        main_IMG_leftIcon.setImageResource(playerOneID);
        s = settings.getString(SettingsActivity.PLAYER_1_NAME, "player 1");
        main_LBL_leftName.setText(s);

        s = settings.getString(AvatarActivity.PLAYER_2_AVATAR, AvatarActivity.CHARACTER_2);
        int playerTwoID = getResources().getIdentifier(s, "drawable", getPackageName());
        main_IMG_rightIcon.setImageResource(playerTwoID);
        s = settings.getString(SettingsActivity.PLAYER_2_NAME, "player 2");
        main_LBL_rightName.setText(s);
    }

    private void updateScore() {
        main_LBL_leftScore.setText(p1Score + "");
        main_LBL_rightScore.setText(p2Score + "");
        main_BAR_progress.setProgress(counter);
    }

    private void dealCards() {
        Collections.shuffle(deckOfCards);
        int halfSize = deckOfCards.size() / 2;
        player1Deck = new ArrayList<>(deckOfCards.subList(0, halfSize));
        player2Deck = new ArrayList<>(deckOfCards.subList(halfSize, deckOfCards.size()));

        //Shuffling again just for kicks.
        Collections.shuffle(player1Deck);
        Collections.shuffle(player2Deck);
    }

    private void create_deck() {
        deckOfCards = new ArrayList<Card>();
        for (String suit : new String[]{"spades", "clubs", "hearts", "diamonds"}) {
            for (int value = 2; value <= 14; value++) {
                deckOfCards.add(new Card(value, suit));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        HideUI.hideSystemUI(this); //Credit : https://developer.android.com/training/system-ui/immersive#java
    }
}