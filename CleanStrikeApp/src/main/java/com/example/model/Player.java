package com.example.model;

import com.example.util.DataManager;

import static com.example.util.DataManager.StrikerOutcomeType.*;

/**
 * Player class has the player detail
 */
public class Player {

    private String name;
    private int points = 0;
    private int foulCount = 0;
    private int failedToPocketCoin = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    private void setPoints(int points) {
        this.points = points;
    }

    private int getFoulCount() {
        return foulCount;
    }

    private void setFoulCount(int foulCount) {
        this.foulCount = foulCount;
    }

    private int getFailedToPocketCoin() {
        return failedToPocketCoin;
    }

    private void setFailedToPocketCoin(int failedToPocketCoin) {
        this.failedToPocketCoin = failedToPocketCoin;
    }

    public void playCleanStrike(int strikeValue, DataManager dataManager) throws Exception{
        int foulData = this.getFoulCount();
        int blackCount = dataManager.blackCoinsCount;
        int redCount = dataManager.redCoinsCount;
        int finalPoints;
        int failedToPocketCoinCount = this.getFailedToPocketCoin();
        if(strikeValue == STRIKE.optionNo){
            finalPoints = this.getPoints() + STRIKE.points;
            blackCount-=STRIKE.effectiveCoinsRemoved;
            failedToPocketCoinCount = 0;
        }else if(strikeValue == MULTI_STRIKE.optionNo){
            finalPoints = this.getPoints() + MULTI_STRIKE.points;
            blackCount-=MULTI_STRIKE.effectiveCoinsRemoved;
            failedToPocketCoinCount = 0;
        }else if(strikeValue == RED_STRIKE.optionNo){
            finalPoints = this.getPoints() + RED_STRIKE.points;
            redCount -= RED_STRIKE.effectiveCoinsRemoved;
            failedToPocketCoinCount = 0;
        }else if(strikeValue == STRIKER_STRIKE.optionNo){
            finalPoints = this.getPoints() + STRIKER_STRIKE.points;
            blackCount-=STRIKER_STRIKE.effectiveCoinsRemoved;
            foulData += 1;
            failedToPocketCoinCount += 1;
        }else if(strikeValue == DEFUNCT_COIN.optionNo){
            finalPoints = this.getPoints() + DEFUNCT_COIN.points;
            blackCount-=DEFUNCT_COIN.effectiveCoinsRemoved;
            foulData += 1;
            failedToPocketCoinCount += 1;
        }else if(strikeValue == NONE.optionNo){
            finalPoints = this.getPoints() + NONE.points;
            blackCount-=NONE.effectiveCoinsRemoved;
            failedToPocketCoinCount += 1;
        }else {
            throw new Exception("Invalid Option"); // Invalid option number. only 1-6(associated with striker outcome) are valid
        }
        if(blackCount < 0 || redCount < 0){
            throw new Exception("Cheating..."); // Thrown when no coin is available in the table and such player looses his that chance
        }
        if(foulData == 3){                      // Remove a point from the player score on 3 fouls
            finalPoints -= 1;
            foulData = 0;
        }
        if(failedToPocketCoinCount == 3){       // Remove a point from the player score on 3 successive failure to pocket coin
            finalPoints -= 1;
            failedToPocketCoinCount = 0;
        }
        this.setFailedToPocketCoin(failedToPocketCoinCount);
        this.setFoulCount(foulData);
        this.setPoints(finalPoints);
        dataManager.blackCoinsCount = blackCount;
        dataManager.redCoinsCount = redCount;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}
