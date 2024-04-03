package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FEESTATUS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Changes the fee status of an existing student in TutorTrack.
 */

public class FeeStatusCommand extends Command {
    public static final String COMMAND_WORD = "feestatus";

    public static final String Message_Usage = COMMAND_WORD + ": Edits the fee status of the student identified "
            + "by the index number used in the last student listing. "
            + "Existing fee status will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_FEESTATUS + "[FEESTATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_FEESTATUS + "paid";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Fee Status command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }

}
