package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEESTATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.FeeStatus;
import seedu.address.model.student.Student;

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

    public static final String MESSAGE_ADD_FEE_STATUS_SUCCESS = "Added Fee Status to Student: %1$s";
    public static final String MESSAGE_DELETE_FEE_STATUS_SUCCESS = "Removed Fee Status from Student: %1$s";
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
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = new Student(studentToEdit.getName(),
                studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getAddress(), studentToEdit.getSubject(),
                studentToEdit.getRemark(), this.feeStatus,
                studentToEdit.getLessons());

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedStudent));
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

    /**
     * Generates a command execution success message based on whether
     * the fee status is added to or removed from
     * {@code studentToEdit}.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        String message = !feeStatus.status.isEmpty() ? MESSAGE_ADD_FEE_STATUS_SUCCESS
                : MESSAGE_DELETE_FEE_STATUS_SUCCESS;
        return String.format(message, studentToEdit);
    }
}
