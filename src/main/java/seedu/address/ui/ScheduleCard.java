package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class ScheduleCard extends UiPart<Region> {

    private static final String FXML = "ScheduleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Lesson lesson;
    public final String studentName;

    @FXML
    private HBox schedulePane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label subject;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private FlowPane lessons;
    @FXML
    private Label remark;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public ScheduleCard(Pair<Student, Lesson> pair, int displayedIndex) {
        super(FXML);
        this.lesson = pair.getValue();
        this.studentName = pair.getKey().getName().toString();
        id.setText(displayedIndex + ". ");
        name.setText(studentName);
        subject.setText(lesson.getSubject().value);
        date.setText(String.valueOf(lesson.getDate()));
        time.setText(lesson.getTime().toString());
    }
//    public ScheduleCard(Student student, int displayedIndex) {
//        super(FXML);
//        this.studentName = student.getName().toString();
//        id.setText(displayedIndex + ". ");
//        name.setText(studentName);
//    }
}
