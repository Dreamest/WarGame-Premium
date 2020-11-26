package com.dreamest.wargame_premium.utilities;

import android.app.Activity;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

public class Utility {


    public static void playSound(AppCompatActivity activity, int rawSound) {
        MediaPlayer mp = MediaPlayer.create(activity, rawSound);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                mp = null;
            }
        });
        mp.start();
    }


    public static int drawableNameToID(AppCompatActivity activity, String name){
        return activity.getResources().getIdentifier(name, "drawable", activity.getPackageName());
    }
}
