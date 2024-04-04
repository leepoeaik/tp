package seedu.address.model;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Student;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    ObservableList<Pair<Student, Lesson>> getScheduleList();
}
