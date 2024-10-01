package ku.cs.controllers.majorStaff;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.Student;
import ku.cs.models.StudentList;
import ku.cs.models.User;
import ku.cs.services.*;

import java.io.IOException;

public class StudentInMajorController {
    @FXML
    private Label nameLabel;
    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButtonClick;

    private StudentList studentList;

    private Datasource<StudentList> datasource;
    private User user;

    @FXML
    public void initialize() {
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
            nameLabel.setText(user.getUsername());
        }

//        errorLabel.setText("");
        datasource = new MajorStaffListFileDataSource("data", "student-in-department.csv");
        studentList = datasource.readData();
        showTable(studentList);

        studentTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue observableValue, Student oldValue, Student newValue) {
                if (newValue != null){
                }
            }
        });

        //searchButtonClick.setOnAction(actionEvent -> searchStudent());
    }

    private void showTable(StudentList studentList) {
        // กำหนด column ให้มี title ว่า ID และใช้ค่าจาก getter id ของ object StudentAdvisor
        TableColumn<Student, String> idColumn = new TableColumn<>("Student ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // กำหนด column ให้มี title ว่า Name และใช้ค่าจาก getter name ของ object StudentAdvisor
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String>  usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));

        // กำหนด column ให้มี title ว่า Faculty และใช้ค่าจาก getter faculty ของ object StudentAdvisor
        TableColumn<Student, String> emailColumn = new TableColumn<>("email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // ล้าง column เดิมทั้งหมดที่มีอยู่ใน table แล้วเพิ่ม column ใหม่
        studentTableView.getColumns().clear();
        studentTableView.getColumns().add(idColumn);
        studentTableView.getColumns().add(nameColumn);
        studentTableView.getColumns().add(usernameColumn);
        studentTableView.getColumns().add(emailColumn);

        studentTableView.getItems().clear();

        for (Student student: studentList.getStudents()) {
            studentTableView.getItems().add(student);
        }
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
    public void onHomeButtonClick() {
        navigateTo("departmentStaff", user);
    }

    @FXML
    public void onUserProfileButton(){
        navigateTo("user-profile", user);
    }

    @FXML
    public void onEditEndorserButton(){
        navigateTo("edit-major-endorser", user);
    }

    private void navigateTo(String route, Object data) {
        try {
            FXRouter.goTo(route, data);
        } catch (IOException e) {
            System.err.println("Navigation to " + route + " failed: " + e.getMessage());
        }
    }

}


