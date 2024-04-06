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
        //given
        board.setCellStatus(1,1, GameConstants.O_SIGN);
        //then
        assertEquals(GameConstants.O_SIGN, board.getCellStatus(0, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> board.getCellStatus(10, 10));
    }

    @Test
    void isCellEmptyTest() {
        assertTrue(board.isCellEmpty(1,1));
        //given
        board.setCellStatus(2,2, GameConstants.X_SIGN);
        //then
        assertFalse(board.isCellEmpty(2, 2));
    }

    @Test
    void isBoardFullyOccupiedTest() {
        assertFalse(board.isBoardFullyOccupied());
        //given
        board.setCellStatus(1, 1, GameConstants.O_SIGN);
        board.setCellStatus(1, 2, GameConstants.O_SIGN);
        board.setCellStatus(1, 3, GameConstants.X_SIGN);
        board.setCellStatus(2, 1, GameConstants.O_SIGN);
        board.setCellStatus(2, 2, GameConstants.O_SIGN);
        board.setCellStatus(2, 3, GameConstants.X_SIGN);
        board.setCellStatus(3, 1, GameConstants.X_SIGN);
        board.setCellStatus(3, 2, GameConstants.X_SIGN);
        board.setCellStatus(3, 3, GameConstants.O_SIGN);
        //then
        assertTrue(board.isBoardFullyOccupied());
    }

    @Test
    void isWinTest() {
        assertFalse(board.isWin(GameConstants.X_SIGN));
        assertFalse(board.isWin(GameConstants.O_SIGN));
        //given
        Board boardWinX = new Board();
        boardWinX.setCellStatus(1,1, GameConstants.X_SIGN );
        boardWinX.setCellStatus(1,2, GameConstants.X_SIGN );
        boardWinX.setCellStatus(1,3, GameConstants.X_SIGN );
        //then
        assertTrue(boardWinX.isWin(GameConstants.X_SIGN));
        //given
        Board boardWinO = new Board();
        boardWinO.setCellStatus(1,1, GameConstants.O_SIGN );
        boardWinO.setCellStatus(2,2, GameConstants.O_SIGN );
        boardWinO.setCellStatus(3,3, GameConstants.O_SIGN );
        //then
        assertTrue(boardWinO.isWin(GameConstants.O_SIGN));
        //given
        Board boardWinXAntiDiagonal = new Board();
        boardWinXAntiDiagonal.setCellStatus(1,3, GameConstants.X_SIGN );
        boardWinXAntiDiagonal.setCellStatus(2,2, GameConstants.X_SIGN );
        boardWinXAntiDiagonal.setCellStatus(3,1, GameConstants.X_SIGN );
        //then
        assertTrue(boardWinXAntiDiagonal.isWin(GameConstants.X_SIGN));


    }

    @Test
    void isDrawTest() {
        assertFalse(board.isDraw());
        //given
        board.setCellStatus(1, 1, GameConstants.O_SIGN);
        board.setCellStatus(1, 2, GameConstants.X_SIGN);
        board.setCellStatus(1, 3, GameConstants.X_SIGN);
        board.setCellStatus(2, 1, GameConstants.X_SIGN);
        board.setCellStatus(2, 2, GameConstants.O_SIGN);
        board.setCellStatus(2, 3, GameConstants.O_SIGN);
        board.setCellStatus(3, 1, GameConstants.X_SIGN);
        board.setCellStatus(3, 2, GameConstants.O_SIGN);
        board.setCellStatus(3, 3, GameConstants.X_SIGN);
        //then
        assertTrue(board.isDraw());
    }

    @Test
    void checkGameStatusTest() {
        assertEquals(OutputMessages.GAME_NOT_FINISHED, Board.checkGameStatus(board));
        //given
        Board boardWinXAntiDiagonal = new Board();
        boardWinXAntiDiagonal.setCellStatus(1,3, GameConstants.X_SIGN );
        boardWinXAntiDiagonal.setCellStatus(2,2, GameConstants.X_SIGN );
        boardWinXAntiDiagonal.setCellStatus(3,1, GameConstants.X_SIGN );
        //then
        assertEquals(OutputMessages.X_WINS, Board.checkGameStatus(boardWinXAntiDiagonal));
        //given
        Board boardWinO = new Board();
        boardWinO.setCellStatus(1,1, GameConstants.O_SIGN );
        boardWinO.setCellStatus(2,2, GameConstants.O_SIGN );
        boardWinO.setCellStatus(3,3, GameConstants.O_SIGN );
        //then
        assertEquals(OutputMessages.O_WINS, Board.checkGameStatus(boardWinO));
        //given
        board.setCellStatus(1, 1, GameConstants.O_SIGN);
        board.setCellStatus(1, 2, GameConstants.X_SIGN);
        board.setCellStatus(1, 3, GameConstants.X_SIGN);
        board.setCellStatus(2, 1, GameConstants.X_SIGN);
        board.setCellStatus(2, 2, GameConstants.O_SIGN);
        board.setCellStatus(2, 3, GameConstants.O_SIGN);
        board.setCellStatus(3, 1, GameConstants.X_SIGN);
        board.setCellStatus(3, 2, GameConstants.O_SIGN);
        board.setCellStatus(3, 3, GameConstants.X_SIGN);
        //then
        assertEquals(OutputMessages.DRAW, Board.checkGameStatus(board));


    }

    @Test
    void clearCellTest() {
        //given
        board.setCellStatus(2,2, GameConstants.X_SIGN);
        //then
        assertEquals(GameConstants.X_SIGN ,board.getCellStatus(1,1));
        //when
        board.clearCell(1,1);
        //then
        assertEquals(GameConstants.EMPTY_SIGN ,board.getCellStatus(1,1));
    }

    @Test
    void printBoardTest() {
        //given
        String pattern =
                """
                        ---------
                        |       |
                        |       |
                        |       |
                        ---------""";
        //then
        assertEquals(pattern, board.printBoard());
    }
}