package com.griddynamics.tictactoe.Validator;


import java.util.ArrayList;
import java.util.List;

import static com.griddynamics.tictactoe.Messages.InputMessages.*;

public class InputValidator {

    private static final String COORDINATE_REGEX = "^-?\\d+\\s-?\\d+$";



    public static boolean validateCoordinateInput(String input) {
        return input.matches(COORDINATE_REGEX);
    }

    public static boolean validateInputCommand(String input) {
        ArrayList<String> possibleCommands = new ArrayList<>(List.of(EASY, MEDIUM, HARD, USER));
        if (input.split(WHITESPACE)[0].equals(EXIT)) {
            return true;
        } else if (input.split(WHITESPACE)[0].equals(START)) {
            if (input.split(WHITESPACE).length == 3) {
                String[] command = input.split(WHITESPACE);
                return possibleCommands.contains(command[1]) && possibleCommands.contains(command[2]);
            }
        }
        return false;
    }

}

