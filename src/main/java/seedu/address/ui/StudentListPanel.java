package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

/**
 * Panel containing the list of students.
 */
public class StudentListPanel extends UiPart<Region> {
    private static final String FXML = "StudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);
    private final MainWindow mainWindow;

    @FXML
    private ListView<Student> studentListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public StudentListPanel(MainWindow mainWindow, ObservableList<Student> studentList) {
        super(FXML);
        this.mainWindow = mainWindow;
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> {
            //Solution below inspired by group AY2023/24-T16-1
            StudentListViewCell studentCells = new StudentListViewCell();
            studentCells.setOnMouseClicked(mouseEvent -> {
                StudentListViewCell clicked = (StudentListViewCell) mouseEvent.getSource();
                mainWindow.updateScheduleListPanel(clicked.getStudent());
            });
            return studentCells;
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class StudentListViewCell extends ListCell<Student> {
        private Student student;
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                this.student = student;
                setGraphic(new StudentCard(student, getIndex() + 1).getRoot());
            }
        }

        public Student getStudent() {
            return this.student;
        }
    }

}
