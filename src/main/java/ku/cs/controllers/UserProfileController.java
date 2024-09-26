package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.User;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class UserProfileController {
        @FXML
        private Label nameLabel;
        @FXML private Label usernameLabel;
        @FXML private Label roleLabel;
        @FXML private Label idLabel;
        @FXML private Circle imagecircle;
        private User user;


    @FXML
    public void initialize() {
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
            updateUI();
            String profilePicPath = user.getProfilePicturePath();
            if (profilePicPath == null || profilePicPath.isEmpty()) {
                profilePicPath = "/images/profileDeafault2.png";
            }
            Image profileImage = new Image(getClass().getResourceAsStream(profilePicPath));
            imagecircle.setFill(new ImagePattern(profileImage));
        } else {
            usernameLabel.setText("Invalid user data");
        }
    }

    private void updateUI() {
        if (user != null) {
            usernameLabel.setText(user.getUsername());
            roleLabel.setText(user.getRole());
            idLabel.setText(user.getId());
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
