package com.softexploration.binarrior.exploration;

/**
 * Set of behaviours belonging to a strategy.
 *
 * @param <T>
 */
public interface ExplorationStrategy<T> {

    /**
     * Explore
     */
    void explore();

    /**
     * Finish exploration.
     */
    void finishExploration();

    /**
     * Get already explored object.
     *
     * @return
     */
    T getExploredObject();
}

