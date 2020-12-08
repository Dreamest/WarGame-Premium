package com.dreamest.wargame_premium.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.game.Player;
import com.dreamest.wargame_premium.utilities.HideUI;
import com.dreamest.wargame_premium.utilities.MySharedPreferences;
import com.dreamest.wargame_premium.utilities.Utility;

public class SettingsActivity extends BaseActivity {

    private EditText settings_TXT_leftName;
    private EditText settings_TXT_rightName;
    private ImageButton settings_BTN_leftAvatar;
    private ImageButton settings_BTN_rightAvatar;
    private Button settings_BTN_return;
    private Player leftPlayer, rightPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings_TXT_leftName = findViewById(R.id.settings_TXT_leftName);
        settings_TXT_rightName = findViewById(R.id.settings_TXT_rightName);
        settings_BTN_leftAvatar = findViewById(R.id.settings_BTN_leftAvatar);
        settings_BTN_rightAvatar = findViewById(R.id.settings_BTN_rightAvatar);
        settings_BTN_return = findViewById(R.id.settings_BTN_return);

        leftPlayer = (Player) MySharedPreferences.getMsp().getObject(MySharedPreferences.KEYS.LEFT_PLAYER, new Player(R.drawable.ic_character_1, "", true));
        rightPlayer = (Player) MySharedPreferences.getMsp().getObject(MySharedPreferences.KEYS.RIGHT_PLAYER, new Player(R.drawable.ic_character_2, "", true));

        settings_TXT_leftName.setText(leftPlayer.getName());
        settings_TXT_rightName.setText(rightPlayer.getName());


        settings_TXT_leftName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    settings_TXT_leftName.clearFocus();
                    HideUI.hideSystemUI(SettingsActivity.this);
                }
                return false;
            }
        });

        settings_TXT_rightName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    settings_TXT_rightName.clearFocus();
                    HideUI.hideSystemUI(SettingsActivity.this);
                }
                return false;
            }
        });

        settings_BTN_leftAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(SettingsActivity.this, R.raw.snd_button_click);
                openAvatarMenu(1);

            }
        });
        settings_BTN_rightAvatar.setOnClickListener(new View.OnClickListener() {
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
        int leftID = Utility.drawableNameToID(this, MySharedPreferences.getMsp().getString(MySharedPreferences.KEYS.PLAYER_LEFT_AVATAR, AvatarActivity.CHARACTER_1));
        int rightID = Utility.drawableNameToID(this, MySharedPreferences.getMsp().getString(MySharedPreferences.KEYS.PLAYER_RIGHT_AVATAR, AvatarActivity.CHARACTER_2));

        Player leftPlayer = new Player(leftID, settings_TXT_leftName.getText().toString(), true);
        Player rightPlayer = new Player(rightID, settings_TXT_rightName.getText().toString(), true);

        MySharedPreferences.getMsp().putObject(MySharedPreferences.KEYS.LEFT_PLAYER, leftPlayer);
        MySharedPreferences.getMsp().putObject(MySharedPreferences.KEYS.RIGHT_PLAYER, rightPlayer);

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        close();
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
        settings_BTN_leftAvatar.setBackgroundResource(Utility.drawableNameToID(this, MySharedPreferences.getMsp().getString(MySharedPreferences.KEYS.PLAYER_LEFT_AVATAR, AvatarActivity.CHARACTER_1)));
        settings_BTN_rightAvatar.setBackgroundResource(Utility.drawableNameToID(this, MySharedPreferences.getMsp().getString(MySharedPreferences.KEYS.PLAYER_RIGHT_AVATAR, AvatarActivity.CHARACTER_2)));
    }
}