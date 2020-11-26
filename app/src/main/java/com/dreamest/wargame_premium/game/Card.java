package com.dreamest.wargame_premium.game;

import androidx.appcompat.app.AppCompatActivity;

public class Card implements Comparable<Card>{
    private int value;
    private String suit;
    private final int VALUE_OFFSET = -2;
    private int id;

    public Card(){};

    public Card(int value, String suit, AppCompatActivity activity) {
        this.value = value;
        this.suit = suit;
        this.id = activity.getResources().getIdentifier(suit + "_" + valueToWord(value), "drawable", activity.getPackageName());

    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public int getVALUE_OFFSET() {
        return VALUE_OFFSET;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String valueToWord(int value) {
        String words[] = {"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace"};
        return words[value + VALUE_OFFSET];
    }

    @Override
    public int compareTo(Card other) {
        return this.value - other.value;
    }

}
