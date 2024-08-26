package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.StudentList;
import ku.cs.services.FXRouter;
import ku.cs.services.StudentListFileDatasource;

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
    private StudentList studentList;


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
        Datasource<StudentList> datasource = new StudentListFileDatasource("data", "student-info.csv");
        studentList = datasource.readData();

        errorLabel.setText("");
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String id = idTextField.getText();
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();

        if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || usernameTextField.getText().isEmpty() || idTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || passwordTextField.getText().isEmpty() || confirmPasswordTextField.getText().isEmpty())
        {
            errorLabel.setText("Please enter your data.");
            return;
        }

        if(studentList.isExists(username, id)){
            errorLabel.setText("The data has been used!");
            return;
        }

        studentList.addNewStudent(name, surname,id, username, email, password);
        datasource.writeData(studentList);
        onRegisterButtonClick();
    }
    @FXML
    public void onRegisterButtonClick() {
        try {
            errorLabel.setText("Your data has been saved!");
            FXRouter.goTo("student");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
