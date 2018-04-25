package com.softexploration.binarrior.command;

import com.softexploration.binarrior.character.GameCharacter;

public class CommandContext {

    private final String commandKeys;
    private final GameCharacter gameCharacter;

    public CommandContext(final String commandKeys, final GameCharacter gameCharacter) {
        this.commandKeys = commandKeys;
        this.gameCharacter = gameCharacter;
    }

    public String getCommandKeys() {
        return commandKeys;
    }

    public GameCharacter getGameCharacter() {
        return gameCharacter;
    }
}
