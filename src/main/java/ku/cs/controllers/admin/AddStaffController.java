package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class AddStaffController {
    @FXML
    public void onMyTeamButtonClick() {
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
}
