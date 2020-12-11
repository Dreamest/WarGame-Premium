package com.dreamest.wargame_premium.game;

import androidx.appcompat.app.AppCompatActivity;

import com.dreamest.wargame_premium.utilities.GpsTracker;
import com.google.android.gms.maps.model.LatLng;

public class Player {
    private int imageID; //We said in class this isn't a recommended class attribute, but in this case it's either saving this, or saving the avatar name and working a lot harder every time.
    private String name;
    private int score;
    private boolean valid;
    private LatLng position;

    public Player() {
    }

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

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
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

    public void updateLocation(AppCompatActivity activity) {

        GpsTracker gpsTracker = new GpsTracker(activity);
        if (gpsTracker.canGetLocation())
            position = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
//            this.latitude = gpsTracker.getLatitude();
//            this.longitude = gpsTracker.getLongitude();
        else
            gpsTracker.showSettingsAlert();
        gpsTracker.stopUsingGPS();
    }
}
