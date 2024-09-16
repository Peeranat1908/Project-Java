package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.User;
import ku.cs.models.Student;
import ku.cs.models.Admin;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.UserAccountsListFileDatasource;
import ku.cs.models.UserAccountList;
import java.io.IOException;

public class LoginController {
    @FXML private Label errorLabel;
    @FXML private TextField UsernameTextField;
    @FXML private PasswordField PasswordTextField;

    private UserAccountList userList;

    @FXML
    private void initialize() {
        errorLabel.setText("");
    }

    private void checkLogin() throws IOException {
        Datasource<UserAccountList> datasource = new UserAccountsListFileDatasource("data", "userAccount.csv");
        userList = datasource.readData();

        String username = UsernameTextField.getText();
        String password = PasswordTextField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter your data.");
            throw new IOException("Invalid username or password");
        }

        boolean isAuthenticated = false;

        for (User user : userList.getUsers()) {
            if (user.check(username, password)) {
                // Update last login and save data
                userList.updateLastLogin(username);
                datasource.writeData(userList);

                // Navigate based on role and pass user data
                navigateByRole(user);
                isAuthenticated = true;
                break;
            }
        }

        if (!isAuthenticated) {
            errorLabel.setText("Username or password is incorrect. Please try again.");
            throw new IOException("Username or password is incorrect.");
        }
    }

    private void navigateByRole(User user) throws IOException {
        switch (user.getRole()) {
            case "student":
                FXRouter.goTo("student", user);
                break;
            case "admin":
                FXRouter.goTo("main-admin", user);
                break;
            case "advisor":
                FXRouter.goTo("advisor", user);
                break;
            case "facultyStaff":
                FXRouter.goTo("facultyStaff", user);
                break;
            case "departmentStaff":
                FXRouter.goTo("departmentStaff", user);
                break;
            default:
                errorLabel.setText("Invalid role. Please contact the administrator.");
                throw new IOException("Invalid role.");
        }
    }

    @FXML
    public void onLoginButtonClick() {
        try {
            checkLogin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onRegisterButtonClick() {
        try {
            FXRouter.goTo("register");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onResetPasswordButton() {
        try {
            FXRouter.goTo("resetPassword");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
