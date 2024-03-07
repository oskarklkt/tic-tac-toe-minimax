package com.griddynamics.tictactoe.validator;

import java.util.ArrayList;
import java.util.List;

import static com.griddynamics.tictactoe.messages.InputMessages.*;

public class InputValidator {

  private static final String COORDINATE_REGEX = "^-?\\d+\\s-?\\d+$";

  public static boolean validateCoordinateInput(String input) {
    return input.matches(COORDINATE_REGEX);
  }

  public static boolean validateInputCommand(String input) {
    List<String> possibleCommands = new ArrayList<>(List.of(EASY, MEDIUM, HARD, USER));
    String[] tokens = input.split(WHITESPACE);
    if (tokens[0].equals(EXIT)) {
      return true;
    } else if (tokens[0].equals(START) && tokens.length == 3) {
      return possibleCommands.contains(tokens[1]) && possibleCommands.contains(tokens[2]);
    }
    return false;
  }

  public static boolean validateInputSize(int size) {
    return size > 0;
  }
}
