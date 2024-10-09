package ku.cs.controllers.advisor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import ku.cs.controllers.NavigationHistoryService;
import ku.cs.models.Student;
import ku.cs.models.StudentList;
import ku.cs.models.User;
import ku.cs.services.FXRouter;
import ku.cs.services.StudentListFileDatasource;

import java.io.IOException;

public class MainAdvisorController {

    @FXML
    private Label errorLabel;
    @FXML
    private TableView<Student> studentAdvisorTableView;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButtonClick;

    private StudentList studentList;
    private ObservableList<Student> studentObservableList;

    private User user;

    private String selectedStudentId;

    @FXML
    public void initialize() {
        errorLabel.setText("");

        StudentListFileDatasource datasource = new StudentListFileDatasource("data", "student-info.csv");
        studentList = datasource.readData();

        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
        }
        StudentList filteredStudentList = new StudentList();
        for (Student student : studentList.getStudents()) {
            if (student.getAdvisorID().equals(user.getId())) {
                filteredStudentList.addStudent(student);
            }
        }

        showTable(filteredStudentList);

        studentAdvisorTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> observableValue, Student oldValue, Student newValue) {
                if (newValue != null) {
                    selectedStudentId = newValue.getId();
                    String advisorId = newValue.getAdvisorID();
                    try {
                        FXRouter.goTo("advisor-appeal-page", new Pair<>(user, selectedStudentId));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        searchButtonClick.setOnAction(actionEvent -> searchStudent());

    }

    private void showTable(StudentList studentList) {

        TableColumn<Student, String> idColumn = new TableColumn<>("Student ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> facultyColumn = new TableColumn<>("Faculty");
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));

        TableColumn<Student, String> majorColumn = new TableColumn<>("Major");
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));

        TableColumn<Student, String> advisorIDColumn = new TableColumn<>("Advisor ID");
        advisorIDColumn.setCellValueFactory(new PropertyValueFactory<>("advisorID"));

        studentAdvisorTableView.getColumns().clear();
        studentAdvisorTableView.getColumns().addAll(idColumn, nameColumn, facultyColumn, majorColumn, advisorIDColumn);

        studentObservableList = FXCollections.observableArrayList(studentList.getStudents());


        studentAdvisorTableView.setItems(studentObservableList);
    }

    private void searchStudent() {
        String searchQuery = searchTextField.getText().trim();
        if (searchQuery.isEmpty()) {
            studentAdvisorTableView.setItems(studentObservableList);
            errorLabel.setText("Please enter search criteria.");
            return;
        }

        clearErrorLabels();

        Student foundStudent = studentList.findStudentById(searchQuery);
        if (foundStudent == null) {
            foundStudent = studentList.findStudentByName(searchQuery);
        }
        // check ID ของนักเรียนที่โชว์ว่าตรงกับอาจารย์ที่ Login มาไหม
        if (foundStudent != null && foundStudent.getAdvisorID().equals(user.getId())) {
            studentAdvisorTableView.setItems(FXCollections.observableArrayList(foundStudent));
        } else {
            studentAdvisorTableView.getItems().clear();
            errorLabel.setText("Student not found.");
        }
        clearTextFiled();
    }
    private void setError(Label label, String message) {
        clearErrorLabels();
        label.setText(message);
    }

    private void clearErrorLabels() {
        errorLabel.setText("");
    }

    private void clearTextFiled(){
        searchTextField.clear();
    }

    @FXML
    public void onMyTeamButtonClick() throws RuntimeException {
        NavigationHistoryService.getInstance().pushPage("main-advisor");
        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}