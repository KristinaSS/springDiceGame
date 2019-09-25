package com.generala.springDiceGame;

import com.generala.springDiceGame.constants.CombinationEnum;
import com.generala.springDiceGame.exceptions.IllegalCountException;
import com.generala.springDiceGame.exceptions.IllegalPrinterTypeException;
import com.generala.springDiceGame.models.DiceRolled;
import com.generala.springDiceGame.models.Player;
import com.generala.springDiceGame.models.PlayerRound;
import com.generala.springDiceGame.utils.GameUtils;

import java.util.Collections;
import java.util.List;

public class Game {

    private int rounds;

    private List<Player> playerList;

    //Methods
    private Game() {
    }

    //play or end Game

    public void playGame() {
        DiceRolled.setNumberOfDice();

        PlayerRound currentPlayerRound;


        System.out.println(">>> WELCOME TO THE DICE GAME <<<");

        for (int curRound = 1; curRound <= rounds; curRound++) {
            for (Player player : playerList) {

                currentPlayerRound = new PlayerRound(player, curRound);

                currentPlayerRound.playPlayerRound();

                if (player.getPlayedCombinationsSet().contains(CombinationEnum.GENERALA)) {
                    return;
                }
            }
        }
    }

    public String endGame() {
        Collections.sort(playerList);
        return GameUtils.getEndGameStats(playerList);
    }

    public static class Builder {
        private List<Player> playerList;
        private int rounds;

        public Builder setPlayerList(int playerCount) {

                if(playerCount < 1)
                    throw new IllegalCountException("Your player count needs to be higher than 0");

            this.playerList = GameUtils.fillPlayerList(playerCount);

            return this;
        }

        public Builder setRounds(int rounds) {
                if(rounds < 1)
                    throw new IllegalCountException("Your round count needs to be higher than 0");

            this.rounds = rounds;

            return this;
        }

        public Game build() {
            Game game = new Game();
            game.playerList = this.playerList;
            game.rounds = this.rounds;

            return game;
        }
    }
}