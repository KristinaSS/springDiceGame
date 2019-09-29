package com.generala.springDiceGame.utils.gametypefactory;


import com.generala.springDiceGame.models.Game;
import com.generala.springDiceGame.exceptions.IllegalCountException;

public class CustomGame implements GameType{
    private static int customGamePlayerCount;
    private static int customGameRounds;
    @Override
    public Game buildGame() {
        if (customGamePlayerCount < 1)
            throw new IllegalCountException("Your player count must be larger than 0");
        if (customGameRounds < 1)
            throw new IllegalCountException("Your round count must be larger than 0");

        return new Game.Builder()
                .setRounds(customGameRounds)
                .setPlayerList(customGamePlayerCount)
                .build();
    }

    public static void setCustomGamePlayerCount(int customGamePlayerCount) {
        CustomGame.customGamePlayerCount = customGamePlayerCount;
    }

    public static void setCustomGameRounds(int customGameRounds) {
        CustomGame.customGameRounds = customGameRounds;
    }
}
