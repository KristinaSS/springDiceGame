package com.generala.springDiceGame.utils.gametypefactory;

import com.generala.springDiceGame.models.Game;

public class ShortGame implements GameType{
    @Override
    public Game buildGame() {
        return new Game.Builder()
                .setRounds(SHORT_GAME_ROUNDS)
                .setPlayerList(SHORT_GAME_PLAYER_COUNT)
                .build();
    }
}
