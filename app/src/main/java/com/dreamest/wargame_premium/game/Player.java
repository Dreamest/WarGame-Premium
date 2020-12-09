package com.dreamest.wargame_premium.game;

import androidx.appcompat.app.AppCompatActivity;

import com.dreamest.wargame_premium.utilities.GpsTracker;

public class Player {
    private int imageID; // todo: remove this
    private String name;
    private int score;
    private boolean valid;
    private double latitude;
    private double longitude;

    public Player() {
    }

    ;

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void updateLocation(AppCompatActivity activity) {

        GpsTracker gpsTracker = new GpsTracker(activity);
        if (gpsTracker.canGetLocation()) {
            this.latitude = gpsTracker.getLatitude();
            this.longitude = gpsTracker.getLongitude();
        } else {
            gpsTracker.showSettingsAlert();
        }
    }
}
