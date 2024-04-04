package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
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
 * Adds a lesson to the student identified using it's displayed index from the address book.
 */
public class AddLessonCommand extends Command {
    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a lesson to the student identified "
            + "by the index number used in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + "l/ [LESSON]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "l/ 20-02-2002|10:00";
    public static final String MESSAGE_ADD_LESSON_SUCCESS = "Scheduled lesson to student: %1$s";
    private final Index index;
    private final LocalDate date;
    private final LocalTime time;
    private Integer isCompleted;
    /***
     * Creates a AddLessonCommand to add the lesson to the specified {@code Person},
     * without the isCompleted field.
     */
    public AddLessonCommand(Index index, LocalDate date, LocalTime time) {
        requireAllNonNull(index, date, time);
        this.index = index;
        this.date = date;
        this.time = time;
    }
    /***
     * Creates a AddLessonCommand to add the lesson to the specified {@code Person},
     * with the isCompleted field.
     */
    public AddLessonCommand(Index index, LocalDate date, LocalTime time, int isCompleted) {
        this(index, date, time);
        this.isCompleted = isCompleted;
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
        // Separate lesson constructors if isCompleted parameter is provided from user input.
        Lesson newLesson;
        if (isCompleted != null) {
            newLesson = new Lesson(studentToEditSubject, this.date, this.time, this.isCompleted);
        } else {
            newLesson = new Lesson(studentToEditSubject, this.date, this.time);
        }
        // Check if the lesson already exists in the student's lesson list.
        if (lessonList.contains(newLesson)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_LESSON);
        }
        // Add the lesson to the student's lesson list.
        lessonList.add(newLesson);
        Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getAddress(), studentToEdit.getSubject(),
                studentToEdit.getRemark(), lessonList);

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    private String generateSuccessMessage(Student editedStudent) {
        return String.format(MESSAGE_ADD_LESSON_SUCCESS, Messages.format(editedStudent));
    }
}
