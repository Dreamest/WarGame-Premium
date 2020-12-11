package com.dreamest.wargame_premium.game;

import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.utilities.MySharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameManager {
    public static final String TIE = "It's a tie!\nNobody Wins.";
    public final int DECK_SIZE = 26;
    private final String NO_PLAYER_FOUND = "NO_PLAYER_FOUND";
    private List<Card> leftDeck;
    private List<Card> rightDeck;
    private int counter;
    private Player leftPlayer, rightPlayer;
    private Card leftCard, rightCard;


    public GameManager() {
        leftPlayer = loadPlayer(MySharedPreferences.KEYS.LEFT_PLAYER);
        rightPlayer = loadPlayer(MySharedPreferences.KEYS.RIGHT_PLAYER);
        leftPlayer.setScore(0);
        rightPlayer.setScore(0);
        createDecks();

    }

    public void play() {
        leftCard = leftDeck.get(counter);
        rightCard = rightDeck.get(counter);
        counter++;
        if (leftCard.compareTo(rightCard) > 0)
            leftPlayer.setScore(leftPlayer.getScore() + 1);
        else if (leftCard.compareTo(rightCard) < 0)
            rightPlayer.setScore(rightPlayer.getScore() + 1);
    }

    private void createDecks() {
        List deckOfCards = new ArrayList<Card>();
        for (String suit : new String[]{"spades", "clubs", "hearts", "diamonds"}) {
            for (int value = 2; value <= 14; value++) {
                deckOfCards.add(new Card(value, suit));
            }
        }
        Collections.shuffle(deckOfCards);
        int halfSize = deckOfCards.size() / 2;
        leftDeck = new ArrayList<>(deckOfCards.subList(0, halfSize));
        rightDeck = new ArrayList<>(deckOfCards.subList(halfSize, deckOfCards.size()));
        //Shuffling again just for kicks.
        Collections.shuffle(leftDeck);
        Collections.shuffle(rightDeck);
    }

    private Player loadPlayer(String key) {
        if (key.equals(MySharedPreferences.KEYS.LEFT_PLAYER))
            return (Player) MySharedPreferences.getMsp().getObject(key, new Player(R.drawable.ic_character_1, "Left", true));
        return (Player) MySharedPreferences.getMsp().getObject(key, new Player(R.drawable.ic_character_2, "Right", true));
    }

    public List<Card> getLeftDeck() {
        return leftDeck;
    }

    public void setLeftDeck(List<Card> leftDeck) {
        this.leftDeck = leftDeck;
    }

    public List<Card> getRightDeck() {
        return rightDeck;
    }

    public void setRightDeck(List<Card> rightDeck) {
        this.rightDeck = rightDeck;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Player getLeftPlayer() {
        return leftPlayer;
    }

    public void setLeftPlayer(Player leftPlayer) {
        this.leftPlayer = leftPlayer;
    }

    public Player getRightPlayer() {
        return rightPlayer;
    }

    public void setRightPlayer(Player rightPlayer) {
        this.rightPlayer = rightPlayer;
    }

    public String getNO_PLAYER_FOUND() {
        return NO_PLAYER_FOUND;
    }

    public Card getLeftCard() {
        return leftCard;
    }

    public void setLeftCard(Card leftCard) {
        this.leftCard = leftCard;
    }

    public Card getRightCard() {
        return rightCard;
    }

    public void setRightCard(Card rightCard) {
        this.rightCard = rightCard;
    }

    public int getDECK_SIZE() {
        return DECK_SIZE;
    }

    /**
     * Determines the game winner
     *
     * @return winning player in case of a victory, null if draw
     */
    public Player determineWinner() {
        if (leftPlayer.getScore() > rightPlayer.getScore())
            return leftPlayer;
        else if (leftPlayer.getScore() < rightPlayer.getScore())
            return rightPlayer;
        return null;
    }
}
