package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {
    public static final String MESSAGE_CONSTRAINTS_1 =
            "Lessons must be of the form subject|dd-MM-yyyy|hh:mm, where subject contains only alphabets"
                    + " and spaces.";
    public static final String VALIDATION_REGEX = "^[a-zA-Z][a-zA-Z ]*$";
    public static final String DATE_REGEX = "\\d{2}-\\d{2}-\\d{4}";
    public static final String TIME_REGEX = "\\d{2}:\\d{2}";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private String value;
    private String jsonValue;
    private final Subject subject;
    private final LocalDate date;
    private final LocalTime time;
    private int isCompleted;

    /**
     * Constructs a {@code Lesson} that is already parsed.
     *
     * @param subject A valid subject.
     * @param date A valid date.
     * @param time A valid time.
     */
    public Lesson(String subject, LocalDate date, LocalTime time) {
        requireNonNull(subject);
        requireNonNull(date);
        requireNonNull(time);
        checkArgument(subject.matches(VALIDATION_REGEX), MESSAGE_CONSTRAINTS_1);
        // assign the attributes to the lesson
        this.subject = new Subject(subject);
        this.date = LocalDate.parse(date.format(DATE_FORMATTER), DATE_FORMATTER);
        this.time = time;
        this.isCompleted = 0;
        // jSON readable form of lesson
        this.jsonValue = this.subject.value + "|" + this.date.format(DATE_FORMATTER) + "|"
                + this.time.format(TIME_FORMATTER) + "|" + this.isCompleted;
        // UI displayed form of lesson
        this.value = this.subject + " " + this.date + " " + this.time;
    }

    /**
     * Constructs a {@code Lesson} that is already parsed, with the optional isCompleted field.
     *
     * @param subject A valid subject.
     * @param date A valid date.
     * @param time A valid time.
     * @param isCompleted A valid isCompleted int field.
     */
    public Lesson(String subject, LocalDate date, LocalTime time, int isCompleted) {
        this(subject, date, time);
        if (isCompleted == 1) {
            this.setLessonComplete();
        } else {
            this.setLessonIncomplete();
        }
    }

    /**
     * Returns true if a given string is a valid lesson.
     */
    public static boolean isValidLesson(String lessonValue) {
        String[] lessonDetails = lessonValue.split("\\|");
        if (lessonDetails.length != 3) {
            return false;
        }
        try {
            LocalDate.parse(lessonDetails[1], DATE_FORMATTER);
            LocalTime.parse(lessonDetails[2], TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return lessonDetails[0].matches(VALIDATION_REGEX)
                && lessonDetails[1].matches(DATE_REGEX)
                && lessonDetails[2].matches(TIME_REGEX);
    }

    /**
     * Gets the subject of the lesson.
     *
     * @return The subject.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Gets the date of the lesson.
     *
     * @return The date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the time of the lesson.
     *
     * @return The time.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Gets the status of the lesson.
     *
     * @return The status.
     */
    public int getLessonStatus() {
        return isCompleted;
    }

    public String getLessonValue() {
        return value;
    }
    public String getJsonValue() {
        return jsonValue;
    }

    public void setLessonComplete() {
        this.isCompleted = 1;
    }

    public void setLessonIncomplete() {
        this.isCompleted = 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return value.equals(otherLesson.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return this.subject + "  " + this.date.toString() + "  " + this.time + " ";
    }
}
