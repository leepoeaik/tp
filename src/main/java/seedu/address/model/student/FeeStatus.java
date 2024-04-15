package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a student's fee status in TutorTrack.
 * Guarantees: immutable; is always valid
 */
public class FeeStatus {
    public final String status;

    /**
     * Constructs an instance of a fee status.
     * @param feeStatus The status of the fees payment of a student.
     */
    public FeeStatus(String feeStatus) {
        requireNonNull(feeStatus);
        status = feeStatus;
    }

    @Override
    public String toString() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FeeStatus // instanceof handles nulls
                && status.equals(((FeeStatus) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }
}
