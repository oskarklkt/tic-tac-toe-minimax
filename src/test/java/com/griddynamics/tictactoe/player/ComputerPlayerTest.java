package com.griddynamics.tictactoe.player;

import com.griddynamics.tictactoe.board.Board;
import com.griddynamics.tictactoe.validator.GameValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static com.griddynamics.tictactoe.game.GameConstants.*;
import static org.mockito.AdditionalAnswers.answersWithDelay;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class ComputerPlayerTest {

    @Mock private Board mockBoard;

    @BeforeAll
    static void setUp() {
        Board.SIZE = 3;
    }

    @Test
    void makeMoveEasyTest() {
        //given
        ComputerPlayer computerPlayer = spy(new ComputerPlayer(Difficulty.EASY, X_SIGN));
        doAnswer(answersWithDelay(0, invocation -> null)).when(computerPlayer).randomMove(any(Board.class));

        //when
        computerPlayer.makeMove(mockBoard);

        //then
        verify(computerPlayer, times(1)).easyMove(mockBoard);
        verify(computerPlayer, times(1)).randomMove(mockBoard);
    }

    @Test
    void makeMoveHardTest() {
        //given
        ComputerPlayer computerPlayer = spy(new ComputerPlayer(Difficulty.HARD, X_SIGN));
        doAnswer(answersWithDelay(0, invocation -> null)).when(computerPlayer).hardMove(any(Board.class));

        //when
        computerPlayer.makeMove(mockBoard);

        //then
        verify(computerPlayer, times(1)).hardMove(mockBoard);
    }

    @Test
    @Disabled
    void testRandomMove() {
        //given
        when(mockBoard.getCellStatus(anyInt(), anyInt())).thenReturn(EMPTY_SIGN);
        when(GameValidator.validateCoordinatesRange(anyInt(), anyInt())).thenReturn(eq(true));
        when(GameValidator.validateCellAvailability(anyInt(), anyInt(), mockBoard)).thenReturn(true);
        ComputerPlayer computerPlayer = new ComputerPlayer(Difficulty.EASY, X_SIGN);
        //when
        computerPlayer.randomMove(mockBoard);
        //then
        verify(mockBoard, times(1)).setCellStatus(anyInt(), anyInt(), eq(X_SIGN));
    }


    @Test
    void testMediumMoveRandom() {
        //given
        ComputerPlayer computerPlayer = spy(new ComputerPlayer(Difficulty.MEDIUM, X_SIGN));

        when(mockBoard.getCellStatus(anyInt(), anyInt())).thenReturn(EMPTY_SIGN);

        doNothing().when(computerPlayer).randomMove(mockBoard);
        //when
        computerPlayer.makeMove(mockBoard);

        //then
        verify(computerPlayer, times(1)).randomMove(mockBoard);
    }

    @Test
    void testIsPossibleMoveAntiDiagonal() {
        //given
        Board board = new Board();
        ComputerPlayer computerPlayer1 = new ComputerPlayer(Difficulty.MEDIUM, X_SIGN);
        board.setCellStatus(2, 2, computerPlayer1.sign);
        board.setCellStatus(3, 1, computerPlayer1.sign);
        assertTrue(computerPlayer1.isPossibleMoveAntiDiagonal(board, computerPlayer1.sign));
        board.setCellStatus(1, 3, computerPlayer1.oppositeSign);
        //then
        assertFalse(computerPlayer1.isPossibleMoveAntiDiagonal(board, computerPlayer1.sign));
    }

    @Test
    void testIsPossibleBlock() {
        //given
        Board board = new Board();
        ComputerPlayer computerPlayer1 = new ComputerPlayer(Difficulty.MEDIUM, X_SIGN);
        board.setCellStatus(2, 2, computerPlayer1.oppositeSign);
        board.setCellStatus(1, 2, computerPlayer1.oppositeSign);
        //then
        assertTrue(computerPlayer1.isPossibleBlock(board));
    }

    @Test
    void testMinimaxWinningScenario() {
        //given
        Board board = new Board();
        ComputerPlayer player = new ComputerPlayer(Difficulty.HARD, X_SIGN);
        board.setCellStatus(1, 1, X_SIGN);
        board.setCellStatus(1, 2, X_SIGN);
        board.setCellStatus(1, 3, EMPTY_SIGN);
        //when
        int score = player.minimax(board, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        //then
        assertEquals(1, score, "Minimax should return 1 for a winning scenario.");
    }

    @Test
    void testMinimaxLosingScenario() {
        //given
        Board board = new Board();
        ComputerPlayer player = new ComputerPlayer(Difficulty.HARD, X_SIGN);
        board.setCellStatus(2, 1, O_SIGN);
        board.setCellStatus(2, 2, O_SIGN);
        board.setCellStatus(2, 3, EMPTY_SIGN);
        //when
        int score = player.minimax(board, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
        //then
        assertEquals(-1, score, "Minimax should return -1 for a losing scenario.");
    }

    @Test
    void testMinimaxDrawScenario() {
        //given
        Board board = new Board();
        ComputerPlayer player = new ComputerPlayer(Difficulty.HARD, X_SIGN);
        board.setCellStatus(1, 1, X_SIGN);
        board.setCellStatus(1, 2, O_SIGN);
        board.setCellStatus(1, 3, X_SIGN);
        board.setCellStatus(2, 1, X_SIGN);
        board.setCellStatus(2, 2, X_SIGN);
        board.setCellStatus(2, 3, O_SIGN);
        board.setCellStatus(3, 1, O_SIGN);
        board.setCellStatus(3, 2, X_SIGN);
        board.setCellStatus(3, 3, O_SIGN);
        //when
        int score = player.minimax(board, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        //then
        assertEquals(0, score, "Minimax should return 0 for a draw scenario.");
    }

    @Test
    void testHardMoveWithImmediateWin() {
        //given
        Board board = new Board();
        ComputerPlayer player = new ComputerPlayer(Difficulty.HARD, X_SIGN);
        board.setCellStatus(1, 1, X_SIGN);
        board.setCellStatus(1, 2, X_SIGN);
        //when
        player.hardMove(board);
        //then
        assertEquals(X_SIGN, board.getCellStatus(0, 2));
    }

    @Test
    void testHardMoveWithImmediateBlock() {
        //given
        Board board = new Board();
        ComputerPlayer player = new ComputerPlayer(Difficulty.HARD, X_SIGN);
        board.setCellStatus(2, 1, O_SIGN);
        board.setCellStatus(2, 2, O_SIGN);
        //when
        player.hardMove(board);
        //then
        assertEquals(X_SIGN, board.getCellStatus(0, 0));
    }

    @Test
    void testGetDifficulty() {
        //given
        ComputerPlayer player = new ComputerPlayer(Difficulty.HARD, X_SIGN);
        //then
        assertEquals(Difficulty.HARD, player.getDifficulty());
    }

    @Test
    void testGetOppositeSign() {
        //given
        ComputerPlayer player = new ComputerPlayer(Difficulty.HARD, X_SIGN);
        //then
        assertEquals(O_SIGN, player.getOppositeSign());
    }

    @Test
    void testGetSign() {
        //given
        ComputerPlayer player = new ComputerPlayer(Difficulty.HARD, X_SIGN);
        //then
        assertEquals(X_SIGN, player.getSign());
    }

    @Test
    void testPossibleMoveMainDiagonalWinningMove() {
        //given
        Board board = new Board();
        ComputerPlayer player = new ComputerPlayer(Difficulty.HARD, X_SIGN);
        board.setCellStatus(1, 1, X_SIGN);
        board.setCellStatus(2, 2, X_SIGN);
        board.setCellStatus(3, 3, EMPTY_SIGN);
        //then
        assertTrue(player.isPossibleMoveMainDiagonal(board, X_SIGN));
        assertEquals(X_SIGN, board.getCellStatus(2, 2));
    }

    @Test
    void testPossibleMoveMainDiagonalBlocked() {
        //given
        Board board = new Board();
        ComputerPlayer player = new ComputerPlayer(Difficulty.HARD, X_SIGN);
        board.setCellStatus(1, 1, X_SIGN);
        board.setCellStatus(2, 2, O_SIGN);
        board.setCellStatus(3, 3, X_SIGN);
        //then
        assertFalse(player.isPossibleMoveMainDiagonal(board, X_SIGN));
    }

    @Test
    void testPossibleMoveMainDiagonalEmpty() {
        //given
        Board board = new Board();
        ComputerPlayer player = new ComputerPlayer(Difficulty.HARD, X_SIGN);
        board.setCellStatus(1, 1, EMPTY_SIGN);
        board.setCellStatus(2, 2, EMPTY_SIGN);
        board.setCellStatus(3, 3, EMPTY_SIGN);
        //then
        assertFalse(player.isPossibleMoveMainDiagonal(board, X_SIGN));
    }
}

