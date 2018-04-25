package com.softexploration.binarrior.command.impl;

import com.softexploration.binarrior.application.ApplicationState;
import com.softexploration.binarrior.command.CommandContext;
import com.softexploration.binarrior.validation.ValidationRules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ExploreCommandTest {

    @Mock
    private ApplicationState applicationState;

    @Mock
    private PrintStream printStream;

    @Mock
    private ValidationRules validationRules;

    @Mock
    private CommandContext context;

    @Mock
    private List exploredObject;

    @Spy
    @InjectMocks
    private ExploreCommand command = new ExploreCommand();

    @Test
    public void execute() {
        // given
        doReturn(false).when(validationRules).isCharacterAvailable(context);
        doNothing().when(command).printIntroduction(context);
        doNothing().when(command).executeNewExploration(context);
        doNothing().when(command).executeContinuationOfExploration(context);
        doNothing().when(command).executeDefault(context);

        // when
        command.execute(context);

        // then
        verify(validationRules).isCharacterAvailable(context);
        verify(command, times(0)).printIntroduction(context);
        verify(command, times(0)).executeNewExploration(context);
        verify(command, times(0)).executeContinuationOfExploration(context);
        verify(command, times(0)).executeDefault(context);
    }

    @Test
    public void executeNewExploration() {
        // given
        doReturn(true).when(validationRules).isCharacterAvailable(context);
        doNothing().when(command).printIntroduction(context);
        doNothing().when(command).executeNewExploration(context);
        doNothing().when(command).executeContinuationOfExploration(context);
        doNothing().when(command).executeDefault(context);
        doReturn("N").when(context).getCommandKeys();

        // when
        command.execute(context);

        // then
        verify(validationRules).isCharacterAvailable(context);
        verify(command).printIntroduction(context);
        verify(command).executeNewExploration(context);
        verify(command, times(0)).executeContinuationOfExploration(context);
        verify(command, times(0)).executeDefault(context);
        verify(context).getCommandKeys();
    }

    @Test
    public void executeContinuationOfExploration() {
        // given
        doReturn(true).when(validationRules).isCharacterAvailable(context);
        doNothing().when(command).printIntroduction(context);
        doNothing().when(command).executeNewExploration(context);
        doNothing().when(command).executeContinuationOfExploration(context);
        doNothing().when(command).executeDefault(context);
        doReturn("C").when(context).getCommandKeys();

        // when
        command.execute(context);

        // then
        verify(validationRules).isCharacterAvailable(context);
        verify(command).printIntroduction(context);
        verify(command, times(0)).executeNewExploration(context);
        verify(command).executeContinuationOfExploration(context);
        verify(command, times(0)).executeDefault(context);
        verify(context).getCommandKeys();
    }

    @Test
    public void executeDefault() {
        // given
        doReturn(true).when(validationRules).isCharacterAvailable(context);
        doNothing().when(command).printIntroduction(context);
        doNothing().when(command).executeNewExploration(context);
        doNothing().when(command).executeContinuationOfExploration(context);
        doNothing().when(command).executeDefault(context);
        doReturn("").when(context).getCommandKeys();

        // when
        command.execute(context);

        // then
        verify(validationRules).isCharacterAvailable(context);
        verify(command).printIntroduction(context);
        verify(command, times(0)).executeNewExploration(context);
        verify(command, times(0)).executeContinuationOfExploration(context);
        verify(command).executeDefault(context);
        verify(context).getCommandKeys();
    }

    @Test
    public void printPromptAndgetExploredObjectAndPrintResult() {
        // given
        doReturn(true).when(validationRules).isCharacterAvailable(context);
        doNothing().when(command).printIntroduction(context);
        doReturn("").when(context).getCommandKeys();
        doReturn(exploredObject).when(command).getExploredObject(context);
        doNothing().when(command).printResult(exploredObject);
        doNothing().when(command).printPrompt();

        // when
        command.execute(context);

        // then
        verify(validationRules).isCharacterAvailable(context);
        verify(command).printIntroduction(context);
        verify(context).getCommandKeys();
        verify(command).getExploredObject(context);
        verify(command).printResult(exploredObject);
        verify(command).printPrompt();
    }
}