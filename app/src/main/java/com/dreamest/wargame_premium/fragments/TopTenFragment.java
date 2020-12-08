package com.dreamest.wargame_premium.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dreamest.wargame_premium.R;

public class TopTenFragment extends Fragment {
    private GridLayout topTen_LAY_scoreboard;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("dddd", "Oncreate");

        //        leaderboards_GRID_scoreboard = findViewById(R.id.leaderboards_GRID_scoreboard);
//
//        settings = PreferenceManager.getDefaultSharedPreferences(this);
//        Gson gson = new Gson();
//        String leaderboardsJSon = settings.getString(Leaderboards.LEADERBOARDS_KEY, Leaderboards.NO_LEADERBOARDS);
//        Log.d("dddd", leaderboardsJSon);
//        leaderboards = gson.fromJson(leaderboardsJSon, Leaderboards.class);
//
//
//        // 0 - 9 Positions
//        // 10-19 Name placements
//        // 20-29 Score placements
//        for (int i = 0; i < Leaderboards.MAX_SIZE; i++){
//            TextView name = (TextView) leaderboards_GRID_scoreboard.getChildAt(i+NAMES_OFFSET);
//            TextView score = (TextView) leaderboards_GRID_scoreboard.getChildAt(i+SCORES_OFFSET);
//            Player p = leaderboards.getPlayers().get(i);
//            name.setText(p.getName());
//            score.setText("" + p.getScore());
//
//            if(p.isValid()) {
//                name.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        openPlayerTab(p);
//                    }
//                });
//                score.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        openPlayerTab(p);
//                    }
//                });
//            }
//        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_ten, container, false);
        topTen_LAY_scoreboard = view.findViewById(R.id.topTen_LAY_scoreboard);
        Log.d("dddd", "OnCreateView");
        return view;
    }
}
