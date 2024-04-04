package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEESTATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEESTATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.FeeStatusCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.FeeStatus;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FeeStatusCommand.
 */

public class FeeStatusCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final FeeStatus feeStatus = new FeeStatus("Some status");

        assertCommandFailure(new FeeStatusCommand(INDEX_FIRST_STUDENT, feeStatus), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_STUDENT.getOneBased(), feeStatus));
    }

    @Test
    public void equals() {
        final FeeStatusCommand standardCommand = new FeeStatusCommand(INDEX_FIRST_STUDENT,
                new FeeStatus(VALID_FEESTATUS_AMY));

        FeeStatusCommand commandWithSameValues = new FeeStatusCommand(INDEX_FIRST_STUDENT,
                new FeeStatus(VALID_FEESTATUS_AMY));

        assertTrue(standardCommand.equals(commandWithSameValues));

        assertTrue(standardCommand.equals(standardCommand));

        assertFalse(standardCommand.equals(null));

        assertFalse(standardCommand.equals(new ClearCommand()));

        assertFalse(standardCommand.equals(new FeeStatusCommand(INDEX_SECOND_STUDENT,
                new FeeStatus(VALID_FEESTATUS_AMY))));

        assertFalse(standardCommand.equals(new FeeStatusCommand(INDEX_FIRST_STUDENT,
                new FeeStatus(VALID_FEESTATUS_BOB))));
    }
}
