package com.griddynamics.tictactoe.player;

import com.griddynamics.tictactoe.exceptions.WrongPlayerTypeException;
import com.griddynamics.tictactoe.game.GameConstants;
import com.griddynamics.tictactoe.messages.InputMessages;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class PlayerFactoryTest {

    private final PlayerFactory playerFactory = new PlayerFactory();

    @Test
    public void testCreateEasyPlayer() {
        //given
        Player player = playerFactory.createPlayer(InputMessages.EASY, GameConstants.X_SIGN);
        //then
        assertInstanceOf(ComputerPlayer.class, player);
        assertEquals(Difficulty.EASY, (((ComputerPlayer) player).getDifficulty()));
        assertEquals('X', player.getSign());
    }

    @Test
    public void testCreateMediumPlayer() {
        //given
        Player player = playerFactory.createPlayer(InputMessages.MEDIUM, GameConstants.O_SIGN);
        //then
        assertInstanceOf(ComputerPlayer.class, player);
        assertEquals(Difficulty.MEDIUM, ((ComputerPlayer) player).getDifficulty());
        assertEquals('O', player.getSign());
    }

    @Test
    public void testCreateHardPlayer() {
        //given
        Player player = playerFactory.createPlayer(InputMessages.HARD, GameConstants.X_SIGN);
        //then
        assertInstanceOf(ComputerPlayer.class, player);
        assertEquals(Difficulty.HARD, ((ComputerPlayer) player).getDifficulty());
        assertEquals('X', player.getSign());
    }

    @Test
    public void testCreateUserPlayer() {
        //given
        Player player = playerFactory.createPlayer(InputMessages.USER, GameConstants.O_SIGN);
        //then
        assertInstanceOf(HumanPlayer.class, player);
        assertEquals('O', player.getSign());
    }

    @Test
    public void testCreatePlayerWithInvalidType() {
        assertThrows(WrongPlayerTypeException.class,
                () -> playerFactory.createPlayer("I", GameConstants.X_SIGN));
    }

    @Test
    public void testCreateEasyPlayerArgumentCapture() {
        //given
        PlayerFactory playerFactoryMock = Mockito.mock(PlayerFactory.class);
        ArgumentCaptor<String> capturedPlayerType = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Character> capturedPlayerSign = ArgumentCaptor.forClass(Character.class);
        //when
        playerFactoryMock.createPlayer(InputMessages.EASY, GameConstants.X_SIGN);
        //then
        verify(playerFactoryMock).createPlayer(capturedPlayerType.capture(), capturedPlayerSign.capture());
        String capturedPlayerTypeValue = capturedPlayerType.getValue();
        Character capturedPlayerSignValue = capturedPlayerSign.getValue();
        assertEquals(InputMessages.EASY, capturedPlayerTypeValue);
        assertEquals(GameConstants.X_SIGN, capturedPlayerSignValue);
    }
}
