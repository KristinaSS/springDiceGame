package com.generala.springDiceGame.models;

import com.generala.springDiceGame.constants.CommonConstants;
import com.generala.springDiceGame.exceptions.IllegalCountException;
import com.generala.springDiceGame.utils.GameUtils;

import java.util.*;

public class DiceRolled {
    private static int numberOfDice;

    private final static List<Die> diceRolled = new ArrayList<>();

    private final static Map<Integer, Integer> timesRepeatedEachDice = new TreeMap<>(Collections.reverseOrder());

    private DiceRolled() {
    }

    public static Map<Integer, Integer> getTimesRepeatedEachDice() {
        return new TreeMap<>(timesRepeatedEachDice);
    }

    public static int getNumberOfDice() {
        return numberOfDice;
    }

    public static void setNumberOfDice() {
        DiceRolled.numberOfDice = Integer.parseInt(GameUtils.getPropertyFromPropFile(CommonConstants.DICE_COUNT_STR));
        if (numberOfDice < 1)
            throw new IllegalCountException("Your dice count needs to be larger than 0");

        for (int i = 0; i < numberOfDice; i++) {
            diceRolled.add(new Die());
        }
    }

    public static List<Die> getDiceRolled() {
        return diceRolled;
    }
}
