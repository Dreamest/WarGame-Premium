package com.dreamest.wargame_premium.game;

public class Card implements Comparable<Card>{
    private int value;
    private String suit;
    private String name;
    private final int VALUE_OFFSET = -2;

    public Card() {
    }

    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
        this.name = suit + "_" + valueToWord(value);

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String valueToWord(int value) {
        String[] words = {"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace"};
        return words[value + VALUE_OFFSET];
    }

    @Override
    public int compareTo(Card other) {
        return this.value - other.value;
    }

}
