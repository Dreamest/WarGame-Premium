package com.dreamest.wargame_premium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings_TXT_p1Name = findViewById(R.id.settings_TXT_p1Name);
        settings_TXT_p2Name = findViewById(R.id.settings_TXT_p2Name);
        settings_BTN_p1Avatar = findViewById(R.id.settings_BTN_p1Avatar);
        settings_BTN_p2Avatar = findViewById(R.id.settings_BTN_p2Avatar);

        settings_TXT_p1Name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    settings_TXT_p1Name.clearFocus();
                    hideSystemUI();
                }
                return false;
            }
        });


        settings_TXT_p2Name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    settings_TXT_p2Name.clearFocus();
                    hideSystemUI();
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

    private void openAvatarMenu(int playerNumber) {
        Intent myIntent = new Intent(this, AvatarActivity.class);
        myIntent.putExtra(AvatarActivity.EXTRA_KEY_PLAYER, playerNumber);
        startActivity(myIntent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("test", settings_TXT_p1Name.getText().toString());
        Log.d("dddd", "onsavedinstance");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI(); //Credit : https://developer.android.com/training/system-ui/immersive#java

    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }



}