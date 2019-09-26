package com.generala.springDiceGame.utils.gametypefactory;


import com.generala.springDiceGame.Game;
import com.generala.springDiceGame.constants.CommonConstants;
import com.generala.springDiceGame.utils.GameUtils;

public interface GameType {
    int SHORT_GAME_ROUNDS = 10;
    int LONG_GAME_ROUNDS = 50;
    //int customGameRounds = Integer.parseInt(GameUtils.getPropertyFromPropFile(CommonConstants.ROUND_COUNT_STR));

    int SHORT_GAME_PLAYER_COUNT = 8;
    int LONG_GAME_PLAYER_COUNT = 20;
    //int customGamePlayerCount = Integer.parseInt(GameUtils.getPropertyFromPropFile(CommonConstants.PLAYER_COUNT_STR));


    Game buildGame();
}
