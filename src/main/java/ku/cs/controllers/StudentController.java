package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.User;
import ku.cs.services.FXRouter;
import java.io.IOException;

public class StudentController

{   @FXML
    private Label usernameLabel;
    private User user;

    @FXML
    private void initialize() {
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
        } else {
            usernameLabel.setText("Invalid user data");
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

    @FXML
    public void selectAppealButtonClick() {
        try {
            FXRouter.goTo("student-appeal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
