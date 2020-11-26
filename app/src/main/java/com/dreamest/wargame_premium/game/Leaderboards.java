package com.dreamest.wargame_premium.game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leaderboards implements Comparator<Player> {
    private ArrayList<Player> players;
    private int lowestScore;

    public Leaderboards() {

    }

    @Override
    public int compare(Player p1, Player p2) {
        return p1.getScore() - p2.getScore();
    }

    public void updateLeaderboards(Player newPlayer){
        if(newPlayer.getScore() <= lowestScore)
            return;
        players.add(newPlayer);
        Collections.sort(players, this::compare);
        if(players.size() > 10)
            players.remove(10);
    }
}
