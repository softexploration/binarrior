package com.softexploration.binarrior.command.impl;

import com.softexploration.binarrior.application.ApplicationState;
import com.softexploration.binarrior.command.Command;
import com.softexploration.binarrior.command.CommandContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;

public class ResumeCommand implements Command {

    private ApplicationState applicationState;
    private PrintStream printStream;

    @Override
    public void execute(final CommandContext context) {
        try (final ObjectInputStream in = new ObjectInputStream(new FileInputStream("app-state.ser"))) {
            final ApplicationState readApplicationState = (ApplicationState) in.readObject();
            applicationState.setGameCharacter(readApplicationState.getGameCharacter());
            printStream.println("The game is resumed. Provide any :X command from Menu.");
        } catch (final IOException | ClassNotFoundException e) {
            printStream.println("Could not resume the game. Provide any :X command from Menu.");
        }
    }

    public void setApplicationState(ApplicationState applicationState) {
        this.applicationState = applicationState;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }
}
