package com.softexploration.binarrior.command;

import com.softexploration.binarrior.application.ApplicationState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandDispatcherTest {

    @Mock
    private ApplicationState applicationState;

    @Spy
    @InjectMocks
    private CommandDispatcher dispatcher = new CommandDispatcher();

    @Test
    public void dispatchCommandLineEntry() {
        // given
        final String commandLineEntry = ":1";
        final String commandKeys = ":1";
        doReturn(commandKeys).when(dispatcher).resolveCommandKeys(commandLineEntry);
        doNothing().when(dispatcher).dispatchCommandKeys(commandKeys);

        // when
        final String result = dispatcher.dispatchCommandLineEntry(commandLineEntry);

        // then
        verify(dispatcher).resolveCommandKeys(commandLineEntry);
        verify(dispatcher).dispatchCommandKeys(commandKeys);
        assertEquals(commandKeys, result);
    }

    @Test
    public void resolveCommandKeys() {
        // given
        // when
        final String result = dispatcher.resolveCommandKeys(":1   ");

        // then
        assertEquals(":1", result);
    }

    @Test
    public void dispatchCommandKeys() {
        // given
        final String commandKeys = ":1";
        doReturn(true).when(dispatcher).isMenuCommand(commandKeys);
        doNothing().when(dispatcher).handleAsMainCommand(commandKeys);
        doNothing().when(dispatcher).handleActiveCommand(commandKeys);
        doReturn(false).when(dispatcher).isActiveCommandAvailable();

        // when
        dispatcher.dispatchCommandKeys(commandKeys);

        // then
        verify(dispatcher).isMenuCommand(commandKeys);
        verify(dispatcher).handleAsMainCommand(commandKeys);
        verify(dispatcher, times(0)).handleActiveCommand(commandKeys);
        verify(dispatcher, times(0)).isActiveCommandAvailable();
    }

    @Test
    public void handleAsMainCommand() {
        // given
        final String commandKeys = ":1";
        final Command command = mock(Command.class);
        doReturn(command).when(dispatcher).resolveCommand(commandKeys);
        doNothing().when(applicationState).setActiveCommand(command);
        doNothing().when(dispatcher).handleActiveCommand(commandKeys);
        doReturn(false).when(dispatcher).isActiveCommandAvailable();

        // when
        dispatcher.handleAsMainCommand(commandKeys);

        // then
        verify(dispatcher).resolveCommand(commandKeys);
        verify(applicationState).setActiveCommand(command);
        verify(dispatcher).handleActiveCommand(commandKeys);
        verify(dispatcher).isActiveCommandAvailable();
    }

    @Test
    public void handleActiveCommand() {
        // given
        final String commandKeys = ":1";
        final Command command = mock(Command.class);
        doReturn(command).when(applicationState).getActiveCommand();
        doNothing().when(command).execute(any());

        // when
        dispatcher.handleActiveCommand(commandKeys);

        // then
        verify(applicationState).getActiveCommand();
        verify(command).execute(any());
    }
}