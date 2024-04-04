package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEESTATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.FeeStatus;

/**
 * Changes the fee status of an existing student in TutorTrack.
 */

public class FeeStatusCommand extends Command {
    public static final String COMMAND_WORD = "feestatus";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the fee status of the student identified "
            + "by the index number used in the last student listing. "
            + "Existing fee status will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_FEESTATUS + "[FEESTATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_FEESTATUS + "Paid.";

    public static final String MESSAGE_ARGUMENTS = "Index: $1$d, Fee Status: $2$s";

    private final Index index;
    private final FeeStatus feeStatus;

    /**
     * Constructs an instance of a FeeStatus Command.
     * @param index The index of the student to whom the fee status is added to.
     * @param feeStatus The fee status of the student.
     */
    public FeeStatusCommand(Index index, FeeStatus feeStatus) {
        requireAllNonNull(index, feeStatus);

        this.index = index;
        this.feeStatus = feeStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, index.getOneBased(), feeStatus));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FeeStatusCommand)) {
            return false;
        }

        // state check
        FeeStatusCommand e = (FeeStatusCommand) other;
        return index.equals(e.index) && feeStatus.equals(e.feeStatus);
    }

}
