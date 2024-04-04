package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.ui.ScheduleListPanel;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueStudentList students;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Students in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
    }

    /**
     * Extracts lessons from studentList
     * Adds the lessons together with student as a pair and sorts it
     * @param studentList observable list of students
     * @return an observable list of a pair of students and lessons
     */
    public ObservableList<Pair<Student, Lesson>> transformList(ObservableList<Student> studentList) {
        List<Pair<Student, Lesson>> scheduleList = new ArrayList<>();
        for (Student student : studentList) {
            List<Lesson> studentLesson = student.getLessons();
            for (Lesson l : studentLesson) {
                scheduleList.add(new Pair(student, l));
            }
        }

        Collections.sort(scheduleList, new SortDate());

        ObservableList<Pair<Student, Lesson>> observableList = FXCollections.observableList(scheduleList);
        return observableList;
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the address
     * book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("students", students)
                .toString();
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Pair<Student, Lesson>> getScheduleList() {
        return transformList(students.asUnmodifiableObservableList());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return students.equals(otherAddressBook.students);
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }

    static class SortDate implements Comparator<Pair<Student, Lesson>> {

        @Override
        public int compare(Pair<Student, Lesson> o1, Pair<Student, Lesson> o2) {
            // TODO: implement your logic here
            return o1.getValue().getDate().compareTo(o2.getValue().getDate());
        }
    }
}
