package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.FXRouter;
import java.io.IOException;

public class StudentController

{
    @FXML
    public void onMyTeamButtonClick() {
        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void selectAppealButtonClick() {
        try {
            FXRouter.goTo("student-appeal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onAppealTrackingClick(){
        try {
            FXRouter.goTo("appeal-tracking");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
