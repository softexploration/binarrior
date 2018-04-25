package com.softexploration.binarrior.application;

import com.softexploration.binarrior.command.CommandDispatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationControllerTest {

    @Mock
    private CommandDispatcher commandDispatcher;

    @Spy
    @InjectMocks
    private ApplicationController controller = new ApplicationController();

    @Test
    public void launchUI() {
        // given
        final String commandKeys = ":1";
        final String commandLineEntry = ":1";
        doNothing().when(controller).clearScreen();
        doNothing().when(controller).printMenu();
        doReturn(commandLineEntry).when(controller).readLine();
        doReturn(commandKeys).when(commandDispatcher).dispatchCommandLineEntry(":1");
        doReturn(false).when(controller).isNotTerminationCommandKeys(commandKeys);

        // when
        controller.launchUI();

        // then
        verify(controller, times(2)).clearScreen();
        verify(controller, times(2)).printMenu();
        verify(controller).readLine();
        verify(commandDispatcher).dispatchCommandLineEntry(":1");
        verify(controller).isNotTerminationCommandKeys(commandKeys);
    }

    @Test(expected = NullPointerException.class)
    public void launchUIWithNPE() {
        // given
        final String commandKeys = ":1";
        final String commandLineEntry = ":1";
        doNothing().when(controller).clearScreen();
        doNothing().when(controller).printMenu();
        doReturn(null).when(controller).readLine();
        doReturn(commandKeys).when(commandDispatcher).dispatchCommandLineEntry(":1");
        doReturn(false).when(controller).isNotTerminationCommandKeys(commandKeys);

        // when
        controller.launchUI();

        fail("NullPointerException was expected");
    }
}