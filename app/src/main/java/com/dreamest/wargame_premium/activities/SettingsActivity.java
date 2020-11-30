package com.dreamest.wargame_premium.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dreamest.wargame_premium.game.Player;
import com.dreamest.wargame_premium.utilities.HideUI;
import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.utilities.Utility;
import com.google.gson.Gson;

public class SettingsActivity extends BaseActivity {

    private EditText settings_TXT_p1Name;
    private EditText settings_TXT_p2Name;
    private ImageButton settings_BTN_p1Avatar;
    private ImageButton settings_BTN_p2Avatar;
    private Button settings_BTN_return;

    public static final String PLAYER_1_NAME = "PLAYER_1_NAME";
    public static final String PLAYER_2_NAME = "PLAYER_2_NAME";

    public static final String LEFT_PLAYER = "LEFT_PLAYER";
    public static final String RIGHT_PLAYER = "RIGHT_PLAYER";

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings_TXT_p1Name = findViewById(R.id.settings_TXT_p1Name);
        settings_TXT_p2Name = findViewById(R.id.settings_TXT_p2Name);
        settings_BTN_p1Avatar = findViewById(R.id.settings_BTN_p1Avatar);
        settings_BTN_p2Avatar = findViewById(R.id.settings_BTN_p2Avatar);
        settings_BTN_return = findViewById(R.id.settings_BTN_return);

        settings = PreferenceManager.getDefaultSharedPreferences(this);
        editor = settings.edit();

        settings_TXT_p1Name.setText(settings.getString(PLAYER_1_NAME, ""));
        settings_TXT_p2Name.setText(settings.getString(PLAYER_2_NAME, ""));


        settings_TXT_p1Name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    settings_TXT_p1Name.clearFocus();
                    HideUI.hideSystemUI(SettingsActivity.this);
                }
                return false;
            }
        });

        settings_TXT_p2Name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){

                    settings_TXT_p2Name.clearFocus();
                    HideUI.hideSystemUI(SettingsActivity.this);
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
        int leftID = Utility.drawableNameToID(this, settings.getString(AvatarActivity.PLAYER_1_AVATAR, AvatarActivity.CHARACTER_1));
        int rightID = Utility.drawableNameToID(this, settings.getString(AvatarActivity.PLAYER_2_AVATAR, AvatarActivity.CHARACTER_2));
        Player leftPlayer = new Player(leftID, settings_TXT_p1Name.getText().toString(), true);
        Player rightPlayer = new Player(rightID, settings_TXT_p2Name.getText().toString(), true);
        Gson gson = new Gson();
        String leftJson = gson.toJson(leftPlayer);
        String rightJson = gson.toJson(rightPlayer);

        editor.putString(LEFT_PLAYER, leftJson);
        editor.putString(RIGHT_PLAYER, rightJson);
        editor.apply();

        finish();

    }

    private void openAvatarMenu(int playerNumber) {
        Intent myIntent = new Intent(this, AvatarActivity.class);
        myIntent.putExtra(AvatarActivity.EXTRA_KEY_PLAYER, playerNumber);
        startActivity(myIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAvatars();
    }


    private void updateAvatars() {
        String s;
        s = settings.getString(AvatarActivity.PLAYER_1_AVATAR, AvatarActivity.CHARACTER_1);
        settings_BTN_p1Avatar.setBackgroundResource(Utility.drawableNameToID(this, s));

        s = settings.getString(AvatarActivity.PLAYER_2_AVATAR, AvatarActivity.CHARACTER_2);
        settings_BTN_p2Avatar.setBackgroundResource(Utility.drawableNameToID(this, s));
    }
}