package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.StudentList;
import ku.cs.models.UserCredentialList;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.StudentListFileDatasource;
import ku.cs.services.UserCredentialsListFileDatasource;

import java.io.IOException;

public class RegisterController {
    @FXML private Label errorLabel;
    @FXML private TextField nameTextField;
    @FXML private TextField surnameTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField idTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private PasswordField confirmPasswordTextField;
    @FXML private StudentList studentList;
    @FXML private UserCredentialList userCredentialList;

    @FXML
    private void initialize() {
        errorLabel.setText("");
    }
    @FXML
    public void onLoginButtonClick() {
        try {
            FXRouter.goTo("login-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onRegisterButtonClick(ActionEvent event) {
        checkRegister();
    }

    private void checkRegister()  {
        Datasource<StudentList> studentDatasource = new StudentListFileDatasource("data", "student-info.csv");
        studentList = studentDatasource.readData();

        Datasource<UserCredentialList> userCredentialDatasource = new UserCredentialsListFileDatasource("data", "UserCredentials.csv");
        userCredentialList = userCredentialDatasource.readData();

        errorLabel.setText("");
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String id = idTextField.getText();
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();

        if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || id.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorLabel.setText("Please enter your data.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            errorLabel.setText("Passwords do not match.");
            return;
        }

        if(studentList.isExists(username, id)) {
            errorLabel.setText("The data has been used!");
            return;
        }

        studentList.addNewStudent(name, surname, id, username, email, password);
        studentDatasource.writeData(studentList);

        userCredentialList.addNewUser(username, password, "student");
        userCredentialDatasource.writeData(userCredentialList);

        onRegisterButtonClick();
    }

    @FXML
    public void onRegisterButtonClick() {
        try {
            FXRouter.goTo("student");
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
