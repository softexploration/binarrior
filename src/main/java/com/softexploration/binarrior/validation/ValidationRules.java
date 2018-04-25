package com.softexploration.binarrior.validation;

import com.softexploration.binarrior.command.CommandContext;

public interface ValidationRules {

    boolean isCharacterAvailable(final CommandContext context);

    boolean isCorrectNameForCharacter(final String name);

    int getCharacterNameMinLength();

    int getCharacterNameMaxLength();
}
