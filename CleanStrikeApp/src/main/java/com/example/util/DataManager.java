package com.example.util;

import com.example.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class maintains the game rules, play history, score history, gives winner details
 */
public class DataManager {
    public Map<String, List<Integer>> playHistory = new HashMap<>(); // Stores players turn outcome history
    public Map<String, List<Integer>> scoreHistory = new HashMap<>();// Stores players score in each turn
    public int blackCoinsCount = 9;
    public int redCoinsCount = 1;
    public int totalCoinsCount = blackCoinsCount + redCoinsCount;
    public List<Player> players = new ArrayList<>();
    private int noOfPlayers;

    // Strike outcomes and its associated points
    public enum StrikerOutcomeType{
        STRIKE (1, 1, 1),
        MULTI_STRIKE(2, 2, 2),
        RED_STRIKE(3, 3, 1),
        STRIKER_STRIKE(4, -1, 0),
        DEFUNCT_COIN(5, -2, 1),
        NONE(6, 0, 0);

        public final int optionNo; // Option no as displayed in the beginning
        public final int points; // Points credited to the player for the outcome
        public final int effectiveCoinsRemoved; // No of coins removed from the table due to the outcome

        StrikerOutcomeType(int optionNo, int points, int effectiveCoinsRemoved) {
            this.optionNo = optionNo;
            this.points = points;
            this.effectiveCoinsRemoved = effectiveCoinsRemoved;
        }
    }

    public DataManager(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
        initialize();
    }

    // This function initializes the players data
    private void initialize(){
        for (int i=1; i<=noOfPlayers; i++){
            players.add(new Player("Player "+i));
        }
        players.forEach(player -> {
            playHistory.put(player.getName(), new ArrayList<>(1));
            scoreHistory.put(player.getName(), new ArrayList<>(1));
        });

    }
    // This function checks for the winner and returns the winning score detail
    public String checkForWinner(){
        Player first = players.get(0);
        Player second = players.get(1);
        for(int i=0; i< players.size(); i++){
            if(players.get(i).getPoints()>first.getPoints()){
                second = first;
                first = players.get(i);
            }
        }
        if(first.getPoints() >= 5 && first.getPoints()>=second.getPoints()+3) {
            return first.getName() + " won the game. Final Score: "+first.getPoints()+" - "+second.getPoints();
        }else {
            return null;
        }
    }

    // This function saves play history and score history of the players
    public void savePlayData(Player player, int strikeValue){
        List<Integer> data = playHistory.get(player.getName());
        data.add(strikeValue);
        playHistory.put(player.getName(), data);
        data = scoreHistory.get(player.getName());
        data.add(player.getPoints());
        scoreHistory.put(player.getName(), data);
        totalCoinsCount = blackCoinsCount + redCoinsCount;
    }
}
