package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.AddLessonCommandParser.isValidLesson;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.model.student.Lesson.DATE_FORMATTER;
import static seedu.address.model.student.Lesson.TIME_FORMATTER;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.MarkLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkLessonCommand object
 */
public class MarkLessonCommandParser implements Parser<MarkLessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkLessonCommand
     * and returns an MarkLessonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public MarkLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LESSON);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkLessonCommand.MESSAGE_USAGE), ive);
        }

        String lesson = argMultimap.getValue(PREFIX_LESSON).orElse("");

        if (!isValidLesson(lesson)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkLessonCommand.MESSAGE_USAGE));
        }
        // split lesson into its attributes based on "|" character
        String[] lessonDetails = lesson.trim().split("\\|");
        LocalDate dateDetail = LocalDate.parse(lessonDetails[0], DATE_FORMATTER);
        LocalTime timeDetail = LocalTime.parse(lessonDetails[1], TIME_FORMATTER);

        return new MarkLessonCommand(index, dateDetail, timeDetail);
    }
}
