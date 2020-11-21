package com.dreamest.wargame_premium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private EditText settings_TXT_p1Name;
    private EditText settings_TXT_p2Name;
    private ImageButton settings_BTN_p1Avatar;
    private ImageButton settings_BTN_p2Avatar;
    private HideUI hideUI;


    private final String PLAYER_1_NAME = "PLAYER_1_NAME";
    private final String PLAYER_2_NAME = "PLAYER_2_NAME";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        hideUI = new HideUI();

        settings_TXT_p1Name = findViewById(R.id.settings_TXT_p1Name);
        settings_TXT_p2Name = findViewById(R.id.settings_TXT_p2Name);
        settings_BTN_p1Avatar = findViewById(R.id.settings_BTN_p1Avatar);
        settings_BTN_p2Avatar = findViewById(R.id.settings_BTN_p2Avatar);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        settings_TXT_p1Name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    editor.putString(PLAYER_1_NAME, v.getText().toString());
                    editor.apply();
                    settings_TXT_p1Name.clearFocus();
                    fixUI();
                }
                return false;
            }
        });

        settings_TXT_p2Name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    editor.putString(PLAYER_2_NAME, v.getText().toString());
                    editor.apply();
                    settings_TXT_p2Name.clearFocus();
                    fixUI();
                }
                return false;
            }
        });

        settings_BTN_p1Avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAvatarMenu(1);

            }
        });
        settings_BTN_p2Avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAvatarMenu(2);
            }
        });
    }

    private void fixUI() {
        hideUI.hideSystemUI(this);
    }

    private void openAvatarMenu(int playerNumber) {
        Intent myIntent = new Intent(this, AvatarActivity.class);
        myIntent.putExtra(AvatarActivity.EXTRA_KEY_PLAYER, playerNumber);
        startActivity(myIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideUI.hideSystemUI(this);
        updateAvatars();
    }

    private void updateAvatars() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String s;
        s = settings.getString(AvatarActivity.PLAYER_1_AVATAR, AvatarActivity.CHARACTER_1);
        Log.d("dddd", s);
        int playerOneID = getResources().getIdentifier(s, "drawable", getPackageName());
        Log.d("dddd", "" + playerOneID);
        settings_BTN_p1Avatar.setBackgroundResource(playerOneID);

        s = settings.getString(AvatarActivity.PLAYER_2_AVATAR, AvatarActivity.CHARACTER_2);
        int playerTwoID = getResources().getIdentifier(s, "drawable", getPackageName());
        settings_BTN_p2Avatar.setBackgroundResource(playerTwoID);
    }
}