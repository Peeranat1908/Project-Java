package ku.cs.controllers.majorStaff;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import ku.cs.models.Student;
import ku.cs.models.StudentList;
import ku.cs.models.User;
import ku.cs.services.*;

import java.io.IOException;

public class StudentInMajorController {

    @FXML
    private TableView<Student> studentTableView;

    private StudentList studentList;
    @FXML
    private TextField searchUserTextfield;

    private Datasource<StudentList> datasource;
    private User user;
    Datasource<StudentList> studentDatasource;
    StudentList studentlistInMajor;

    @FXML
    public void initialize() {
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;

        }

        studentDatasource = new StudentListFileDatasource("data", "student-info.csv");
        studentList = studentDatasource.readData();
        studentlistInMajor = studentList.getStudentsListBYMajor(user.getMajor());
        showTable(studentlistInMajor);

        studentTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue observableValue, Student oldValue, Student newValue) {
            }
        });

        searchUserTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue);
        });
        studentTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Student selectedStudent  = studentTableView.getSelectionModel().getSelectedItem();
            } else if (event.getClickCount() == 2) {
                Student selectedStudent  = studentTableView.getSelectionModel().getSelectedItem();
                if (selectedStudent != null) {
                    try {
                        Pair<User, Student> userStudentPair = new Pair<>(user, selectedStudent);
                        FXRouter.goTo("student-detail-major-staff", userStudentPair);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void filterTable(String searchText) {
        StudentList filteredList = new StudentList();
        for (Student student : studentlistInMajor.getStudents()) {
            if (student.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                    student.getUsername().toLowerCase().contains(searchText.toLowerCase()) ||
                    student.getEmail().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.addStudent(student);
            }
        }
        studentTableView.getItems().setAll(filteredList.getStudents());
    }

    private void showTable(StudentList studentList) {
        TableColumn<Student, String> idColumn = new TableColumn<>("Student ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> emailColumn = new TableColumn<>("email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Student, String> advisorColumn = new TableColumn<>("AdvisorId");
        advisorColumn.setCellValueFactory(new PropertyValueFactory<>("advisorID"));

        studentTableView.getColumns().clear();
        studentTableView.getColumns().addAll(idColumn, nameColumn, emailColumn, advisorColumn);

        studentTableView.getItems().setAll(studentList.getStudents());
    }




    @FXML
    public void onMyTeamButtonClick() throws RuntimeException {
        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onRequestButtonClick() {
        try {
            FXRouter.goTo("request-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void addStudentButtonClick() {
        try {
            FXRouter.goTo("add-student",user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


