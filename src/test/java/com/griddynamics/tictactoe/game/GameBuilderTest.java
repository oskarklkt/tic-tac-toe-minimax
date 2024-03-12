package com.griddynamics.tictactoe.game;

import static org.junit.jupiter.api.Assertions.*;

import com.griddynamics.tictactoe.messages.InputMessages;
import com.griddynamics.tictactoe.player.ComputerPlayer;
import com.griddynamics.tictactoe.player.HumanPlayer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.griddynamics.tictactoe.board.Board;

public class GameBuilderTest {

    @Test
    public void testSetPlayer1() {
        GameBuilder builder = new GameBuilder();
        builder.setPlayer1(InputMessages.EASY, GameConstants.X_SIGN);
        Game game = builder.build();

        assertNotNull(game.getPlayer1());
        assertInstanceOf(ComputerPlayer.class, game.getPlayer1());
        assertEquals(GameConstants.X_SIGN, game.getPlayer1().getSign());
    }

    @Test
    public void testSetPlayer2() {
        GameBuilder builder = new GameBuilder();
        builder.setPlayer2(InputMessages.HARD, GameConstants.O_SIGN);
        Game game = builder.build();

        assertNotNull(game.getPlayer2());
        assertInstanceOf(ComputerPlayer.class, game.getPlayer2());
        assertEquals(GameConstants.O_SIGN, game.getPlayer2().getSign());
    }

    @Test
    public void testSetSize() {
        GameBuilder builder = new GameBuilder();
        builder.setSize(5);
        Game game = builder.build();

        assertNotNull(game.getBoard());
        assertEquals(5, Board.SIZE);
    }

    @Test
    public void testBuild() {
        GameBuilder builder = new GameBuilder();
        builder.setPlayer1(InputMessages.USER, GameConstants.X_SIGN).setPlayer2(InputMessages.MEDIUM, GameConstants.O_SIGN).setSize(3);
        Game game = builder.build();

        assertNotNull(game.getPlayer1());
        assertNotNull(game.getPlayer2());
        assertNotNull(game.getBoard());

        assertTrue(game.getPlayer1() instanceof HumanPlayer);
        assertTrue(game.getPlayer2() instanceof ComputerPlayer);
        assertEquals(GameConstants.X_SIGN, game.getPlayer1().getSign());
        assertEquals(GameConstants.O_SIGN, game.getPlayer2().getSign());
        assertEquals(3, Board.SIZE);
    }
}

