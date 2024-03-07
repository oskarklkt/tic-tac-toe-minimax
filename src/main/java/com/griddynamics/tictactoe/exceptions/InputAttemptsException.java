package com.griddynamics.tictactoe.exceptions;

public class InputAttemptsException extends RuntimeException {

  private static final String message =
      "You exceeded allowed number of attempts to provide correct coordinates";

  public InputAttemptsException() {
    super(message);
  }
}
