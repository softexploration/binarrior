package com.softexploration.binarrior.command.impl;

import com.softexploration.binarrior.application.ApplicationState;
import com.softexploration.binarrior.character.BinarriorGameCharacter;
import com.softexploration.binarrior.command.Command;
import com.softexploration.binarrior.command.CommandContext;
import com.softexploration.binarrior.validation.ValidationRules;

import java.io.PrintStream;


public class CreateGameCharacterCommand implements Command {

    private ApplicationState applicationState;
    private PrintStream printStream;
    private ValidationRules validationRules;

    private CreateCharacterCommandState state;
    private BinarriorGameCharacter.Builder characterBuilder;

    public CreateGameCharacterCommand() {
        state = CreateCharacterCommandState.PROMPT;
    }

    @Override
    public void execute(final CommandContext context) {
        if (validationRules.isCharacterAvailable(context)) {
            printStream.println("Dear " + context.getGameCharacter().getName() + ", you are already here. Now it's time to enjoy other activities :)");
            return;
        }

        switch (getState()) {
            case PROMPT:
                executePromptState();
                break;

            case PROVIDE_NAME:
                executeProvideNameState(context);
                break;
        }
    }

    protected void executePromptState() {
        characterBuilder = new BinarriorGameCharacter.Builder();
        printStream.println("Hello new Binarrior, please provide your name:");
        state = CreateCharacterCommandState.PROVIDE_NAME;
        return;
    }

    protected void executeProvideNameState(final CommandContext context) {
        final String commandLine = context.getCommandKeys();
        if (validationRules.isCorrectNameForCharacter(commandLine)) {
            final BinarriorGameCharacter newCharacter = characterBuilder.name(commandLine).build();
            applicationState.setGameCharacter(newCharacter);
            printStream.println("Dear " + newCharacter.getName() + ", welcome to the binary challenge!");
            reset();
        } else {
            printStream.println("Invalid name, please use length of " + validationRules.getCharacterNameMinLength() + " to " + validationRules.getCharacterNameMaxLength() + " characters:");
        }
    }

    @Override
    public void reset() {
        characterBuilder = null;
        state = CreateCharacterCommandState.PROMPT;
    }

    protected enum CreateCharacterCommandState {
        PROMPT, PROVIDE_NAME;
    }

    protected CreateCharacterCommandState getState() {
        return state;
    }

    public void setApplicationState(ApplicationState applicationState) {
        this.applicationState = applicationState;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void setValidationRules(ValidationRules validationRules) {
        this.validationRules = validationRules;
    }
}
