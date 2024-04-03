package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class LessonTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lesson(null, null, null));
    }

    @Test
    public void constructor_invalidLesson_throwsIllegalArgumentException() {
        String invalidSubject = "";
        LocalDate validDate = LocalDate.parse("10-01-2023", Lesson.DATE_FORMATTER);
        LocalTime validTime = LocalTime.parse("23:00", Lesson.TIME_FORMATTER);
        assertThrows(IllegalArgumentException.class, () -> new Lesson(invalidSubject, validDate, validTime));
    }

    @Test
    public void isValidLesson() {
        // null lesson
        assertThrows(NullPointerException.class, () -> Lesson.isValidLesson(null));

        // invalid lessons
        assertFalse(Lesson.isValidLesson("")); // empty string
        assertFalse(Lesson.isValidLesson(" ")); // spaces only
        assertFalse(Lesson.isValidLesson("Math|invalidDate|09:00")); // invalid date format
        assertFalse(Lesson.isValidLesson("Math|01-01-2023|invalidTime")); // invalid time format

        // valid lessons
        assertTrue(Lesson.isValidLesson("Math|01-01-2023|09:00"));
        assertTrue(Lesson.isValidLesson("Science|01-01-2023|10:00"));
    }

    @Test
    public void equals() {
        Lesson lesson = new Lesson("Math", LocalDate.parse("01-01-2023", Lesson.DATE_FORMATTER),
                LocalTime.parse("09:00", Lesson.TIME_FORMATTER));

        // same values -> returns true
        String validLesson = "Math";
        LocalDate validDate = LocalDate.parse("01-01-2023", Lesson.DATE_FORMATTER);
        LocalTime validTime = LocalTime.parse("09:00", Lesson.TIME_FORMATTER);
        assertTrue(lesson.equals(new Lesson(validLesson, validDate, validTime)));

        // same object -> returns true
        assertEquals(lesson, lesson);

        // null -> returns false
        assertFalse(lesson.equals(null));

        // different types -> returns false
        assertFalse(lesson.equals(5.0f));

        // different values -> returns false
        String differentLesson = "Science";
        assertFalse(lesson.equals(new Lesson(differentLesson, validDate, validTime)));
    }
}
