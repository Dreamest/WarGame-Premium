package com.dreamest.wargame_premium.game;

public class Player {
    private int imageID; // todo: remove this
    private String name;
    private int score;
    private boolean valid;

    public Player(){};

    public Player(int imageID, String name, boolean valid) {
        this.imageID = imageID;
        this.name = name;
        this.score = 0;
        this.valid = valid;
    }

    public int getImageID() {
        return imageID;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isValid() {
        return valid;
    }


    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
