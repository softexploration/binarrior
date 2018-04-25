package com.softexploration.binarrior.command.impl;

import com.softexploration.binarrior.application.ApplicationState;
import com.softexploration.binarrior.character.GameCharacter;
import com.softexploration.binarrior.command.CommandContext;
import com.softexploration.binarrior.validation.ValidationRules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateGameCharacterCommandTest {

    @Mock
    private ApplicationState applicationState;

    @Mock
    private PrintStream printStream;

    @Mock
    private ValidationRules validationRules;

    @Mock
    private CommandContext context;

    @Spy
    @InjectMocks
    final CreateGameCharacterCommand command = new CreateGameCharacterCommand();

    @Test
    public void executeCharacterExists() {
        // given
        doReturn(true).when(validationRules).isCharacterAvailable(context);
        doNothing().when(command).executePromptState();
        doNothing().when(command).executeProvideNameState(context);
        final GameCharacter gameCharacter = mock(GameCharacter.class);
        doReturn(gameCharacter).when(context).getGameCharacter();
        doReturn("Anton").when(gameCharacter).getName();

        // when
        command.execute(context);

        // then
        verify(validationRules).isCharacterAvailable(context);
        verify(command, times(0)).executePromptState();
        verify(command, times(0)).executeProvideNameState(context);
    }

    @Test
    public void executePromptState() {
        // given
        doReturn(false).when(validationRules).isCharacterAvailable(context);
        doNothing().when(command).executePromptState();
        doNothing().when(command).executeProvideNameState(context);

        // when
        command.execute(context);

        // then
        verify(validationRules).isCharacterAvailable(context);
        verify(command, times(1)).executePromptState();
        verify(command, times(0)).executeProvideNameState(context);
    }

    @Test
    public void executeProvideNameState() {
        // given
        doReturn(false).when(validationRules).isCharacterAvailable(context);
        doNothing().when(command).executePromptState();
        doNothing().when(command).executeProvideNameState(context);
        doReturn(CreateGameCharacterCommand.CreateCharacterCommandState.PROVIDE_NAME).when(command).getState();

        // when
        command.execute(context);

        // then
        verify(validationRules, times(1)).isCharacterAvailable(context);
        verify(command, times(0)).executePromptState();
        verify(command, times(1)).executeProvideNameState(context);
    }

    @Test
    public void reset() {
        //given
        //when
        command.reset();

        // then
        assertEquals(CreateGameCharacterCommand.CreateCharacterCommandState.PROMPT, command.getState());
    }
}