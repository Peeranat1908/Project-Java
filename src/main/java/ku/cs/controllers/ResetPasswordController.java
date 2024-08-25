package ku.cs.controllers;

import javafx.fxml.FXML;
import ku.cs.services.FXRouter;
import java.io.IOException;

public class ResetPasswordController {
    @FXML
    public void ResetPasswordButtonClick() {
        try {
            FXRouter.goTo("login-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
