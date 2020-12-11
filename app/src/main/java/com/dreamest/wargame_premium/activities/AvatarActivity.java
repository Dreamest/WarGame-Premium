package com.dreamest.wargame_premium.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.utilities.MySharedPreferences;
import com.dreamest.wargame_premium.utilities.Utility;

public class AvatarActivity extends BaseActivity {
    public static final String EXTRA_KEY_PLAYER = "EXTRA_KEY_PLAYER";
    public static final String CHARACTER_1 = "ic_character_1";
    public static final String CHARACTER_2 = "ic_character_2";
    public static final String CHARACTER_3 = "ic_character_3";
    public static final String CHARACTER_4 = "ic_character_4";
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    private ImageButton avatar_BTN_character1;
    private ImageButton avatar_BTN_character2;
    private ImageButton avatar_BTN_character3;
    private ImageButton avatar_BTN_character4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        int currentPlayer = getIntent().getIntExtra(EXTRA_KEY_PLAYER, -1);
        findViews();

        avatar_BTN_character1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(AvatarActivity.this, R.raw.snd_button_click);
                chooseAvatar(currentPlayer, CHARACTER_1);
            }
        });
        avatar_BTN_character2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(AvatarActivity.this, R.raw.snd_button_click);
                chooseAvatar(currentPlayer, CHARACTER_2);
            }
        });
        avatar_BTN_character3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(AvatarActivity.this, R.raw.snd_button_click);
                chooseAvatar(currentPlayer, CHARACTER_3);
            }
        });
        avatar_BTN_character4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.playSound(AvatarActivity.this, R.raw.snd_button_click);
                chooseAvatar(currentPlayer, CHARACTER_4);
            }
        });
    }

    private void findViews() {
        avatar_BTN_character1 = findViewById(R.id.avatar_BTN_character1);
        avatar_BTN_character2 = findViewById(R.id.avatar_BTN_character2);
        avatar_BTN_character3 = findViewById(R.id.avatar_BTN_character3);
        avatar_BTN_character4 = findViewById(R.id.avatar_BTN_character4);
    }


    private void chooseAvatar(int currentPlayer, String characterIconID) {
        if (currentPlayer == LEFT)
            MySharedPreferences.getMsp().putString(MySharedPreferences.KEYS.PLAYER_LEFT_AVATAR, characterIconID);
        else
            MySharedPreferences.getMsp().putString(MySharedPreferences.KEYS.PLAYER_RIGHT_AVATAR, characterIconID);
        finish();


    }
}