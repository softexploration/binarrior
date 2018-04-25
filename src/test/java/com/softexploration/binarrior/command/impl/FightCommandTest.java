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
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FightCommandTest {

    @Mock
    private ApplicationState applicationState;

    @Mock
    private PrintStream printStream;

    @Mock
    private ValidationRules validationRules;

    @Mock
    private CommandContext context;

    @Mock
    private GameCharacter character;

    @Spy
    @InjectMocks
    private FightCommand command = new FightCommand();

    @Test
    public void execute() {
        // given
        doReturn(false).when(validationRules).isCharacterAvailable(context);
        doNothing().when(command).printIntroduction(context);
        doNothing().when(command).executeStartState(context);
        doNothing().when(command).executeWaitingState(context);

        // when
        command.execute(context);

        // then
        verify(validationRules).isCharacterAvailable(context);
        verify(command, times(0)).printIntroduction(context);
        verify(command, times(0)).executeStartState(context);
        verify(command, times(0)).executeWaitingState(context);
    }

    @Test
    public void reset() {
        // given
        doReturn(character).when(context).getGameCharacter();
        doReturn(0).when(character).provideNewNumber();
        doNothing().when(command).printIntroduction(context);

        // when
        command.executeStartState(context);
        command.reset();

        // then
        verify(context, times(2)).getGameCharacter();
        verify(character, times(2)).provideNewNumber();
        verify(command).printIntroduction(context);
        assertEquals(FightCommand.FightCommandState.START, command.getState());
    }

    @Test
    public void executeStartState() {
        // given
        doReturn(true).when(validationRules).isCharacterAvailable(context);
        doNothing().when(command).printIntroduction(context);
        doNothing().when(command).executeWaitingState(context);
        doReturn(character).when(context).getGameCharacter();
        doReturn(0).when(character).provideNewNumber();

        // when
        command.execute(context);

        // then
        verify(validationRules).isCharacterAvailable(context);
        verify(command).printIntroduction(context);
        verify(command, times(0)).executeWaitingState(context);
        verify(context, times(2)).getGameCharacter();
        verify(character, times(2)).provideNewNumber();
    }

    @Test
    public void executeWaitingState() {
        // given
        doReturn(true).when(validationRules).isCharacterAvailable(context);
        doNothing().when(command).printIntroduction(context);
        doNothing().when(command).executeStartState(context);
        doReturn(FightCommand.FightCommandState.WAITING_FOR_ANSWER).when(command).getState();

        // when
        command.execute(context);

        // then
        verify(validationRules).isCharacterAvailable(context);
        verify(command).printIntroduction(context);
        verify(command, times(0)).executeStartState(context);
        verify(command).getState();
    }

    @Test
    public void parse() {
        // given
        // when
        // then
        assertEquals(Integer.valueOf(10), command.parse("1010"));
        assertEquals(Integer.valueOf(0), command.parse("0"));
        assertNull(command.parse("2"));
        assertNull(command.parse("-2"));
        assertNull(command.parse("abc"));
    }
}