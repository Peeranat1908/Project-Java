package ku.cs.controllers;

import javafx.fxml.FXML;
import ku.cs.services.FXRouter;
import java.io.IOException;

public class RegisterController {
    @FXML
    public void onLoginButtonClick() {
        try {
            FXRouter.goTo("hello");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onRegisterButtonClick() {
        try {
            FXRouter.goTo("hello");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
