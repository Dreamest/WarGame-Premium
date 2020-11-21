package com.dreamest.wargame_premium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageButton main_BTN_deal;
    private ImageView main_IMG_leftCard;
    private ImageView main_IMG_rightCard;
    private TextView main_LBL_leftScore;
    private TextView main_LBL_rightScore;
    private List<Card> deckOfCards;
    private List<Card> player1Deck;
    private List<Card> player2Deck;
    private int p1Score, p2Score;
    private int counter;
    private HideUI hideUI;
    //This is a test update to confirm the repository was forked properly. V2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideUI = new HideUI();

        main_BTN_deal = findViewById(R.id.main_BTN_deal);
        main_IMG_leftCard = findViewById(R.id.main_IMG_leftCard);
        main_IMG_rightCard = findViewById(R.id.main_IMG_rightCard);
        main_LBL_leftScore = findViewById(R.id.main_LBL_leftScore);
        main_LBL_rightScore = findViewById(R.id.main_LBL_rightScore);


        initGame();


        main_BTN_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter == player1Deck.size()) {
                    
                    changeActivity();
                }
                else
                    turn();
            }
        });
    }

    private void turn() {
        Card p1Card = player1Deck.get(counter);
        Card p2Card = player2Deck.get(counter);
        int playerOneID = getResources().getIdentifier(p1Card.getName(), "drawable", getPackageName());
        int playerTwoID = getResources().getIdentifier(p2Card.getName(), "drawable", getPackageName());
        main_IMG_leftCard.setImageResource(playerOneID);
        main_IMG_rightCard.setImageResource(playerTwoID);
        counter++;
        if (p1Card.compareTo(p2Card) > 0)
            p1Score++;
        else if(p1Card.compareTo(p2Card) < 0)
            p2Score++;
        updateScore();
    }

    private void changeActivity() {
        Intent myIntent = new Intent(this, ResultsActivity.class);
        String winner;
        if (p1Score > p2Score)
            winner = "Left Player Wins!";
        else if (p1Score < p2Score)
            winner = "Right Player Wins!";
        else
            winner = "It's a tie!";
        myIntent.putExtra(ResultsActivity.EXTRA_KEY_SCORE, Math.max(p1Score, p2Score));
        myIntent.putExtra(ResultsActivity.EXTRA_KEY_WINNER, winner);
        startActivity(myIntent);
        finish();

    }

    private void initGame() {
        p1Score = 0;
        p2Score = 0;
        updateScore();
        create_deck();
        dealCards();
    }

    private void updateScore() {
        main_LBL_leftScore.setText(p1Score + "");
        main_LBL_rightScore.setText(p2Score + "");
    }

    private void dealCards() {
        Collections.shuffle(deckOfCards);
        int halfSize = deckOfCards.size()/2;
        player1Deck = new ArrayList<>(deckOfCards.subList(0, halfSize));
        player2Deck = new ArrayList<>(deckOfCards.subList(halfSize, deckOfCards.size()));

        //Shuffling again just for kicks.
        Collections.shuffle(player1Deck);
        Collections.shuffle(player2Deck);
    }

    private void create_deck() {
        deckOfCards = new ArrayList<Card>();
        for (String suit: new String[] {"spades", "clubs", "hearts", "diamonds"}) {
            for (int value = 2 ; value <=14; value++) {
                deckOfCards.add(new Card(value, suit));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideUI.hideSystemUI(this); //Credit : https://developer.android.com/training/system-ui/immersive#java
    }
}