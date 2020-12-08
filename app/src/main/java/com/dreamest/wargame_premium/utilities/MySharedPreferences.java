package com.dreamest.wargame_premium.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class MySharedPreferences {

    private static MySharedPreferences msp;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private Gson gson;

    private MySharedPreferences(Context context) {
        settings = context.getApplicationContext().getSharedPreferences(KEYS.MY_SP, Context.MODE_PRIVATE);
        editor = settings.edit();
        gson = new Gson();
    }

    public static void init(Context context) {
        if (msp == null)
            msp = new MySharedPreferences(context);
    }

    public static MySharedPreferences getMsp() {
        return msp;
    }

    public void removeKey(String key) {
        editor.remove(key);
        editor.apply();
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        return settings.getString(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return settings.getBoolean(key, defaultValue);
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key, int defaultValue) {
        return settings.getInt(key, defaultValue);
    }

    public void putLong(String key, long value) {
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key, long defaultValue) {
        return settings.getLong(key, defaultValue);
    }

    public void putObject(String key, Object value) {
        putString(key, gson.toJson(value));
    }

    public Object getObject(String key, Object defaultValue) {
        String objectJson = getString(key, KEYS.NO_OBJECT);
        if (objectJson.equals(KEYS.NO_OBJECT))
            return defaultValue;
        return gson.fromJson(objectJson, defaultValue.getClass());
    }

    public interface KEYS {
        public static final String MY_SP = "MY_SP";
        public static final String NO_OBJECT = "NO_OBJECT";
        public static final String PLAYER_LEFT_AVATAR = "PLAYER_LEFT_AVATAR";
        public static final String PLAYER_RIGHT_AVATAR = "PLAYER_RIGHT_AVATAR";
        public static final String LEADERBOARDS_KEY = "LEADERBOARDS_KEY";
        public static final String PLAYER_LEFT_NAME = "PLAYER_LEFT_NAME";
        public static final String PLAYER_RIGHT_NAME = "PLAYER_RIGHT_NAME";
        public static final String LEFT_PLAYER = "LEFT_PLAYER";
        public static final String RIGHT_PLAYER = "RIGHT_PLAYER";

    }


}
