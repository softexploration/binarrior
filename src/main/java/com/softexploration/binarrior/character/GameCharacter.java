package com.softexploration.binarrior.character;

import com.softexploration.binarrior.exploration.ExplorationStrategy;

import java.io.Serializable;


/**
 * Game character behaviours.
 */
public interface GameCharacter extends ExplorationStrategy, Serializable {

    /**
     * Please, introduce yourself.
     *
     * @return
     */
    String introduceYourself();

    /**
     * Get character's name.
     *
     * @return
     */
    String getName();

    /**
     * Compute the sum of two integral numbers.
     *
     * @param a
     * @param b
     * @return
     */
    default int sum(final int a, final int b) {
        return a + b;
    }

    /**
     * Provide new number.
     *
     * @return
     */
    int provideNewNumber();
}
