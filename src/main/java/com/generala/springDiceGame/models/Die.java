package com.generala.springDiceGame.models;

import com.generala.springDiceGame.constants.CommonConstants;
import com.generala.springDiceGame.utils.GameUtils;

import java.util.concurrent.ThreadLocalRandom;

public class Die {
    private int dieSide;

    public static final int numberOfSides = Integer
            .parseInt(GameUtils.readPropertiesFile().getProperty(CommonConstants.NUMBER_OF_SIDES_STR));

    public Die() {
    }

    public int getDieSide() {
        return dieSide;
    }

    public void setDieSide(int dieSide) {
        this.dieSide = dieSide;
    }
}
