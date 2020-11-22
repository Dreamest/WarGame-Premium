package com.dreamest.wargame_premium;

import android.app.Activity;
import android.media.MediaPlayer;

public class Utility {


    public static void playSound(Activity activity, int rawSound) {
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
}
