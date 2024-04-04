package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.model.student.Lesson.DATE_FORMATTER;
import static seedu.address.model.student.Lesson.TIME_FORMATTER;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddLessonCommand object
 */
public class AddLessonCommandParser implements Parser<AddLessonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddLessonCommand
     * and returns an AddLessonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LESSON);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddLessonCommand.MESSAGE_USAGE), ive);
        }

        String lesson = argMultimap.getValue(PREFIX_LESSON).orElse("");

        if (!isValidLesson(lesson)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddLessonCommand.MESSAGE_USAGE));
        }
        // split lesson into its attributes based on "|" character
        String[] lessonDetails = lesson.trim().split("\\|");
        LocalDate dateDetail = LocalDate.parse(lessonDetails[0], DATE_FORMATTER);
        LocalTime timeDetail = LocalTime.parse(lessonDetails[1], TIME_FORMATTER);
        int isCompleted;
        if (lessonDetails.length == 3) {
            isCompleted = Integer.parseInt(lessonDetails[2]);
            return new AddLessonCommand(index, dateDetail, timeDetail, isCompleted);
        } else {
            return new AddLessonCommand(index, dateDetail, timeDetail);
        }
    }

    /**
     * Checks if the lesson is in the correct format.
     * @param lesson the lesson to be checked
     * @return true if the lesson is in the correct format, false otherwise
     */
    public static boolean isValidLesson(String lesson) {
        String[] lessonDetails = lesson.trim().split("\\|");
        if (lessonDetails.length != 2 && lessonDetails.length != 3) {
            return false;
        }
        try {
            LocalDate.parse(lessonDetails[0], DATE_FORMATTER);
            LocalTime.parse(lessonDetails[1], TIME_FORMATTER);
            if (lessonDetails.length == 3) {
                int isCompleted = Integer.parseInt(lessonDetails[2]);
                if (isCompleted != 0 && isCompleted != 1) {
                    return false;
                }
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            return false;
        }
        return true;
    }
}
