package com.dreamest.wargame_premium;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        HideUI.hideSystemUI(this);
    }
}
