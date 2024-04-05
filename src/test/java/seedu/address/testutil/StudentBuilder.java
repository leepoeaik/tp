package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.FeeStatus;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Remark;
import seedu.address.model.student.Student;
import seedu.address.model.student.Subject;
import seedu.address.storage.JsonAdaptedLesson;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    private static final String DEFAULT_REMARK = "";
    private static final String DEFAULT_SUBJECT = "Math";
    private static final String DEFAULT_FEE_STATUS = "Paid.";
    private static final LocalDate DEFAULT_DATE = LocalDate.parse("10-05-2002", Lesson.DATE_FORMATTER);
    private static final LocalTime DEFAULT_TIME = LocalTime.parse("13:00", Lesson.TIME_FORMATTER);
    private static final Lesson DEFAULT_LESSON = new Lesson(DEFAULT_SUBJECT, DEFAULT_DATE, DEFAULT_TIME);

    private Name name;
    private Phone phone;
    private Email email;
    private FeeStatus feeStatus;
    private Address address;
    private Subject subject;
    private List<Lesson> lessons;
    private Remark remark;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        feeStatus = new FeeStatus(DEFAULT_FEE_STATUS);
        address = new Address(DEFAULT_ADDRESS);
        subject = new Subject(DEFAULT_SUBJECT);
        lessons = new ArrayList<>();
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        feeStatus = personToCopy.getFeeStatus();
        address = personToCopy.getAddress();
        subject = personToCopy.getSubject();
        lessons = personToCopy.getLessons();
        remark = personToCopy.getRemark();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code lessons} into a {@code List<Lesson>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withLessons(String... lessons) {
        this.lessons = Arrays.stream(lessons)
                .map(JsonAdaptedLesson::parseJsonLesson)
                .collect(Collectors.toList());
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code FeeStatus} of the {@code Student} that we are building.
     */
    public StudentBuilder withFeeStatus(String feeStatus) {
        this.feeStatus = new FeeStatus(feeStatus);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Student} that being built.
     */
    public StudentBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Subject} of the {@code Student} that being built.
     */
    public StudentBuilder withSubject(String subject) {
        this.subject = new Subject(subject);
        return this;
    }

    public Student build() {
        return new Student(name, phone, email, address, subject, remark, feeStatus, lessons);
    }
}
