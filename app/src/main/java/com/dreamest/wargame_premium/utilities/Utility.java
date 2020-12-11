package com.dreamest.wargame_premium.utilities;

import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

public class Utility {

    /**
     * Plays an audio file
     *
     * @param activity the activity
     * @param rawSound ID of the audio file
     */
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

    /**
     * Converts vectorDrawable file to its matching ID
     *
     * @param activity the activity
     * @param name     name of the vectorDrawable file
     * @return ID of the vectorDrawable
     */
    public static int drawableNameToID(AppCompatActivity activity, String name) {
        return activity.getResources().getIdentifier(name, "drawable", activity.getPackageName());
    }
}
