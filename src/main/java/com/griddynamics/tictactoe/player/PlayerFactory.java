package com.griddynamics.tictactoe.player;

import com.griddynamics.tictactoe.messages.OutputMessages;

import static com.griddynamics.tictactoe.messages.InputMessages.*;

public class PlayerFactory {

  public Player createPlayer(String type, char sign) {
    return switch (type) {
      case EASY -> new ComputerPlayer(Difficulty.EASY, sign);
      case MEDIUM -> new ComputerPlayer(Difficulty.MEDIUM, sign);
      case HARD -> new ComputerPlayer(Difficulty.HARD, sign);
      case USER -> new HumanPlayer(sign);
      default -> {
        System.out.println(OutputMessages.BAD_PARAMETERS);
        yield null;
      }
    };
  }
}
