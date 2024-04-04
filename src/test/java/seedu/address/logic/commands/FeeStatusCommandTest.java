package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEESTATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEESTATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.FeeStatus;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FeeStatusCommand.
 */

public class FeeStatusCommandTest {
    private static final String FEE_STATUS_STUB = "Paid.";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    public void execute_addFeeStatusUnfilteredList_success() {
        Student firstPerson = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstPerson).withRemark(FEE_STATUS_STUB).build();

        FeeStatusCommand feeStatusCommand = new FeeStatusCommand(INDEX_FIRST_STUDENT,
                new FeeStatus(editedStudent.getFeeStatus().status));

        String expectedMessage = String.format(FeeStatusCommand.MESSAGE_ADD_FEE_STATUS_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstPerson, editedStudent);

        assertCommandSuccess(feeStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(model.getFilteredStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased()))
                .withFeeStatus(FEE_STATUS_STUB).build();

        FeeStatusCommand feeStatusCommand = new FeeStatusCommand(INDEX_FIRST_STUDENT,
                new FeeStatus(editedStudent.getFeeStatus().status));

        String expectedMessage = String.format(FeeStatusCommand.MESSAGE_ADD_FEE_STATUS_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(feeStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        FeeStatusCommand feeStatusCommand = new FeeStatusCommand(outOfBoundIndex, new FeeStatus(VALID_FEESTATUS_BOB));

        assertCommandFailure(feeStatusCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        FeeStatusCommand feeStatusCommand = new FeeStatusCommand(outOfBoundIndex, new FeeStatus(VALID_FEESTATUS_BOB));

        assertCommandFailure(feeStatusCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final FeeStatusCommand standardCommand = new FeeStatusCommand(INDEX_FIRST_STUDENT,
                new FeeStatus(VALID_FEESTATUS_AMY));
        // same values -> returns true
        FeeStatusCommand commandWithSameValues = new FeeStatusCommand(INDEX_FIRST_STUDENT,
                new FeeStatus(VALID_FEESTATUS_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new FeeStatusCommand(INDEX_SECOND_STUDENT,
                new FeeStatus(VALID_FEESTATUS_AMY))));
        // different remark -> returns false
        assertFalse(standardCommand.equals(new FeeStatusCommand(INDEX_FIRST_STUDENT,
                new FeeStatus(VALID_FEESTATUS_BOB))));
    }
}
