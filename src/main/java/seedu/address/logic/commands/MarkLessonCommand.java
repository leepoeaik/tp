package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Student;

/**
 * Marks a lesson as done for a student.
 */
public class MarkLessonCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a lesson as completed to the student identified "
            + "by the index number used in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + "l/ [LESSON]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "l/ 20-02-2002|10:00";

    public static final String MESSAGE_MARK_LESSON_SUCCESS = "Mark lesson %1$s to student: %1$s";
    private final Index index;
    private final LocalDate dateDetail;
    private final LocalTime timeDetail;
    /**
     * Creates a MarkLessonDoneCommand to mark the specified {@code Lesson} as done.
     */
    public MarkLessonCommand(Index index, LocalDate dateDetail, LocalTime timeDetail) {
        this.index = index;
        this.dateDetail = dateDetail;
        this.timeDetail = timeDetail;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        List<Lesson> lessonList = new ArrayList<>(studentToEdit.getLessons());
        String studentToEditSubject = studentToEdit.getSubject().value;

        Lesson lessonToMarkDone = new Lesson(studentToEditSubject, dateDetail, timeDetail);
        // Iterate through lessonList and check if lessonToMark is found in list.
        Boolean lessonFound = false;
        for (Lesson l : lessonList) {
            if (l.equals(lessonToMarkDone)) {
                l.setLessonComplete();
                lessonFound = true;
                break;
            }
        }
        // throw exception if lesson is not found in list.
        if (!lessonFound) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON);
        }
        Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getAddress(), studentToEdit.getSubject(),
                studentToEdit.getRemark(), lessonList);

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(String.format(MESSAGE_MARK_LESSON_SUCCESS,
                lessonToMarkDone.getLessonValue(), Messages.format(editedStudent)));
    }
}
