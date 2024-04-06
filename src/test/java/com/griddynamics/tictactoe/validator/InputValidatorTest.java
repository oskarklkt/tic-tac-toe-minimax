package com.griddynamics.tictactoe.validator;

import com.griddynamics.tictactoe.messages.InputMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class InputValidatorTest {

    @Test
    void validateCoordinateInput() {
        assertTrue(InputValidator.validateCoordinateInput("1 3"));
        assertFalse(InputValidator.validateCoordinateInput("1"));
        assertFalse(InputValidator.validateCoordinateInput("1 "));
        assertFalse(InputValidator.validateCoordinateInput(""));
        assertFalse(InputValidator.validateCoordinateInput("s"));
        assertFalse(InputValidator.validateCoordinateInput("one three"));
        assertFalse(InputValidator.validateCoordinateInput("1 3 3"));
        assertFalse(InputValidator.validateCoordinateInput("1 three"));
    }

    @Test
    void validateInputCommand() {
        assertTrue(InputValidator.validateInputCommand(InputMessages.EXIT));
        assertTrue(InputValidator.validateInputCommand(InputMessages.START + " " + InputMessages.EASY +
                " " + InputMessages.HARD));
        assertFalse(InputValidator.validateInputCommand(InputMessages.START));
        assertFalse(InputValidator.validateInputCommand(InputMessages.START + " " + InputMessages.HARD));
      }

    @Test
    void validateInputSize() {
        assertFalse(InputValidator.validateInputSize(-12));
        assertTrue(InputValidator.validateInputSize(3));
        assertFalse(InputValidator.validateInputSize(0));
      }
}