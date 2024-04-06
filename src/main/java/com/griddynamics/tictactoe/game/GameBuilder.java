package com.griddynamics.tictactoe.game;

import com.griddynamics.tictactoe.player.Player;
import com.griddynamics.tictactoe.board.Board;
import com.griddynamics.tictactoe.player.PlayerFactory;

public class GameBuilder {

  private Player player1;
  private Player player2;
  private Board board;

  public GameBuilder setPlayer1(String type, char sign) {
    this.player1 = new PlayerFactory().createPlayer(type, sign);
    return this;
  }

  public GameBuilder setPlayer2(String type, char sign) {
    this.player2 = new PlayerFactory().createPlayer(type, sign);
    return this;
  }

  public GameBuilder setSize(int size) {
    Board.SIZE = size;
    this.board = new Board();
    return this;
  }

  public Game build() {
    return new Game(player1, player2, board);
  }
}
