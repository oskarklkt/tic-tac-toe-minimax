package com.griddynamics.tictactoe.validator;

import com.griddynamics.tictactoe.board.Board;
import com.griddynamics.tictactoe.game.GameConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameValidatorTest {

    private Board board;

    @BeforeEach
    void setUp() {
        Board.SIZE = 3;
        board = new Board();
    }

    @Test
    void validateCellAvailability() {
            assertTrue(GameValidator.validateCellAvailability(1, 1, this.board));
            //given
            board.setCellStatus(1, 1, GameConstants.X_SIGN);
            //then
            assertFalse(GameValidator.validateCellAvailability(1, 1, board));
      }

    @Test
    void validateCoordinatesRange() {
            assertTrue(GameValidator.validateCoordinatesRange(2, 2));
            assertFalse(GameValidator.validateCoordinatesRange(222222, 2341));
      }
}