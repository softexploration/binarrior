package com.softexploration.binarrior.application;

import com.softexploration.binarrior.command.CommandDispatcher;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class ApplicationController {

    public static final String BINARRIOR = "\n" +
            "__________.__                           .__              \n" +
            "\\______   \\__| ____ _____ ______________|__| ___________ \n" +
            " |    |  _/  |/    \\\\__  \\\\_  __ \\_  __ \\  |/  _ \\_  __ \\\n" +
            " |    |   \\  |   |  \\/ __ \\|  | \\/|  | \\/  (  <_> )  | \\/\n" +
            " |______  /__|___|  (____  /__|   |__|  |__|\\____/|__|   \n" +
            "        \\/        \\/     \\/                              \n";

    private Map<String, String> commandKeysToCommandNames;
    private Map<String, String> commandNamesToLabels;
    private String terminationCommandKeys;
    private CommandDispatcher commandDispatcher;
    private PrintStream printStream;
    private Scanner scanner;

    public void launchUI() {
        String commandKeys;
        clearScreen();
        printMenu();
        do {
            final String commandLineEntry = readLine();
            clearScreen();
            printMenu();
            commandKeys = commandDispatcher.dispatchCommandLineEntry(commandLineEntry);
        } while (isNotTerminationCommandKeys(commandKeys));
    }

    protected void clearScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else if (os.contains("Mac")) {
                printStream.print("\033[H\033[2J");
            }
        } catch (final Exception e) {
            // NOP
        }
    }

    protected void printMenu() {
        printStream.println(BINARRIOR);
        commandKeysToCommandNames.forEach((keys, command) -> printStream.print(createMenuLabel(command, keys) + " "));
        printStream.println();
    }

    protected String createMenuLabel(final String command, final String commandKeys) {
        return "[" + commandNamesToLabels.get(command) + " " + commandKeys + "]";
    }

    protected boolean isNotTerminationCommandKeys(final String command) {
        return !terminationCommandKeys.equals(command);
    }

    protected String readLine() {
        return scanner.nextLine();
    }

    public void setCommandKeysToCommandNames(final Map<String, String> commandKeysToCommandNames) {
        this.commandKeysToCommandNames = commandKeysToCommandNames;
    }

    public void setCommandNamesToLabels(final Map<String, String> commandNamesToLabels) {
        this.commandNamesToLabels = commandNamesToLabels;
    }

    public void setTerminationCommandKeys(final String terminationCommandKeys) {
        this.terminationCommandKeys = terminationCommandKeys;
    }

    public void setPrintStream(final PrintStream printStream) {
        this.printStream = printStream;
    }

    public void setScanner(final Scanner scanner) {
        this.scanner = scanner;
    }

    public void setCommandDispatcher(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }
}
