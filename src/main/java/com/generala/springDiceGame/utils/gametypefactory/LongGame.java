package com.generala.springDiceGame.utils.gametypefactory;


import com.generala.springDiceGame.Game;

public class LongGame implements GameType{

    @Override
    public Game buildGame() {
        return new Game.Builder()
                .setRounds(LONG_GAME_ROUNDS)
                .setPlayerList(LONG_GAME_PLAYER_COUNT)
                .build();
    }
}
