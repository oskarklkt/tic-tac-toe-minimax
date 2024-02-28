package com.griddynamics.tictactoe.Validator;

public class InputValidator {

    private static final String coordinateRegex = "^-?\\d+\\s-?\\d+$";


    public static boolean validateCoordinateInput(String input) {
        return input.matches(coordinateRegex);
    }

}

