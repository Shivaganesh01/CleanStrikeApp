package com.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestCases {

    @Test
    public void player1WinTest(){
        Assert.assertEquals("Player 1 won the game. Final Score: 6 - 3",new MainApplication().playGame(2, Arrays.asList(1,1,1,1,1,1,3,1,1)));
    }

    @Test
    public void player2WinTest(){
        Assert.assertEquals("Player 2 won the game. Final Score: 6 - 3",new MainApplication().playGame(2, Arrays.asList(1,2,1,2,1,2)));
    }

    @Test
    public void drawMatchTest1(){
        Assert.assertEquals("Game ended in a Draw, Play Again :)",new MainApplication().playGame(2, Arrays.asList(1,1,1,1,1,1,1,1,1,3)));
    }

    @Test
    public void drawMatchTest2(){
        Assert.assertEquals("Game ended in a Draw, Play Again :)",new MainApplication().playGame(2, Arrays.asList(2,2,2,2,2)));
    }

    @Test
    public void failedto_pocketcoin_by_player1_looses_an_additional_point(){
        Assert.assertEquals("Player 2 won the game. Final Score: 5 - 1",new MainApplication().playGame(2, Arrays.asList(6,1,6,1,6,1,1,1,1,1)));
    }

    @Test
    public void foul_by_player2_looses_an_additional_point(){
        Assert.assertEquals("Player 1 won the game. Final Score: 5 - -7",new MainApplication().playGame(2, Arrays.asList(1,5,1,4,1,6,1,5,1,6)));
    }

    @Test
    public void player3WinTest(){
        Assert.assertEquals("Player 3 won the game. Final Score: 5 - 2",new MainApplication().playGame(3, Arrays.asList(1,1,3,1,1,2)));
    }

    @Test
    public void player4WinTest(){
        Assert.assertEquals("Player 4 won the game. Final Score: 5 - 2",new MainApplication().playGame(4, Arrays.asList(1,1,1,3,1,6,1,2)));
    }

    @Test
    public void playerCountTest(){
        Assert.assertEquals("No.of Players can be 2, 3 or 4",new MainApplication().playGame(1, Arrays.asList(1,1,1,1,1,1,3,1,1)));
        Assert.assertEquals("No.of Players can be 2, 3 or 4",new MainApplication().playGame(5, Arrays.asList(1,1,1,1,1,1,3,1,1)));
    }

}
