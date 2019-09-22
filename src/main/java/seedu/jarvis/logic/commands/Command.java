package seedu.jarvis.logic.commands;

import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.AddressModel;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Returns whether the command has an inverse execution.
     * If the command has no inverse execution, then calling {@code executeInverse}
     * will be guaranteed to always throw a {@code CommandException}.
     *
     * @return Whether the command has an inverse execution.
     */
    public abstract boolean hasInverseExecution();

    /**
     * Executes the command and returns the result message.
     *
     * @param addressModel {@code AddressModel} which the command should operate on.
     * @return Feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(AddressModel addressModel) throws CommandException;

    /**
     * Executes the inverse of the command and returns the result message.
     *
     * @param addressModel {@code AddressModel} which the command should inversely operate on.
     * @return Feedback message of the inverse operation result for display.
     * @throws CommandException If an error occurs during the command inverse execution.
     */
    public abstract CommandResult executeInverse(AddressModel addressModel) throws CommandException;

}