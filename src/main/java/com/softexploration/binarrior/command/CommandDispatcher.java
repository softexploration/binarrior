package com.softexploration.binarrior.command;

import com.softexploration.binarrior.application.ApplicationState;

import java.util.Map;

public class CommandDispatcher {

    private Map<String, String> commandKeysToCommandNames;
    private Map<String, Command> commandNamesToCommandDef;
    private ApplicationState applicationState;

    public String dispatchCommandLineEntry(final String commandLineEntry) {
        final String commandKeys = resolveCommandKeys(commandLineEntry);
        dispatchCommandKeys(commandKeys);
        return commandKeys;
    }

    protected String resolveCommandKeys(final String entry) {
        return entry.trim();
    }

    protected void dispatchCommandKeys(final String commandKeys) {
        if (isMenuCommand(commandKeys)) {
            handleAsMainCommand(commandKeys);
        } else if (isActiveCommandAvailable()) {
            handleActiveCommand(commandKeys);
        }
    }

    protected void handleAsMainCommand(final String commandKeys) {
        if (isActiveCommandAvailable()) {
            applicationState.getActiveCommand().reset();
        }
        final Command command = resolveCommand(commandKeys);
        applicationState.setActiveCommand(command);
        handleActiveCommand(commandKeys);
    }

    protected boolean isActiveCommandAvailable() {
        return applicationState.getActiveCommand() != null;
    }

    protected void handleActiveCommand(final String commandKeys) {
        applicationState.getActiveCommand().execute(new CommandContext(commandKeys, applicationState.getGameCharacter()));
    }

    protected Command resolveCommand(final String commandKeys) {
        return commandNamesToCommandDef.get(commandKeysToCommandNames.get(commandKeys));
    }

    protected boolean isMenuCommand(final String commandKeys) {
        return commandKeysToCommandNames.containsKey(commandKeys);
    }

    public void setCommandNamesToCommandDef(final Map<String, Command> commandNamesToCommandDef) {
        this.commandNamesToCommandDef = commandNamesToCommandDef;
    }

    public void setCommandKeysToCommandNames(final Map<String, String> commandKeysToCommandNames) {
        this.commandKeysToCommandNames = commandKeysToCommandNames;
    }

    public void setApplicationState(final ApplicationState applicationState) {
        this.applicationState = applicationState;
    }
}
