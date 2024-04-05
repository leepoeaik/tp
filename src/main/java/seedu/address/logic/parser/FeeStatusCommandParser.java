package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEESTATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.FeeStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.FeeStatus;

/**
 * Parses input arguments and creates a new {@code FeeStatusCommand} object.
 */
public class FeeStatusCommandParser implements Parser<FeeStatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code FeeStatusCommand}
     * returns a {@code FeeStatus} object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FeeStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FEESTATUS);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FeeStatusCommand.MESSAGE_USAGE), ive);
        }

        String feeStatus = argMultimap.getValue(PREFIX_FEESTATUS).orElse("");

        return new FeeStatusCommand(index, new FeeStatus(feeStatus));
    }
}
