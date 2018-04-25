package com.softexploration.binarrior.application;

import com.softexploration.binarrior.character.GameCharacter;
import com.softexploration.binarrior.command.Command;

import java.io.Serializable;

public class ApplicationState implements Serializable {

    private GameCharacter gameCharacter;
    private transient Command activeCommand;

    public void setGameCharacter(final GameCharacter gameCharacter) {
        this.gameCharacter = gameCharacter;
    }

    public GameCharacter getGameCharacter() {
        return gameCharacter;
    }

    public void setActiveCommand(final Command activeCommand) {
        this.activeCommand = activeCommand;
    }

    public Command getActiveCommand() {
        return activeCommand;
    }

}
