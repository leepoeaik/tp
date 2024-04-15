package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
/**
 * Represents a Person's subject in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSubject(String)}
 */
public class Subject {

    public static final String MESSAGE_CONSTRAINTS =
            "Subjects can only take alphabetic values and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "^[A-Za-z][a-zA-Z ]*$";

    public final String value;

    /**
     * Constructs a {@code Subject}.
     *
     * @param subject A valid subject.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        if (subject.trim().isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }


        String formattedSubject = subject.substring(0, 1).toUpperCase() + subject.substring(1).toLowerCase();
        checkArgument(isValidSubject(subject), MESSAGE_CONSTRAINTS);
        this.value = formattedSubject;
    }
    /**
     * Returns true if a given string is a valid subject.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Subject)) {
            return false;
        }

        Subject otherSubject = (Subject) other;
        return value.equals(otherSubject.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
