package com.softexploration.binarrior.command;

/**
 * Command abstraction coming from UI. Command is considered to be stateful.
 */
public interface Command {

    /**
     * Execute command basing on the context.
     *
     * @param context
     */
    void execute(final CommandContext context);

    /**
     * Reset to the initial state.
     */
    default void reset() {
    }
}
