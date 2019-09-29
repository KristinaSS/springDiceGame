package com.generala.springDiceGame.gameservices;

import com.generala.springDiceGame.models.Die;

import java.util.concurrent.ThreadLocalRandom;

import static com.generala.springDiceGame.models.Die.*;

class DieServices {
    int rollDie(Die die) {
        die.setDieSide(ThreadLocalRandom.current().nextInt(numberOfSides) + 1);
        return die.getDieSide();
    }
}
