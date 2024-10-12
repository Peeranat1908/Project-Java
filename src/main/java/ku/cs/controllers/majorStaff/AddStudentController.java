package ku.cs.controllers.majorStaff;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.controllers.NavigationHistoryService;
import ku.cs.models.*;
import ku.cs.services.*;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AddStudentController {
    @FXML
    private TextField advisorIdTextfield;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private Label addStudentLabel;

    private UserList userList;
    private UserListFileDatasource datasource;
    private User user;
    private StudentList studentList;
    private PreRegisterStudentListFileDatasource studentFileDatasource;
    @FXML
    public void initialize() {
        addStudentLabel.setText("");
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
        }
        studentFileDatasource = new PreRegisterStudentListFileDatasource("data", "studentPreRegister.csv");
        studentList = studentFileDatasource.readData();
    }

    @FXML
    public void addStaffButtonClick() {
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String id = idTextField.getText();
        String advisorId = advisorIdTextfield.getText().isEmpty() ? null : advisorIdTextfield.getText();

        if (name.isEmpty() || email.isEmpty() || id.isEmpty() ){
            addStudentLabel.setText("กรุณากรอกข้อมูลให้ครบถ้วน");
            return;
        }

        if (studentList.findUserByName(name) != null) {
            addStudentLabel.setText("ชื่อ " + name + " มีอยู่แล้ว");
            return;
        }
        if (studentList.findStudentByEmail(email) != null) {
            addStudentLabel.setText("Email " + email + " มีอยู่แล้ว");
            return;
        }
        if (studentList.findStudentById(id) != null) {
            addStudentLabel.setText("ID " + id + " มีอยู่แล้ว");
            return;
        }

        Student newStudent = new Student(name, id, email, user.getFaculty(), user.getMajor(), advisorId);

        studentList.addStudent(newStudent);
        PreRegisterStudentListFileDatasource studentDatasource = new PreRegisterStudentListFileDatasource("data", "studentPreRegister.csv");
        studentDatasource.writeData(studentList);
        addStudentLabel.setText("เพิ่มผู้ใช้สำเร็จ: " + name);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("สำเร็จ");
        alert.setHeaderText(null);
        alert.setContentText("เพิ่มผู้ใช้สำเร็จ: " + name);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

                clearInputFields();
            }
        });;
    }
    private void clearInputFields() {
        nameTextField.clear();
        idTextField.clear();
        emailTextField.clear();
        advisorIdTextfield.clear();
        addStudentLabel.setText("");


    }
    @FXML
    public void onMyTeamButtonClick() throws RuntimeException {
        NavigationHistoryService.getInstance().pushPage("add-student");
        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onLogoutButtonClick() {
        try {
            FXRouter.goTo("login-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void homeButtonClick() {
        try {
            FXRouter.goTo("departmentStaff",user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}