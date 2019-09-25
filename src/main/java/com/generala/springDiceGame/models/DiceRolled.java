package com.generala.springDiceGame.models;

import com.generala.springDiceGame.constants.CommonConstants;
import com.generala.springDiceGame.exceptions.IllegalCountException;
import com.generala.springDiceGame.utils.GameUtils;

import java.util.*;

public class DiceRolled {
    private static int numberOfDice;

    private final static List<Die> diceRolled = new ArrayList<>();

    private final static Map<Integer, Integer> timesRepeatedEachDice = new TreeMap<>(Collections.reverseOrder());

 /*   static {
        for (int i = 0; i < numberOfDice; i++) {
            diceRolled.add(new Die());
        }
    }*/
    // getters and setters and constructors

    private DiceRolled() {
    }

    static Map<Integer, Integer> getTimesRepeatedEachDice() {
        return timesRepeatedEachDice;
    }

    public static int getNumberOfDice() {
        return numberOfDice;
    }

    public static void setNumberOfDice() {
        DiceRolled.numberOfDice = Integer.parseInt(GameUtils.getPropertyFromPropFile(CommonConstants.DICE_COUNT_STR));

        if(numberOfDice<1)
            throw new IllegalCountException("Your dice count needs to be larger than 0");

        for (int i = 0; i < numberOfDice; i++) {
            diceRolled.add(new Die());
        }
    }

    // other methods

    static void rollAllDice() {
        timesRepeatedEachDice.clear();

        int dieSide;

        for (Die die : diceRolled) {
            dieSide = die.rollDie();

            timesRepeatedEachDice.put(dieSide, timesRepeatedEachDice.getOrDefault(dieSide, 0) + 1);
        }

    }

    static String rolledDiceListToString() {
        diceRolled.sort(Comparator.comparingInt(Die::getDieSide));
        StringBuilder diceRolledSB = new StringBuilder(" ");
        for (Die die : diceRolled) {
            diceRolledSB.append(die.getDieSide()).append(", ");
        }
        return diceRolledSB.toString();
    }

    static int findFirstValueGreaterThanOrEqualTo(int compareBy) {
        for (Map.Entry<Integer, Integer> entry : timesRepeatedEachDice.entrySet()) {
            if (entry.getValue() >= compareBy) {
                return entry.getKey();
            }
        }
        return -1;
    }

}
