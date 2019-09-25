package com.generala.springDiceGame.exceptions;

public class IllegalCountException extends RuntimeException {
    private IllegalCountException(){
        super();
    }
    public IllegalCountException(String message){
        super(message);
    }
}
