package com.dreamest.wargame_premium.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dreamest.wargame_premium.game.Card;
import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.game.Leaderboards;
import com.dreamest.wargame_premium.game.Player;
import com.dreamest.wargame_premium.utilities.Utility;
import com.google.gson.Gson;

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
    private ImageView main_IMG_background;

    private List<Card> deckOfCards;
    private List<Card> player1Deck;
    private List<Card> player2Deck;
    private int p1Score, p2Score;
    private int counter;

    private Timer carousalTimer;

    private final int DELAY = 500;
    private boolean running;
    private boolean cardFacingUp;

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    private Player leftPlayer, rightPlayer;

    private final String NO_PLAYER_FOUND = "NO_PLAYER_FOUND";
    public static final String TIE = "It's a tie!\nNobody Wins.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carousalTimer = new Timer();

        running = false;
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        editor = settings.edit();

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
                    startAutoPlay();
                } else {
                    stopAutoPlay();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopAutoPlay();
    }

    private void turn() {
        if(!cardFacingUp) {
            cardFacingUp = true;
            Card p1Card = player1Deck.get(counter);
            Card p2Card = player2Deck.get(counter);
            Utility.playSound(this, R.raw.snd_card_flip);
            main_IMG_leftCard.setImageResource(p1Card.getId());
            main_IMG_rightCard.setImageResource(p2Card.getId());
            counter++;
            if (p1Card.compareTo(p2Card) > 0)
                p1Score++;
            else if (p1Card.compareTo(p2Card) < 0)
                p2Score++;
            updateScore();
        } else
            faceDownCards();

    }

    private void startAutoPlay() {
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (counter == player1Deck.size()) {
                            stopAutoPlay();
                            endMatch();
                        } else
                            turn();

                    }
                });
            }
        }, 0, DELAY);
        main_BTN_deal.setBackgroundResource(R.drawable.ic_pause_button);
        running = true;
    }

    private void stopAutoPlay() {
        carousalTimer.cancel();
        main_BTN_deal.setBackgroundResource(R.drawable.ic_play_button);
        running = false;
    }

    private void endMatch() {
        Intent myIntent = new Intent(this, ResultsActivity.class);
        Gson gson = new Gson();
        String winner;
        Leaderboards leaderboards = gson.fromJson(settings.getString(Leaderboards.LEADERBOARDS_KEY, Leaderboards.NO_LEADERBOARDS), Leaderboards.class);

        if (p1Score > p2Score){
            winner = gson.toJson(leftPlayer);
            if(leftPlayer.getScore() > leaderboards.getLowestScore())
                leaderboards.updateLeaderboards(leftPlayer);
        }
        else if (p1Score < p2Score){
            winner = gson.toJson(rightPlayer);
            if(rightPlayer.getScore() > leaderboards.getLowestScore())
                leaderboards.updateLeaderboards(rightPlayer);
        }
        else
            winner = TIE;
        if(p1Score - p2Score != 0)
            Utility.playSound(this, R.raw.snd_applause);
        else
            Utility.playSound(this, R.raw.snd_awww);

        editor.putString(Leaderboards.LEADERBOARDS_KEY, gson.toJson(leaderboards));
        editor.apply();
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
        Glide.with(this).load(R.drawable.game_background).into(main_IMG_background);

        updateSettings();
        updateScore();
        create_deck();
        dealCards();
    }

    private void faceDownCards() {
        main_IMG_leftCard.setImageResource(R.drawable.ic_card_back);
        main_IMG_rightCard.setImageResource(R.drawable.ic_card_back);
        cardFacingUp = false;
    }

    private void updateSettings() {
        leftPlayer = LoadPlayer(SettingsActivity.LEFT_PLAYER);
        main_IMG_leftIcon.setImageResource(leftPlayer.getImageID());
        main_LBL_leftName.setText(leftPlayer.getName());

        rightPlayer = LoadPlayer(SettingsActivity.RIGHT_PLAYER);
        main_IMG_rightIcon.setImageResource(rightPlayer.getImageID());
        main_LBL_rightName.setText(rightPlayer.getName());
    }

    private Player LoadPlayer(String key) {
        Player p;
        Gson gson = new Gson();
        String jsonFile = settings.getString(key, NO_PLAYER_FOUND);
        if (jsonFile.equals(NO_PLAYER_FOUND)) {
            if(key == SettingsActivity.LEFT_PLAYER)
                p = new Player(R.drawable.ic_character_1, "Left Player", true);
            else
                p = new Player(R.drawable.ic_character_2, "Right Player", true);
        } else
            p = gson.fromJson(jsonFile, Player.class);

        // TODO: 11/30/20 p.setLocation(currentLocation)
        return p;

    }

    private void updateScore() {
        main_LBL_leftScore.setText(p1Score + "");
        main_LBL_rightScore.setText(p2Score + "");
        leftPlayer.setScore(p1Score);
        rightPlayer.setScore(p2Score);
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
                deckOfCards.add(new Card(value, suit, this));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}