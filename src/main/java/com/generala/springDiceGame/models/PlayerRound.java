package com.generala.springDiceGame.models;


import com.generala.springDiceGame.constants.CombinationEnum;
import com.generala.springDiceGame.gameservices.DiceRolledService;

import java.util.Map;

public class
PlayerRound {
    private int round;

    private Player player;

    private int maxRoundScore;

    private CombinationEnum comboForMaxScore;

    public PlayerRound(Player player, int round) {
        this.round = round;
        this.player = player;
        this.maxRoundScore = 0;
        this.comboForMaxScore = null;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getMaxRoundScore() {
        return maxRoundScore;
    }

    public void setMaxRoundScore(int maxRoundScore) {
        this.maxRoundScore = maxRoundScore;
    }

    public CombinationEnum getComboForMaxScore() {
        return comboForMaxScore;
    }

    public void setComboForMaxScore(CombinationEnum comboForMaxScore) {
        this.comboForMaxScore = comboForMaxScore;
    }
}
