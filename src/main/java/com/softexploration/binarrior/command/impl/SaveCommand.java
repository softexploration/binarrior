package com.softexploration.binarrior.command.impl;

import com.softexploration.binarrior.application.ApplicationState;
import com.softexploration.binarrior.command.Command;
import com.softexploration.binarrior.command.CommandContext;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;

public class SaveCommand implements Command {

    private ApplicationState applicationState;
    private PrintStream printStream;

    @Override
    public void execute(final CommandContext context) {
        try (final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("app-state.ser"))) {
            final ApplicationState toSave = new ApplicationState();
            toSave.setGameCharacter(applicationState.getGameCharacter());
            oos.writeObject(applicationState);
            printStream.println("The game is saved. Provide any :X command from Menu.");
        } catch (final IOException e) {
            printStream.println("Could not save the game. Provide any :X command from Menu.");
        }
    }

    public void setApplicationState(ApplicationState applicationState) {
        this.applicationState = applicationState;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }
}
