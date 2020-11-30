package com.dreamest.wargame_premium.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.game.Leaderboards;
import com.dreamest.wargame_premium.game.Player;
import com.google.gson.Gson;

import org.w3c.dom.Text;

public class LeaderboardsActivity extends BaseActivity {
    private GridLayout leaderboards_GRID_scoreboard;
    private SharedPreferences settings;
    private Leaderboards leaderboards;

    private final int NAMES_OFFSET = 10;
    private final int SCORES_OFFSET = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        leaderboards_GRID_scoreboard = findViewById(R.id.leaderboards_GRID_scoreboard);

        settings = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String leaderboardsJSon = settings.getString(Leaderboards.LEADERBOARDS_KEY, Leaderboards.NO_LEADERBOARDS);
        Log.d("dddd", leaderboardsJSon);
        leaderboards = gson.fromJson(leaderboardsJSon, Leaderboards.class);


        // 0 - 9 Positions
        // 10-19 Name placements
        // 20-29 Score placements
        for (int i = 0; i < Leaderboards.MAX_SIZE; i++){
            TextView name = (TextView) leaderboards_GRID_scoreboard.getChildAt(i+NAMES_OFFSET);
            TextView score = (TextView) leaderboards_GRID_scoreboard.getChildAt(i+SCORES_OFFSET);
            Player p = leaderboards.getPlayers().get(i);
            name.setText(p.getName());
            score.setText("" + p.getScore());

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPlayerTab();
                }
            });
            score.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPlayerTab();
                }
            });
        }
    }

    private void openPlayerTab() {
        // TODO: 11/30/20 On click, should open new activity showing the player name, icon, score and location. 
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



}