package com.griddynamics.tictactoe.Player;
import com.griddynamics.tictactoe.Board;
import com.griddynamics.tictactoe.InputExceptions.InputAttemptsException;

public interface Player {
    void makeMove(Board board) throws InputAttemptsException;

}
