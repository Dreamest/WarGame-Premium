package com.dreamest.wargame_premium.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.dreamest.wargame_premium.utilities.HideUI;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        HideUI.hideSystemUI(this);
    }
}
