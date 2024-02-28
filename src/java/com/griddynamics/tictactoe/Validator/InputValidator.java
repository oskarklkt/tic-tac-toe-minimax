package com.griddynamics.tictactoe.Validator;

public class InputValidator {

    private static final String coordinateRegex = "^-?\\d+\\s-?\\d+$";

    private static final String initialStateRegex = "^[XO_]{9}$";

    public static boolean validateCoordinateInput(String input) {
        return input.matches(coordinateRegex);
    }

    public static boolean validateInitialState(String input) {
        return input.matches(initialStateRegex);
    }
}

