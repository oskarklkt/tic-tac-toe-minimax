package com.griddynamics.tictactoe.exceptions;

public class WrongPlayerTypeException extends RuntimeException {

    private static final String message = "Wrong Player type!";
    public WrongPlayerTypeException() {
        super(message);
    }
}
