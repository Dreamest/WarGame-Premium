package com.dreamest.wargame_premium;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;

public class AvatarActivity extends BaseActivity {
    public static final String EXTRA_KEY_PLAYER = "EXTRA_KEY_PLAYER";
    public static final String CHARACTER_1 = "ic_character_1";
    public static final String CHARACTER_2 = "ic_character_2";
    public static final String CHARACTER_3 = "ic_character_3";
    public static final String CHARACTER_4 = "ic_character_4";
    public static final String PLAYER_1_AVATAR = "PLAYER_1_AVATAR";
    public static final String PLAYER_2_AVATAR = "PLAYER_2_AVATAR";


    private ImageButton avatar_BTN_character1;
    private ImageButton avatar_BTN_character2;
    private ImageButton avatar_BTN_character3;
    private ImageButton avatar_BTN_character4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        int currentPlayer = getIntent().getIntExtra(EXTRA_KEY_PLAYER, -1);
        avatar_BTN_character1 = findViewById(R.id.avatar_BTN_character1);
        avatar_BTN_character2 = findViewById(R.id.avatar_BTN_character2);
        avatar_BTN_character3 = findViewById(R.id.avatar_BTN_character3);
        avatar_BTN_character4 = findViewById(R.id.avatar_BTN_character4);

        avatar_BTN_character1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(AvatarActivity.this, R.raw.snd_button_click);
                chooseAvatar(currentPlayer, CHARACTER_1, editor);
            }
        });
        avatar_BTN_character2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(AvatarActivity.this, R.raw.snd_button_click);
                chooseAvatar(currentPlayer, CHARACTER_2, editor);
            }
        });
        avatar_BTN_character3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(AvatarActivity.this, R.raw.snd_button_click);
                chooseAvatar(currentPlayer, CHARACTER_3, editor);
            }
        });
        avatar_BTN_character4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(AvatarActivity.this, R.raw.snd_button_click);
                chooseAvatar(currentPlayer, CHARACTER_4, editor);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        HideUI.hideSystemUI(this);
    }

    private void chooseAvatar(int currentPlayer, String characterIconID, SharedPreferences.Editor editor) {
        if(currentPlayer == 1)
            editor.putString(PLAYER_1_AVATAR, characterIconID);
        else
            editor.putString(PLAYER_2_AVATAR, characterIconID);
        editor.apply();
        finish();


    }
}