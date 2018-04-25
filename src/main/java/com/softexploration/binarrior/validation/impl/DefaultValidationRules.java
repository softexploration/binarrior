package com.softexploration.binarrior.validation.impl;

import com.softexploration.binarrior.command.CommandContext;
import com.softexploration.binarrior.validation.ValidationRules;

public class DefaultValidationRules implements ValidationRules {

    @Override
    public boolean isCharacterAvailable(final CommandContext context) {
        return context.getGameCharacter() != null;
    }

    @Override
    public boolean isCorrectNameForCharacter(final String name) {
        return name != null && !name.isEmpty() && name.length() >= getCharacterNameMinLength() && name.length() <= getCharacterNameMaxLength();
    }

    @Override
    public int getCharacterNameMinLength() {
        return 3;
    }

    @Override
    public int getCharacterNameMaxLength() {
        return 20;
    }
}
