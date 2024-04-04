package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.student.Lesson.DATE_FORMATTER;
import static seedu.address.model.student.Lesson.TIME_FORMATTER;
import static seedu.address.model.student.Lesson.isValidLesson;
import static seedu.address.storage.JsonAdaptedLesson.isValidJsonLesson;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Subject;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }
    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }
    /**
     * Parses a {@code String subject} into an {@code Subject}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subject} is invalid.
     */
    public static Subject parseSubject(String subject) throws ParseException {
        requireNonNull(subject);
        String trimmedSubject = subject.trim();
        if (!Subject.isValidSubject(trimmedSubject)) {
            throw new ParseException(Subject.MESSAGE_CONSTRAINTS);
        }
        return new Subject(trimmedSubject);
    }

    /**
     * Parses a {@code String lesson} into a {@code Lesson}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code lesson} is invalid.
     */
    public static Lesson parseLesson(String lesson) throws ParseException {
        requireNonNull(lesson);
        if (!isValidLesson(lesson) && !isValidJsonLesson(lesson)) {
            throw new ParseException(Lesson.MESSAGE_CONSTRAINTS_1);
        }
        // split lesson into its attributes based on "|" character
        String[] lessonDetails = lesson.trim().split("\\|");
        String subjectDetail = lessonDetails[0];
        LocalDate dateDetail = LocalDate.parse(lessonDetails[1], DATE_FORMATTER);
        LocalTime timeDetail = LocalTime.parse(lessonDetails[2], TIME_FORMATTER);
        int isCompleted;
        if (lessonDetails.length == 4) {
            isCompleted = Integer.parseInt(lessonDetails[3]);
            return new Lesson(subjectDetail, dateDetail, timeDetail, isCompleted);
        } else {
            return new Lesson(subjectDetail, dateDetail, timeDetail);
        }
    }

    /**
     * Parses a {@code Collection<String> lessonList} into a {@code List<Lesson>}.
     *
     * @throws ParseException if the given {@code lessonSet} is invalid.
     */
    public static List<Lesson> parseLessons(Collection<String> lessons) throws ParseException {
        requireNonNull(lessons);
        List<Lesson> lessonList = new ArrayList<>();
        for (String lesson : lessons) {
            lessonList.add(parseLesson(lesson));
        }
        return lessonList;
    }
}
