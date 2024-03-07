package com.griddynamics.tictactoe.exceptions;

public class InputSizeException extends RuntimeException {
  private static final String message = "Size must be greater than 0";

  public InputSizeException() {
    super(message);
  }
}
