package seedu.address.storage;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.student.Lesson.DATE_FORMATTER;
import static seedu.address.model.student.Lesson.TIME_FORMATTER;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Lesson;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
public class JsonAdaptedLesson {

    private static final String MESSAGE_CONSTRAINTS =
            "Jackson-adapted Lessons must be of the form subject|dd-MM-yyyy|hh:mm|0/1, where subject contains only"
                    + " alphabets and spaces, and indicate lesson incomplete/completed with 0 or 1 respectively.";

    private final String lesson;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given {@code lesson}.
     */
    @JsonCreator
    public JsonAdaptedLesson(String lesson) {
        this.lesson = lesson;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        lesson = source.getJsonValue();
    }


    @JsonValue
    public String getLessonName() {
        return lesson;
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType() throws IllegalValueException {
        if (!isValidJsonLesson(getLessonName())) {
            throw new IllegalValueException(MESSAGE_CONSTRAINTS);
        }
        return parseJsonLesson(getLessonName());
    }
    /**
     * Returns true if a given Jackson-friendly adapted lesson object is a valid lesson.
     */
    public static boolean isValidJsonLesson(String jsonLesson) {
        requireNonNull(jsonLesson);

        String[] lessonDetails = jsonLesson.trim().split("\\|");
        if (lessonDetails.length != 4) {
            return false;
        }
        if (!lessonDetails[0].matches(Lesson.VALIDATION_REGEX)) {
            return false;
        }
        if (!lessonDetails[1].matches(Lesson.DATE_REGEX)) {
            return false;
        }
        if (!lessonDetails[2].matches(Lesson.TIME_REGEX)) {
            return false;
        }
        if (!lessonDetails[3].matches("0|1")) {
            return false;
        }
        return true;
    }

    /**
     * Parses a Jackson-friendly adapted lesson into a model {@code Lesson} object.
     */
    public static Lesson parseJsonLesson(String jsonLesson) {
        requireNonNull(jsonLesson);
        assert isValidJsonLesson(jsonLesson);

        String[] lessonDetails = jsonLesson.trim().split("\\|");
        String subjectDetail = lessonDetails[0];
        LocalDate dateDetail = LocalDate.parse(lessonDetails[1], DATE_FORMATTER);
        LocalTime timeDetail = LocalTime.parse(lessonDetails[2], TIME_FORMATTER);
        int isCompleted = Integer.parseInt(lessonDetails[3]);
        Lesson newLesson = new Lesson(subjectDetail, dateDetail, timeDetail);
        if (isCompleted == 1) {
            newLesson.setLessonComplete();
        }
        return newLesson;
    }

}
