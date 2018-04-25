package com.softexploration.binarrior.application;

import com.softexploration.binarrior.command.Command;
import com.softexploration.binarrior.command.CommandDispatcher;
import com.softexploration.binarrior.command.impl.CreateGameCharacterCommand;
import com.softexploration.binarrior.command.impl.ExitCommand;
import com.softexploration.binarrior.command.impl.ExploreCommand;
import com.softexploration.binarrior.command.impl.FightCommand;
import com.softexploration.binarrior.command.impl.ResumeCommand;
import com.softexploration.binarrior.command.impl.SaveCommand;
import com.softexploration.binarrior.validation.ValidationRules;
import com.softexploration.binarrior.validation.impl.DefaultValidationRules;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ApplicationControllerDependencyResolver {

    public void resolveDependencies(final ApplicationController controller) {

        final CreateGameCharacterCommand createGameCharacterCommand = new CreateGameCharacterCommand();
        final ExploreCommand exploreCommand = new ExploreCommand();
        final SaveCommand saveCommand = new SaveCommand();
        final ResumeCommand resumeCommand = new ResumeCommand();
        final FightCommand fightCommand = new FightCommand();
        final ExitCommand exitCommand = new ExitCommand();

        final Map<String, String> commandKeysToCommandNames = new LinkedHashMap<>();
        commandKeysToCommandNames.put(":1", "Create");
        commandKeysToCommandNames.put(":2", "Explore");
        commandKeysToCommandNames.put(":3", "Fight");
        commandKeysToCommandNames.put(":4", "Save");
        commandKeysToCommandNames.put(":5", "Resume");
        commandKeysToCommandNames.put(":6", "Exit");

        final Map<String, Command> commandNamesToCommandDef = new LinkedHashMap<>();
        commandNamesToCommandDef.put("Create", createGameCharacterCommand);
        commandNamesToCommandDef.put("Explore", exploreCommand);
        commandNamesToCommandDef.put("Fight", fightCommand);
        commandNamesToCommandDef.put("Save", saveCommand);
        commandNamesToCommandDef.put("Resume", resumeCommand);
        commandNamesToCommandDef.put("Exit", exitCommand);

        final Map<String, String> commandNamesToLabels = new LinkedHashMap<>();
        commandNamesToLabels.put("Create", "Create character");
        commandNamesToLabels.put("Explore", "Explore");
        commandNamesToLabels.put("Fight", "Fight");
        commandNamesToLabels.put("Save", "Save game");
        commandNamesToLabels.put("Resume", "Resume game");
        commandNamesToLabels.put("Exit", "Exit");

        controller.setCommandKeysToCommandNames(commandKeysToCommandNames);
        controller.setCommandNamesToLabels(commandNamesToLabels);
        controller.setTerminationCommandKeys(":6");

        final CommandDispatcher commandDispatcher = new CommandDispatcher();
        commandDispatcher.setCommandKeysToCommandNames(commandKeysToCommandNames);
        commandDispatcher.setCommandNamesToCommandDef(commandNamesToCommandDef);
        controller.setCommandDispatcher(commandDispatcher);

        final ApplicationState applicationState = new ApplicationState();
        commandDispatcher.setApplicationState(applicationState);
        createGameCharacterCommand.setApplicationState(applicationState);

        final Scanner scanner = new Scanner(System.in);
        controller.setScanner(scanner);
        controller.setPrintStream(System.out);

        exitCommand.setScanner(scanner);
        fightCommand.setPrintStream(System.out);

        createGameCharacterCommand.setApplicationState(applicationState);
        createGameCharacterCommand.setPrintStream(System.out);

        saveCommand.setApplicationState(applicationState);
        saveCommand.setPrintStream(System.out);

        resumeCommand.setApplicationState(applicationState);
        resumeCommand.setPrintStream(System.out);

        final ValidationRules validationRules = new DefaultValidationRules();
        createGameCharacterCommand.setValidationRules(validationRules);
        fightCommand.setValidationRules(validationRules);

        exploreCommand.setPrintStream(System.out);
        exploreCommand.setValidationRules(validationRules);

        exitCommand.setPrintStream(System.out);
    }

}
