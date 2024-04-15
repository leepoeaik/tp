package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FeeStatusTest {

    @Test
    public void equals() {
        FeeStatus feeStatus = new FeeStatus("paid");

        // same object -> returns true
        assertTrue(feeStatus.equals(feeStatus));

        // same values -> returns true
        FeeStatus feeStatusCopy = new FeeStatus(feeStatus.status);
        assertTrue(feeStatus.equals(feeStatusCopy));

        // different types -> returns false
        assertFalse(feeStatus.equals(1));

        // null -> returns false
        assertFalse(feeStatus.equals(null));

        // different remark -> returns false
        FeeStatus differentFeeStatus = new FeeStatus("unpaid");
        assertFalse(feeStatus.equals(differentFeeStatus));
    }
}
