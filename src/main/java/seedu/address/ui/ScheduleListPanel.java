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

    @FXML
    private ListView<Pair<Student, Lesson>> scheduleListView;
//    @FXML
//    private ListView<Student> scheduleListView;


    /**
     * Creates a {@code ScheduleListPanel} with the given {@code ObservableList}.
     */
    public ScheduleListPanel(ObservableList<Pair<Student, Lesson>> scheduleList) {
        super(FXML);

//        scheduleListView.setItems(transformList(studentList));
//        System.out.println(studentList);

        scheduleListView.setItems(scheduleList);
        scheduleListView.setCellFactory(listView -> new ScheduleListViewCell());
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

//    class ScheduleListViewCell extends ListCell<Student> {
//
//
//        @Override
//        protected void updateItem(Student student, boolean empty) {
//            super.updateItem(student, empty);
//
//            if (empty || student == null) {
//                setGraphic(null);
//                setText(null);
//            } else {
//
//                setGraphic(new ScheduleCard(student, getIndex() + 1).getRoot());
//            }
//        }
//    }



}
