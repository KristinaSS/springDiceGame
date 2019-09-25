package com.generala.springDiceGame.controllers;

import com.generala.springDiceGame.Game;
import com.generala.springDiceGame.exceptions.IllegalCountException;
import com.generala.springDiceGame.exceptions.IllegalGameTypeException;
import com.generala.springDiceGame.exceptions.IllegalPrinterTypeException;
import com.generala.springDiceGame.utils.gametypefactory.GameType;
import com.generala.springDiceGame.utils.gametypefactory.GameTypeFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    @RequestMapping("/")
    public String home(){
        return "Welcome to Generala Dice game <br>" +
                "write /shortGame to generate a short game <br>" +
                "write /longGame to generate a long game <br>";
    }

    @RequestMapping("/shortGame")
    public String playShortGame(){
        String gameTypeString = "short";
        return playGameType(gameTypeString);
    }

    @RequestMapping("/longGame")
    public String playLongGame(){
        String gameTypeString = "long";
        return playGameType(gameTypeString);
    }

    private String playGameType(String gameTypeStr){
        GameType gameType = new GameTypeFactory().getGameType(gameTypeStr);
        Game diceGame;
        try{
            if(gameType != null) {
                diceGame =gameType.buildGame();
                diceGame.playGame();
            }else
                throw new IllegalGameTypeException("There is no such game: " + gameTypeStr);

        }catch (IllegalGameTypeException e){
            return "An IllegalGameTypeException was thrown :" + e.getMessage();
        }catch (IllegalPrinterTypeException e){
            return "An IllegalPrinterTypeException was thrown :"+ e.getMessage();
        }catch (IllegalCountException e){
            return "An IllegalCountException was thrown! :" + e.getMessage();
        }
        return diceGame.endGame();
    }
}
