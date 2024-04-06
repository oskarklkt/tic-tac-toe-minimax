package com.griddynamics.tictactoe.player;

import com.griddynamics.tictactoe.board.Board;

public interface Player {
  char getSign();

  void makeMove(Board board);
}
