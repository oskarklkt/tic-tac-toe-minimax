package com.griddynamics.tictactoe.board;

import com.griddynamics.tictactoe.game.GameConstants;
import com.griddynamics.tictactoe.messages.OutputMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        Board.SIZE = 3;
        board = new Board();
    }

    @Test
    void initTest() {
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                char actualValue = board.getCellStatus(row, col);
                assertEquals(GameConstants.EMPTY_SIGN, actualValue,
                        "The cell at [" + row + "][" + col + "] should be initialized to EMPTY_SIGN, but was " + actualValue);
            }
        }
    }

    @Test
    void setCellStatusTest() {
        board.setCellStatus(1,1, GameConstants.O_SIGN);
        assertEquals(GameConstants.O_SIGN, board.getCellStatus(0, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> board.getCellStatus(10, 10));
    }

    @Test
    void isCellEmptyTest() {
        assertTrue(board.isCellEmpty(1,1));
        board.setCellStatus(2,2, GameConstants.X_SIGN);
        assertFalse(board.isCellEmpty(2, 2));
    }

    @Test
    void isBoardFullyOccupiedTest() {
        assertFalse(board.isBoardFullyOccupied());
        board.setCellStatus(1, 1, GameConstants.O_SIGN);
        board.setCellStatus(1, 2, GameConstants.O_SIGN);
        board.setCellStatus(1, 3, GameConstants.X_SIGN);
        board.setCellStatus(2, 1, GameConstants.O_SIGN);
        board.setCellStatus(2, 2, GameConstants.O_SIGN);
        board.setCellStatus(2, 3, GameConstants.X_SIGN);
        board.setCellStatus(3, 1, GameConstants.X_SIGN);
        board.setCellStatus(3, 2, GameConstants.X_SIGN);
        board.setCellStatus(3, 3, GameConstants.O_SIGN);
        assertTrue(board.isBoardFullyOccupied());
    }

    @Test
    void isWinTest() {
        assertFalse(board.isWin(GameConstants.X_SIGN));
        assertFalse(board.isWin(GameConstants.O_SIGN));
        Board boardWinX = new Board();
        boardWinX.setCellStatus(1,1, GameConstants.X_SIGN );
        boardWinX.setCellStatus(1,2, GameConstants.X_SIGN );
        boardWinX.setCellStatus(1,3, GameConstants.X_SIGN );
        assertTrue(boardWinX.isWin(GameConstants.X_SIGN));
        Board boardWinO = new Board();
        boardWinO.setCellStatus(1,1, GameConstants.O_SIGN );
        boardWinO.setCellStatus(2,2, GameConstants.O_SIGN );
        boardWinO.setCellStatus(3,3, GameConstants.O_SIGN );
        assertTrue(boardWinO.isWin(GameConstants.O_SIGN));
        Board boardWinXAntiDiagonal = new Board();
        boardWinXAntiDiagonal.setCellStatus(1,3, GameConstants.X_SIGN );
        boardWinXAntiDiagonal.setCellStatus(2,2, GameConstants.X_SIGN );
        boardWinXAntiDiagonal.setCellStatus(3,1, GameConstants.X_SIGN );
        assertTrue(boardWinXAntiDiagonal.isWin(GameConstants.X_SIGN));


    }

    @Test
    void isDrawTest() {
        assertFalse(board.isDraw());
        board.setCellStatus(1, 1, GameConstants.O_SIGN);
        board.setCellStatus(1, 2, GameConstants.X_SIGN);
        board.setCellStatus(1, 3, GameConstants.X_SIGN);
        board.setCellStatus(2, 1, GameConstants.X_SIGN);
        board.setCellStatus(2, 2, GameConstants.O_SIGN);
        board.setCellStatus(2, 3, GameConstants.O_SIGN);
        board.setCellStatus(3, 1, GameConstants.X_SIGN);
        board.setCellStatus(3, 2, GameConstants.O_SIGN);
        board.setCellStatus(3, 3, GameConstants.X_SIGN);
        assertTrue(board.isDraw());
    }

    @Test
    void checkGameStatusTest() {
        assertEquals(OutputMessages.GAME_NOT_FINISHED, Board.checkGameStatus(board));
        Board boardWinXAntiDiagonal = new Board();
        boardWinXAntiDiagonal.setCellStatus(1,3, GameConstants.X_SIGN );
        boardWinXAntiDiagonal.setCellStatus(2,2, GameConstants.X_SIGN );
        boardWinXAntiDiagonal.setCellStatus(3,1, GameConstants.X_SIGN );
        assertEquals(OutputMessages.X_WINS, Board.checkGameStatus(boardWinXAntiDiagonal));
        Board boardWinO = new Board();
        boardWinO.setCellStatus(1,1, GameConstants.O_SIGN );
        boardWinO.setCellStatus(2,2, GameConstants.O_SIGN );
        boardWinO.setCellStatus(3,3, GameConstants.O_SIGN );
        assertEquals(OutputMessages.O_WINS, Board.checkGameStatus(boardWinO));
        board.setCellStatus(1, 1, GameConstants.O_SIGN);
        board.setCellStatus(1, 2, GameConstants.X_SIGN);
        board.setCellStatus(1, 3, GameConstants.X_SIGN);
        board.setCellStatus(2, 1, GameConstants.X_SIGN);
        board.setCellStatus(2, 2, GameConstants.O_SIGN);
        board.setCellStatus(2, 3, GameConstants.O_SIGN);
        board.setCellStatus(3, 1, GameConstants.X_SIGN);
        board.setCellStatus(3, 2, GameConstants.O_SIGN);
        board.setCellStatus(3, 3, GameConstants.X_SIGN);
        assertEquals(OutputMessages.DRAW, Board.checkGameStatus(board));


    }

    @Test
    void clearCellTest() {
        board.setCellStatus(2,2, GameConstants.X_SIGN);
        assertEquals(GameConstants.X_SIGN ,board.getCellStatus(1,1));
        board.clearCell(1,1);
        assertEquals(GameConstants.EMPTY_SIGN ,board.getCellStatus(1,1));
    }
}