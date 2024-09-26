package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.User;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class UserProfileController {
        @FXML
        private Label nameLabel;
        @FXML private Label usernameLabel;
        @FXML private Label roleLabel;
        @FXML private Label idLabel;

        private User user;


    @FXML
    public void initialize() {
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
            updateUI();
        } else {
            usernameLabel.setText("Invalid user data");
        }
    }

    private void updateUI() {
        if (user != null) {
            usernameLabel.setText(user.getUsername());
            roleLabel.setText(user.getRole());
            nameLabel.setText(user.getName());
            idLabel.setText((user.getId()));
        }
    }

    @FXML
    private void onBackButton() {
        try {
            FXRouter.goTo("student", user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
