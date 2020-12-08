package com.dreamest.wargame_premium.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dreamest.wargame_premium.R;
import com.dreamest.wargame_premium.game.Leaderboards;
import com.dreamest.wargame_premium.game.Player;
import com.dreamest.wargame_premium.utilities.MySharedPreferences;

public class TopTenFragment extends Fragment {
    private GridLayout topTen_LAY_scoreboard;

    private final int NAMES_OFFSET = 10;
    private final int SCORES_OFFSET = 20;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void focusOnMap() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_ten, container, false);
        topTen_LAY_scoreboard = view.findViewById(R.id.topTen_LAY_scoreboard);
        loadLeaderboards();
        return view;
    }

    private void loadLeaderboards() {
        Leaderboards leaderboards = (Leaderboards) MySharedPreferences.getMsp().getObject(MySharedPreferences.KEYS.LEADERBOARDS_KEY, new Leaderboards());

        // 0 - 9 numbers
        // 10-19 Name placements
        // 20-29 Score placements
        for (int i = 0; i < Leaderboards.MAX_SIZE; i++) {
            TextView number = (TextView) topTen_LAY_scoreboard.getChildAt(i);
            TextView name = (TextView) topTen_LAY_scoreboard.getChildAt(i + NAMES_OFFSET);
            TextView score = (TextView) topTen_LAY_scoreboard.getChildAt(i + SCORES_OFFSET);
            Player p = leaderboards.getPlayers().get(i);
            name.setText(p.getName());
            score.setText("" + p.getScore());

            if (p.isValid()) {
                number.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        focusOnMap();
                    }
                });
                name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        focusOnMap();
                    }
                });
                score.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        focusOnMap();
                    }
                });
            }
        }
    }

    private void findViews(View view) {
        topTen_LAY_scoreboard = view.findViewById(R.id.topTen_LAY_scoreboard);
    }


}
