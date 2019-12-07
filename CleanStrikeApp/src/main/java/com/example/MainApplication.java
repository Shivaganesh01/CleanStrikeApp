package com.example;

import com.example.model.Player;
import com.example.util.DataManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Main class which handles game session
 * @author Shivaganesh (shivaganesh7970@gmail.com)
 * @version 1.1
 */
public class MainApplication {
    private static Logger logger = Logger.getLogger("MainApplication");

    public static void main(String[] args) {
        //System.out.println(playGame(2, Arrays.asList(1,2,1,2,1,2)));
    }

    public String playGame(int noOfPlayers, List<Integer> inputData){
        String result;
        Player currentPlayer;
        int strikeValue;

        if(noOfPlayers < 2 || noOfPlayers > 4){
            logger.severe("No.of Players can be 2, 3 or 4");
            return "No.of Players can be 2, 3 or 4";
        }
        DataManager dataManager = new DataManager(noOfPlayers);

        System.out.println("Choose an outcome from the list below");
        System.out.println("1.  Strike");
        System.out.println("2.  Multistrike");
        System.out.println("3.  Red strike");
        System.out.println("4.  Striker strike");
        System.out.println("5.  Defunct coin");
        System.out.println("6.  None");

        for (int i=0; i< inputData.size() && dataManager.totalCoinsCount != 0; i++){
            currentPlayer = dataManager.players.get(i % dataManager.players.size());
            strikeValue = inputData.get(i);
            System.out.println("> "+strikeValue);
            try {
                currentPlayer.playCleanStrike(inputData.get(i), dataManager);
                dataManager.savePlayData(currentPlayer, strikeValue);
            } catch (Exception e) {
                logger.severe(e.getMessage());
            }
            result = dataManager.checkForWinner();
            if(result != null){
                logStatistics(dataManager);
                return result;
            }
        }
        logStatistics(dataManager);
        return "Game ended in a Draw, Play Again :)";
    }

    private void logStatistics(DataManager dataManager){
        logger.info("Play History: "+dataManager.playHistory);
        logger.info("Score History: "+dataManager.scoreHistory);
    }

}


