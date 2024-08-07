package ku.cs.controllers;

import javafx.fxml.FXML;
import ku.cs.services.FXRouter;
import java.io.IOException;

public class RegisterController {
    @FXML
    public void onLoginButtonClick() {
        try {
            FXRouter.goTo("login-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onRegisterButtonClick() {
        try {
            FXRouter.goTo("student");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
