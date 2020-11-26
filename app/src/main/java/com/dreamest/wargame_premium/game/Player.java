package com.dreamest.wargame_premium.game;

public class Player {
    private int imageID;
    private String name;
    private int score;

    public Player(){};

    public Player(int imageID, String name) {
        this.imageID = imageID;
        this.name = name;
        this.score = 0;
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
}
