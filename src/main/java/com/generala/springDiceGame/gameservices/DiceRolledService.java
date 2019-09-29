package com.generala.springDiceGame.gameservices;

import com.generala.springDiceGame.models.DiceRolled;
import com.generala.springDiceGame.models.Die;

import java.util.Comparator;
import java.util.Map;

public class DiceRolledService {
    private static DieServices dieServices = new DieServices();
    // other methods

    public static void rollAllDice() {
        Map<Integer, Integer> timesRepeatedEachDice = DiceRolled.getTimesRepeatedEachDice();
        timesRepeatedEachDice.clear();

        int dieSide;

        for (Die die : DiceRolled.getDiceRolled()) {
            dieSide = dieServices.rollDie(die);

            timesRepeatedEachDice.put(dieSide, timesRepeatedEachDice.getOrDefault(dieSide, 0) + 1);
        }

    }

    public static String rolledDiceListToString() {
        DiceRolled.getDiceRolled().sort(Comparator.comparingInt(Die::getDieSide));
        StringBuilder diceRolledSB = new StringBuilder(" ");
        for (Die die : DiceRolled.getDiceRolled()) {
            diceRolledSB.append(die.getDieSide()).append(", ");
        }
        return diceRolledSB.toString();
    }

    static int findFirstValueGreaterThanOrEqualTo(int compareBy) {
        for (Map.Entry<Integer, Integer> entry : DiceRolled.getTimesRepeatedEachDice().entrySet()) {
            if (entry.getValue() >= compareBy) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
