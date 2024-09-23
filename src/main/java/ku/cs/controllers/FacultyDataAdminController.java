package ku.cs.controllers;

import javafx.fxml.FXML;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class FacultyDataAdminController {

    @FXML
    public void onLogOutButtonClick() {
        try {
            FXRouter.goTo("login-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onMyTeamButtonClick() {
        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onHomeButtonClick() {
        try {
            FXRouter.goTo("main-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
