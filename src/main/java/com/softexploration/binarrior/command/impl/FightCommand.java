package com.softexploration.binarrior.command.impl;

import com.softexploration.binarrior.command.Command;
import com.softexploration.binarrior.command.CommandContext;
import com.softexploration.binarrior.validation.ValidationRules;

import java.io.PrintStream;

public class FightCommand implements Command {

    private PrintStream printStream;
    private ValidationRules validationRules;

    private FightCommandState state;
    private int number1;
    private int number2;

    public FightCommand() {
        state = FightCommandState.START;
    }

    @Override
    public void execute(final CommandContext context) {
        if (!validationRules.isCharacterAvailable(context)) {
            printStream.println("Binnarior GameCharacter not created yet. Please create your GameCharacter first.");
            return;
        }

        switch (getState()) {
            case START:
                executeStartState(context);
                break;

            case WAITING_FOR_ANSWER:
                executeWaitingState(context);
                break;
        }
    }

    @Override
    public void reset() {
        state = FightCommandState.START;
    }

    protected void executeStartState(final CommandContext context) {
        number1 = context.getGameCharacter().provideNewNumber();
        number2 = context.getGameCharacter().provideNewNumber();
        state = FightCommandState.WAITING_FOR_ANSWER;
        printIntroduction(context);
    }

    protected void executeWaitingState(final CommandContext context) {
        printIntroduction(context);
        final String commandLine = context.getCommandKeys();
        final Integer actualNumber = parse(commandLine);
        if (actualNumber == null) {
            printStream.println(commandLine + " is invalid binary number.");
        } else {
            int expected = context.getGameCharacter().sum(number1, number2);
            if (expected == actualNumber.intValue()) {
                printStream.println("Congratulations! " + commandLine + " is a correct answer.");
            } else {
                printStream.println("Unfortunately, " + commandLine + " is a wrong answer.");
            }
        }
        printStream.println("Press Enter to continue fighting or provide any :X command from Menu.");
        state = FightCommandState.START;
    }

    protected void printIntroduction(final CommandContext context) {
        printStream.println(context.getGameCharacter().introduceYourself() + " Now it's time to fight by operating on binary numbers.");
        printStream.println("Provide the result and confirm with Enter.");
        printStream.println(Integer.toString(number1, 2) + "+" + Integer.toString(number2, 2) + "=?");
    }

    protected Integer parse(final String line) {
        Integer ret = null;
        try {
            ret = Integer.valueOf(line, 2);
        } catch (final Exception ex) {
            // NOP
        }
        return ret;
    }

    protected enum FightCommandState {
        START, WAITING_FOR_ANSWER;
    }

    protected FightCommandState getState() {
        return state;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void setValidationRules(ValidationRules validationRules) {
        this.validationRules = validationRules;
    }
}
