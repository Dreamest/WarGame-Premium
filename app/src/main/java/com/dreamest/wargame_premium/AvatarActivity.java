package com.dreamest.wargame_premium;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AvatarActivity extends AppCompatActivity {
    public static final String EXTRA_KEY_PLAYER = "EXTRA_KEY_PLAYER";
    public static final String CHARACTER_1 = "ic_character_1";
    public static final String CHARACTER_2 = "ic_character_2";
    public static final String CHARACTER_3 = "ic_character_3";
    public static final String CHARACTER_4 = "ic_character_4";

    private ImageButton avatar_BTN_character1;
    private ImageButton avatar_BTN_character2;
    private ImageButton avatar_BTN_character3;
    private ImageButton avatar_BTN_character4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        int currentPlayer = getIntent().getIntExtra(EXTRA_KEY_PLAYER, -1);
        avatar_BTN_character1 = findViewById(R.id.avatar_BTN_character1);
        avatar_BTN_character2 = findViewById(R.id.avatar_BTN_character2);
        avatar_BTN_character3 = findViewById(R.id.avatar_BTN_character3);
        avatar_BTN_character4 = findViewById(R.id.avatar_BTN_character4);

        avatar_BTN_character4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseAvatar(currentPlayer, CHARACTER_1);
            }
        });
    }

    private void chooseAvatar(int currentPlayer, String characterIconID) {
        // TODO: 11/21/20 Store values for other activities to use and show 
    }
}