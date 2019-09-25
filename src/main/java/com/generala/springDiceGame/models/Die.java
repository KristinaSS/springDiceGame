package com.generala.springDiceGame.models;

import com.generala.springDiceGame.constants.CommonConstants;
import com.generala.springDiceGame.utils.GameUtils;

import java.util.concurrent.ThreadLocalRandom;

class Die {
    private int dieSide;

    static final int numberOfSides = Integer
            .parseInt(GameUtils.readPropertiesFile().getProperty(CommonConstants.NUMBER_OF_SIDES_STR));

    Die() {
    }

    int getDieSide() {
        return dieSide;
    }

    int rollDie() {
        dieSide = ThreadLocalRandom.current().nextInt(numberOfSides) + 1;
        return dieSide;
    }

}
