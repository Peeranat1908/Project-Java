package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ku.cs.services.FXRouter;
import java.io.IOException;


public class StudentAppealController {

    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("student");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onNormalAppealClick() {
        try {
            FXRouter.goTo("normal-appeal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onLeaveAppealClick() {
        try {
            FXRouter.goTo("leave-appeal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onEnrollAppealClick() {
        try {
            FXRouter.goTo("enroll-appeal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}