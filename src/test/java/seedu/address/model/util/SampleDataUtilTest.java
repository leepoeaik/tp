package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Student;

public class SampleDataUtilTest {

    @Test
    void getSampleStudents() {
        Student[] sampleStudents = SampleDataUtil.getSampleStudents();
        assertEquals(8, sampleStudents.length);
    }

    @Test
    void getLessonList() throws ParseException {
        List<Lesson> lessonList = SampleDataUtil.getLessonList("Maths|10-05-2004|12:29", "English|10-05-2004|12:29");
        assertEquals(2, lessonList.size());
        assertTrue(lessonList.contains(ParserUtil.parseLesson("Maths|10-05-2004|12:29")));
        assertTrue(lessonList.contains(ParserUtil.parseLesson("English|10-05-2004|12:29")));
    }
}
