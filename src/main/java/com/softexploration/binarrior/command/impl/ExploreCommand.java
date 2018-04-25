package com.softexploration.binarrior.command.impl;

import com.softexploration.binarrior.command.Command;
import com.softexploration.binarrior.command.CommandContext;
import com.softexploration.binarrior.validation.ValidationRules;

import java.io.PrintStream;
import java.util.List;

public class ExploreCommand implements Command {

    private PrintStream printStream;
    private ValidationRules validationRules;

    @Override
    public void execute(final CommandContext context) {
        if (!validationRules.isCharacterAvailable(context)) {
            printStream.println("Binnarior GameCharacter not created yet. Please create your GameCharacter first.");
            return;
        }

        printIntroduction(context);
        switch (context.getCommandKeys().toUpperCase()) {
            case "N":
                executeNewExploration(context);
                break;

            case "C":
                executeContinuationOfExploration(context);
                break;

            default:
                executeDefault(context);
        }
    }

    protected void executeNewExploration(final CommandContext context) {
        context.getGameCharacter().finishExploration();
        context.getGameCharacter().explore();
        final List<Number> numbers = getExploredObject(context);
        printResult(numbers);
        printPrompt();
    }

    protected void executeContinuationOfExploration(final CommandContext context) {
        context.getGameCharacter().explore();
        final List<Number> numbers = getExploredObject(context);
        printResult(numbers);
        printPrompt();
    }

    protected void executeDefault(final CommandContext context) {
        final List<Number> numbers = getExploredObject(context);
        printResult(numbers);
        printPrompt();
    }

    protected void printIntroduction(final CommandContext context) {
        printStream.println(context.getGameCharacter().introduceYourself() + " Now it's time to explore natural numbers.");
    }

    protected void printPrompt() {
        printStream.println("Continue exploration/New exploration/Menu command [C/N/:X]?");
    }

    protected List<Number> getExploredObject(final CommandContext context) {
        return (List<Number>) context.getGameCharacter().getExploredObject();
    }

    protected void printResult(final List<Number> numbers) {
        printStream.println("Explored  numbers:");
        if (numbers.isEmpty()) {
            printStream.println("No number has been explored during this exploration.");
        } else {
            numbers.stream().map(number -> Integer.toString(number.intValue(), 2) + " ").forEach(printStream::print);
            printStream.println();
        }
    }

    public void setValidationRules(ValidationRules validationRules) {
        this.validationRules = validationRules;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }
}
