package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.Student;
import ku.cs.models.StudentList;
import ku.cs.models.User;
import ku.cs.models.UserList;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.StudentListFileDatasource;
import ku.cs.services.UserListFileDatasource;
import org.mindrot.jbcrypt.BCrypt;


import java.io.IOException;

public class RegisterController {
    @FXML private Label errorLabel;
    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField idTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private PasswordField confirmPasswordTextField;
    private StudentList studentList;
    private UserList userList;

    @FXML
    private void initialize() {
        errorLabel.setText("");
        loadData();
    }

    private void loadData() {
        Datasource<StudentList> studentDatasource = new StudentListFileDatasource("data", "student-info.csv");
        studentList = studentDatasource.readData();

        Datasource<UserList> userDatasource = new UserListFileDatasource("data", "user.csv");
        userList = userDatasource.readData();
    }



    private void checkRegister() {
        errorLabel.setText("");
        String name = nameTextField.getText();
        String id = idTextField.getText();
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();

        if (name.isEmpty()  || username.isEmpty() || id.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorLabel.setText("Please enter all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            errorLabel.setText("Passwords do not match.");
            return;
        }

        if (studentList.isExists(username, id)) {
            errorLabel.setText("Username or ID already exists.");
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());


        Student newStudent = new Student(name, username, null, id, email, null, null, null, null, null);
        studentList.addStudent(newStudent);
        Datasource<StudentList> studentDatasource = new StudentListFileDatasource("data", "student-info.csv");
        studentDatasource.writeData(studentList);

        User newUser = new User(name, username, hashedPassword, "student", null,id);
        userList.addUser(newUser);
        UserListFileDatasource userDatasource = new UserListFileDatasource("data", "user.csv");
        userDatasource.writeData(userList);

        try {
            FXRouter.goTo("login-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onRegisterButtonClick() {
        checkRegister();
    }
    @FXML
    private void onLoginButtonClick() {
        try {
            FXRouter.goTo("login-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void onBackButton() {
        try {
            FXRouter.goTo("login-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
