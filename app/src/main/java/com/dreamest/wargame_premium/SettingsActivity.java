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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private EditText settings_TXT_p1Name;
    private EditText settings_TXT_p2Name;
    private ImageButton settings_BTN_p1Avatar;
    private ImageButton settings_BTN_p2Avatar;
    private Button settings_BTN_return;

    public static final String PLAYER_1_NAME = "PLAYER_1_NAME";
    public static final String PLAYER_2_NAME = "PLAYER_2_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings_TXT_p1Name = findViewById(R.id.settings_TXT_p1Name);
        settings_TXT_p2Name = findViewById(R.id.settings_TXT_p2Name);
        settings_BTN_p1Avatar = findViewById(R.id.settings_BTN_p1Avatar);
        settings_BTN_p2Avatar = findViewById(R.id.settings_BTN_p2Avatar);
        settings_BTN_return = findViewById(R.id.settings_BTN_return);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        settings_TXT_p1Name.setText(settings.getString(PLAYER_1_NAME, ""));
        settings_TXT_p2Name.setText(settings.getString(PLAYER_2_NAME, ""));


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
                Utility.playSound(SettingsActivity.this, R.raw.snd_button_click);
                openAvatarMenu(1);

            }
        });
        settings_BTN_p2Avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(SettingsActivity.this, R.raw.snd_button_click);
                openAvatarMenu(2);
            }
        });

        settings_BTN_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(SettingsActivity.this, R.raw.snd_button_click);
                close();
            }
        });
    }

    private void close() {
        finish();
    }

    private void fixUI() {
        HideUI.hideSystemUI(this);
    }

    private void openAvatarMenu(int playerNumber) {
        Intent myIntent = new Intent(this, AvatarActivity.class);
        myIntent.putExtra(AvatarActivity.EXTRA_KEY_PLAYER, playerNumber);
        startActivity(myIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HideUI.hideSystemUI(this);
        updateAvatars();
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            HideUI.hideSystemUI(this);
//        }
//    }

    private void updateAvatars() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String s;
        s = settings.getString(AvatarActivity.PLAYER_1_AVATAR, AvatarActivity.CHARACTER_1);
        int playerOneID = getResources().getIdentifier(s, "drawable", getPackageName());
        settings_BTN_p1Avatar.setBackgroundResource(playerOneID);

        s = settings.getString(AvatarActivity.PLAYER_2_AVATAR, AvatarActivity.CHARACTER_2);
        int playerTwoID = getResources().getIdentifier(s, "drawable", getPackageName());
        settings_BTN_p2Avatar.setBackgroundResource(playerTwoID);
    }
}