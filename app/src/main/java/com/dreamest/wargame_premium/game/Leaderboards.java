package com.dreamest.wargame_premium.game;

import java.util.ArrayList;
import java.util.Collections;

public class Leaderboards {
    public static final int MAX_SIZE = 10;


    private ArrayList<Player> players;
    private int lowestScore;

    public Leaderboards() {
        this.lowestScore = 0;
        players = new ArrayList<>();
        for(int i = 0; i < MAX_SIZE; i++){
            players.add(new Player(0, "", false));
        }

    }
    public static int getMaxSize() {
        return MAX_SIZE;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getLowestScore() {
        return lowestScore;
    }

    public void setLowestScore(int lowestScore) {
        this.lowestScore = lowestScore;
    }

    private int compareScore(Player p1, Player p2) {
        return p2.getScore() - p1.getScore();
    }

    public void updateLeaderboards(Player newPlayer){
        players.add(newPlayer);
        Collections.sort(players, this::compareScore);
        if(players.size() > MAX_SIZE)
            players.remove(MAX_SIZE);
        lowestScore = players.get(MAX_SIZE -1).getScore();
    }
}
