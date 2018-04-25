package com.softexploration.binarrior.command.impl;

import com.softexploration.binarrior.command.Command;
import com.softexploration.binarrior.command.CommandContext;

import java.io.PrintStream;
import java.util.Scanner;

public class ExitCommand implements Command {

    private Scanner scanner;
    private PrintStream printStream;

    @Override
    public void execute(final CommandContext context) {
        printStream.println("Bye!");
        scanner.close();
    }

    public void setScanner(final Scanner scanner) {
        this.scanner = scanner;
    }

    public void setPrintStream(final PrintStream printStream) {
        this.printStream = printStream;
    }
}
