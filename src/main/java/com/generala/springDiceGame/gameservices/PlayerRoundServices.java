package com.generala.springDiceGame.gameservices;

import com.generala.springDiceGame.constants.CombinationEnum;
import com.generala.springDiceGame.models.DiceRolled;
import com.generala.springDiceGame.models.Die;
import com.generala.springDiceGame.models.Player;
import com.generala.springDiceGame.models.PlayerRound;

import java.util.Map;

import static com.generala.springDiceGame.gameservices.DiceRolledService.findFirstValueGreaterThanOrEqualTo;

public class PlayerRoundServices {

    //public methods

    public String playPlayerRound(PlayerRound playerRound) {
        int oldScore = playerRound.getPlayer().getScore();

        playerRound.getPlayer().rollDice();

        callMethodsToUpdateScore(playerRound);

        if (playerRound.getMaxRoundScore() > 0) {
            playerRound.getPlayer().getPlayedCombinationsSet().add(playerRound.getComboForMaxScore());
            playerRound.getPlayer().setScore(playerRound.getPlayer().getScore() + playerRound.getComboForMaxScore().getValue());
        }
        return printRound(oldScore, playerRound);
    }

    //caller methods

    private void callMethodsToUpdateScore(PlayerRound playerRound) {

        updateScoreIfGenerala(playerRound);

        if (playerRound.getPlayer().getPlayedCombinationsSet().size() >= CombinationEnum.values().length - 1 || playerRound.getMaxRoundScore() > 0)
            return;

        updateScoreIfStraight(playerRound);

        int fourOfAKind = getFourOfAKind();
        int triple = getTriple(fourOfAKind);
        int pair = getPair(triple);
        int secondPair = getSecondPair(pair);

        updateScoreIfFourOfAKind(fourOfAKind, playerRound);

        updateRoundScoreIfTripleValid(triple, playerRound);

        updateRoundScoreIfFullHouseValid(triple, pair, playerRound);

        updateRoundScoreIfPairValid(triple, pair, playerRound);

        updateRoundScoreIfDoublePairValid(pair, secondPair, playerRound);
    }

    private String printRound(int oldScore, PlayerRound playerRound) {
        return ">>> round: " + playerRound.getRound() + "<br>" +
                ">player " + playerRound.getPlayer().getPlayerNumber() + ":" + "<br>" +
                "current score: " + oldScore + "<br>" +
                "dice roll:" + DiceRolledService.rolledDiceListToString() + "-> " + (playerRound.getComboForMaxScore() != null
                ? playerRound.getComboForMaxScore().getLabel()
                : "No combination") +
                " (" + playerRound.getMaxRoundScore() + ") " + "<br>" +
                "new score: " + playerRound.getPlayer().getScore() + "<br>" +
                "<br>";
    }

    private void updateRoundScore(int score, CombinationEnum combo,PlayerRound playerRound) {
        if (score > playerRound.getMaxRoundScore()) {
            playerRound.setMaxRoundScore(score);
            playerRound.setComboForMaxScore(combo);
        }
    }

    //Updating score and getting combo die numbers

    private void updateScoreIfGenerala(PlayerRound playerRound) {
        CombinationEnum generala = CombinationEnum.GENERALA;
        if (DiceRolled.getTimesRepeatedEachDice().size() != 1)
            return;

        if (!playerRound.getPlayer().getPlayedCombinationsSet().contains(generala)) {
            playerRound.setComboForMaxScore(generala);
            playerRound.setMaxRoundScore(generala.calculateCombination(findFirstValueGreaterThanOrEqualTo(generala.getDiceCount())));
        }
    }

    private void updateScoreIfStraight(PlayerRound playerRound) {
        int straightCounter = 0;
        int beginningOfStraight = 0;
        CombinationEnum straight = CombinationEnum.STRAIGHT;
        Map<Integer, Integer> timesRepeatedEachDieSideMap = DiceRolled.getTimesRepeatedEachDice();

        if (timesRepeatedEachDieSideMap.size() < straight.getDiceCount()) //if mapsize == 5
            return;

        if (playerRound.getPlayer().getPlayedCombinationsSet().contains(straight)) //if player has already played straight
            return;

        //System.out.println("Enter straight");
        int i;
        for (i = 0; i < Die.numberOfSides; i++) {
            if (timesRepeatedEachDieSideMap.containsKey(Die.numberOfSides - i)) {
                if (straightCounter == 0) {
                    beginningOfStraight = Die.numberOfSides - i;
                }
                straightCounter++;
            } else
                straightCounter = 0;
            if (straightCounter == straight.getDiceCount()) {
                //int temp = straight.calculateCombination(beginningOfStraight);
                updateRoundScore(straight.calculateCombination(beginningOfStraight), straight, playerRound);
            }
        }
    }

