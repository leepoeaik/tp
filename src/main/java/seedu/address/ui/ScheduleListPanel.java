package seedu.address.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Student;

/**
 * Panel containing the list of students.
 */
public class ScheduleListPanel extends UiPart<Region> {
    private static final String FXML = "ScheduleListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ScheduleListPanel.class);
    private ObservableList<Student> studentList;

    @FXML
    private ListView<Pair<Student, Lesson>> scheduleListView;

    /**
     * Creates a {@code ScheduleListPanel} with the given {@code ObservableList}.
     */
    public ScheduleListPanel(ObservableList<Student> studentList) {
        super(FXML);
        this.studentList = studentList;

        scheduleListView.setItems(transformList(this.studentList));
        scheduleListView.setCellFactory(listView -> new ScheduleListViewCell());
    }

    /**
     * Extracts lessons from studentList
     * Adds the lessons together with student as a pair and sorts it
     * @param studentList observable list of students
     * @return an observable list of a pair of students and lessons
     */
    public ObservableList<Pair<Student, Lesson>> transformList(ObservableList<Student> studentList) {
        List<Pair<Student, Lesson>> scheduleList = new ArrayList<>();
        for (Student student : studentList) {
            List<Lesson> studentLesson = student.getLessons();
            for (Lesson l : studentLesson) {
                if (l.getLessonStatus() == 0) {
                    scheduleList.add(new Pair(student, l));
                }
            }
        }

        Collections.sort(scheduleList, new SortDate());

        ObservableList<Pair<Student, Lesson>> observableList = FXCollections.observableList(scheduleList);
        return observableList;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class ScheduleListViewCell extends ListCell<Pair<Student, Lesson>> {
        @Override
        protected void updateItem(Pair<Student, Lesson> pair, boolean empty) {
            super.updateItem(pair, empty);
            if (empty || pair == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ScheduleCard(pair, getIndex() + 1).getRoot());
            }
        }
    }

    class SortDate implements Comparator<Pair<Student, Lesson>> {

        @Override
        public int compare(Pair<Student, Lesson> o1, Pair<Student, Lesson> o2) {
            return o1.getValue().getDate().compareTo(o2.getValue().getDate());
        }
    }

}
