package com.generala.springDiceGame.gameservices;

import com.generala.springDiceGame.constants.CombinationEnum;
import com.generala.springDiceGame.models.DiceRolled;
import com.generala.springDiceGame.models.Player;
import com.generala.springDiceGame.models.PlayerRound;
import com.generala.springDiceGame.utils.GameUtils;

import java.util.Collections;
import java.util.List;

public class GameService {
    //play or end Game

    public String playGame(int rounds, List<Player> playerList) {
        StringBuilder gameRounds =new StringBuilder();

        DiceRolled.setNumberOfDice();

        PlayerRound currentPlayerRound;


        gameRounds.append(">>> WELCOME TO THE DICE GAME <<<" + "<br>");
        PlayerRoundServices playerRoundServices = new PlayerRoundServices();

        for (int curRound = 1; curRound <= rounds; curRound++) {
            for (Player player : playerList) {

                currentPlayerRound = new PlayerRound(player, curRound);

                gameRounds.append(playerRoundServices.playPlayerRound(currentPlayerRound));

                if (player.getPlayedCombinationsSet().contains(CombinationEnum.GENERALA)) {
                    return gameRounds.toString();
                }
            }
        }
        return gameRounds.toString();
    }

    public String endGame(List<Player>playerList) {
        Collections.sort(playerList);
        return GameUtils.getEndGameStats(playerList);
    }
}