    private void updateScoreIfFourOfAKind(int foundFourOfAKind, PlayerRound playerRound) {
        CombinationEnum fourOfAKind = CombinationEnum.FOUR_OF_A_KIND;
        if (foundFourOfAKind <= 0)
            return;
        if (playerRound.getPlayer().getPlayedCombinationsSet().contains(fourOfAKind))
            return;

        int tempScore = fourOfAKind.calculateCombination(foundFourOfAKind);
        updateRoundScore(tempScore, fourOfAKind, playerRound);
    }

    private void updateRoundScoreIfTripleValid(int tripleDie, PlayerRound playerRound) {
        CombinationEnum triple = CombinationEnum.TRIPLE;
        if (tripleDie <= 0)
            return;
        if (playerRound.getPlayer().getPlayedCombinationsSet().contains(triple))
            return;

        int tempScore = triple.calculateCombination(tripleDie);
        updateRoundScore(tempScore, triple, playerRound);
    }

    private void updateRoundScoreIfFullHouseValid(int triple, int pair, PlayerRound playerRound) {
        CombinationEnum fullHouse = CombinationEnum.FULL_HOUSE;
        if (triple <= 0 || pair <= 0)
            return;
        if (playerRound.getPlayer().getPlayedCombinationsSet().contains(fullHouse))
            return;

        int tempScore = fullHouse.calculateCombination((3 * triple) + (2 * pair));
        updateRoundScore(tempScore, fullHouse,playerRound);
    }

    private void updateRoundScoreIfPairValid(int tripleDie, int pairDie, PlayerRound playerRound) {
        CombinationEnum pair = CombinationEnum.PAIR;
        int tempScore;
        if (playerRound.getPlayer().getPlayedCombinationsSet().contains(pair))
            return;
        if (tripleDie > 0 && tripleDie > pairDie) {
            tempScore = pair.calculateCombination(tripleDie);
            updateRoundScore(tempScore, pair,playerRound);
        } else if (pairDie > 0) {
            tempScore = pair.calculateCombination(pairDie);
            updateRoundScore(tempScore, pair, playerRound);
        }
    }

    private void updateRoundScoreIfDoublePairValid(int pair, int secondPair, PlayerRound playerRound) {
        CombinationEnum doublePair = CombinationEnum.DOUBLE_PAIR;
        if (secondPair > 0 && !(playerRound.getPlayer().getPlayedCombinationsSet().contains(doublePair))) {
            int tempScore = doublePair.calculateCombination(pair + secondPair);
            updateRoundScore(tempScore, doublePair ,playerRound);
        }
    }

    private int getFourOfAKind() {
        System.out.println(CombinationEnum.FOUR_OF_A_KIND.getDiceCount());
        return findFirstValueGreaterThanOrEqualTo(CombinationEnum.FOUR_OF_A_KIND.getDiceCount());
    }

    private int getTriple(int fourOfAKind) {
        int triple = findFirstValueGreaterThanOrEqualTo(CombinationEnum.TRIPLE.getDiceCount());
        if (fourOfAKind > triple)
            triple = fourOfAKind;
        return triple;
    }

    private int getPair(int triple) {
        DiceRolled.getTimesRepeatedEachDice().remove(triple);
        return findFirstValueGreaterThanOrEqualTo(CombinationEnum.PAIR.getDiceCount());
    }

    private int getSecondPair(int pair) {
        if (pair > 0) {
            DiceRolled.getTimesRepeatedEachDice().remove(pair);
            return findFirstValueGreaterThanOrEqualTo(CombinationEnum.DOUBLE_PAIR.getDiceCount());
        }
        return -1;
    }
}
