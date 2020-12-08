package com.dreamest.wargame_premium;

import android.app.Application;

import com.dreamest.wargame_premium.utilities.MySharedPreferences;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MySharedPreferences.init(this);


    }
}
