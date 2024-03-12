package com.griddynamics.tictactoe.player;

import com.griddynamics.tictactoe.game.GameConstants;
import com.griddynamics.tictactoe.messages.InputMessages;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerFactoryTest {

    private final PlayerFactory playerFactory = new PlayerFactory();

    @Test
    public void testCreateEasyPlayer() {
        Player player = playerFactory.createPlayer(InputMessages.EASY, GameConstants.X_SIGN);
        assertInstanceOf(ComputerPlayer.class, player);
        assertEquals(Difficulty.EASY, (((ComputerPlayer) player).getDifficulty()));
        assertEquals('X', player.getSign());
    }

    @Test
    public void testCreateMediumPlayer() {
        Player player = playerFactory.createPlayer(InputMessages.MEDIUM, GameConstants.O_SIGN);
        assertInstanceOf(ComputerPlayer.class, player);
        assertEquals(Difficulty.MEDIUM, ((ComputerPlayer) player).getDifficulty());
        assertEquals('O', player.getSign());
    }

    @Test
    public void testCreateHardPlayer() {
        Player player = playerFactory.createPlayer(InputMessages.HARD, GameConstants.X_SIGN);
        assertInstanceOf(ComputerPlayer.class, player);
        assertEquals(Difficulty.HARD, ((ComputerPlayer) player).getDifficulty());
        assertEquals('X', player.getSign());
    }

    @Test
    public void testCreateUserPlayer() {
        Player player = playerFactory.createPlayer(InputMessages.USER, GameConstants.O_SIGN);
        assertInstanceOf(HumanPlayer.class, player);
        assertEquals('O', player.getSign());
    }

    @Test
    public void testCreatePlayerWithInvalidType() {
        Player player = playerFactory.createPlayer("INVALID", GameConstants.X_SIGN);
        assertNull(player);
    }
}
