package com.generala.springDiceGame.models;

import com.generala.springDiceGame.exceptions.IllegalCountException;
import com.generala.springDiceGame.utils.GameUtils;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int rounds;

    private List<Player> playerList;

    public int getRounds() {
        return rounds;
    }

    public List<Player> getPlayerList() {
        return new ArrayList<>(playerList);
    }

    //Methods
    private Game() {
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