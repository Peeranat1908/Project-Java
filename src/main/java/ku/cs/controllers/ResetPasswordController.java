package ku.cs.controllers;

import javafx.fxml.FXML;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import ku.cs.services.UserCredentialsListFileDatasource;
import ku.cs.models.UserCredential;
import ku.cs.models.UserCredentialList;
import javafx.scene.control.TextField;

public class ResetPasswordController {

    @FXML private Label errorLabel;
    @FXML private TextField usernameTextfield;
    @FXML private PasswordField oldPasswordTextfield;
    @FXML private PasswordField newPasswordTextfield;
    @FXML private PasswordField confirmPasswordTextfield;

    private UserCredentialList userList;

    @FXML
    private void initialize() {
        errorLabel.setText("");
    }

    @FXML
    private void onResetPasswordButtonClick() {
        try {
            resetPassword();
        } catch (IOException e) {
            errorLabel.setText("An error occurred. Please try again.");
        }
    }

    private void resetPassword() throws IOException {
        Datasource<UserCredentialList> datasource = new UserCredentialsListFileDatasource("data", "UserCredentials.csv");
        userList = datasource.readData();

        String username = usernameTextfield.getText();
        String oldPassword = oldPasswordTextfield.getText();
        String newPassword = newPasswordTextfield.getText();
        String confirmPassword = confirmPasswordTextfield.getText();

        if (username.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            errorLabel.setText("Please fill in all fields.");
            return;
        }

        boolean isUserFound = false;

        for (UserCredential user : userList.getUsers()) {
            if (user.getUsernameName().equals(username) && user.getPassword().equals(oldPassword)) {
                if (!newPassword.equals(confirmPassword)) {
                    errorLabel.setText("New passwords do not match. Please try again.");
                    return;
                }

                user.setPassword(newPassword);
                datasource.writeData(userList);
                errorLabel.setText("Password has been reset successfully.");
                isUserFound = true;
                break;
            }
        }

        if (!isUserFound) {
            errorLabel.setText("Username or old password is incorrect. Please try again.");
            return;
        }
        FXRouter.goTo("login-page");
    }
}

