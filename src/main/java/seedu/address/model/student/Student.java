package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    // Data fields
    private final FeeStatus feeStatus;
    private final Address address;
    private final Remark remark;
    private List<Lesson> lessons = new ArrayList<>();
    private final Subject subject;

    /**
     * Student constructor with all fields.
     */
    public Student(Name name, Phone phone, Email email, Address address, Subject subject,
                  Remark remark, FeeStatus feeStatus, List<Lesson> lessons) {
        requireAllNonNull(name, phone, email, address, subject);

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.subject = subject;
        this.lessons.addAll(lessons);
        this.remark = remark;
        this.feeStatus = feeStatus;
    }
    /**
     * Student constructor with default values for remark, fee status and lessons.
     */
    public Student(Name name, Phone phone, Email email, Address address, Subject subject) {
        requireAllNonNull(name, phone, email, address, subject);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.subject = subject;
        this.lessons = new ArrayList<>();
        this.feeStatus = new FeeStatus("");
        this.remark = new Remark("");
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public FeeStatus getFeeStatus() {
        return feeStatus;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns the ArrayList of lessons for the student, this list is mutable.
     */
    public List<Lesson> getLessons() {
        return lessons;
    }

    public Remark getRemark() {
        return remark;
    }

    public Subject getSubject() {
        return subject;
    }

    /**
     * Returns true if both Students have the same name.
     * This defines a weaker notion of equality between two Students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
    }

    /**
     * Returns true if both Students have the same identity and data fields.
     * This defines a stronger notion of equality between two Students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return name.equals(otherStudent.name)
                && phone.equals(otherStudent.phone)
                && email.equals(otherStudent.email)
                && address.equals(otherStudent.address)
                && lessons.equals(otherStudent.lessons)
                && subject.equals(otherStudent.subject);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, subject, remark, lessons);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("subject", subject)
                .add("remark", remark)
                .add("fee status", feeStatus)
                .add("lessons", lessons)
                .toString();
    }
}
