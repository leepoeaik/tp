package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEESTATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FeeStatusCommand;
import seedu.address.model.student.FeeStatus;

public class FeeStatusCommandParserTest {
    private FeeStatusCommandParser parser = new FeeStatusCommandParser();
    private final String nonEmptyFeeStatus = "Some remark.";

    @Test
    public void parse_indexSpecified_success() {
        // have remark
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_FEESTATUS + nonEmptyFeeStatus;
        FeeStatusCommand expectedCommand = new FeeStatusCommand(INDEX_FIRST_STUDENT, new FeeStatus(nonEmptyFeeStatus));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no remark
        userInput = targetIndex.getOneBased() + " " + PREFIX_FEESTATUS;
        expectedCommand = new FeeStatusCommand(INDEX_FIRST_STUDENT, new FeeStatus(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FeeStatusCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, FeeStatusCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, FeeStatusCommand.COMMAND_WORD + " " + nonEmptyFeeStatus, expectedMessage);
    }
}
