package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.UserAccount;
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

        for (UserAccount user : userList.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {

                userList.updateLastLogin(username);

                datasource.writeData(userList);

                navigateByRole(user.getRole());
                isAuthenticated = true;
                break;
            }
        }

        if (!isAuthenticated) {
            errorLabel.setText("Username or password is incorrect. Please try again.");
            throw new IOException("Username or password is incorrect.");
        }
    }

    private void navigateByRole(String role) throws IOException {
        switch (role) {
            case "student":
                FXRouter.goTo("student");
                break;
            case "admin":
                FXRouter.goTo("main-admin");
                break;
            case "advisor":
                FXRouter.goTo("advisor");
                break;
            case "facultyStaff":
                FXRouter.goTo("facultyStaff");
                break;
            case "departmentStaff":
                FXRouter.goTo("departmentStaff");
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
            FXRouter.goTo("student");
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