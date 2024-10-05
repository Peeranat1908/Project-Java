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
    @FXML private Label nameLabel;
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
                profilePicPath = "/images/profileDeafault2.png"; // Ensure the path is correct
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
        }
    }

    @FXML
    private void onBackButton() {
        try {
            navigateByRole(user);
        } catch (IOException e) {
            e.printStackTrace(); // Print error to the console for debugging purposes
        }
    }
    @FXML
    private void onLogOutButtonClick(){
        try{
            FXRouter.goTo("login-page");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void navigateByRole(User user) throws IOException {
        if (user == null) {
            throw new IOException("User is not logged in.");
        }

        switch (user.getRole()) {
            case "student":
                FXRouter.goTo("student", user);
                break;
            case "admin":
                FXRouter.goTo("main-admin", user);
                break;
            case "advisor":
                if (user.isFirstlogin()) {
                    System.out.println("Please change your password before your first login."); // Print a message instead of using a label
                    return;
                }
                FXRouter.goTo("advisor", user);
                break;
            case "facultyStaff":
                if (user.isFirstlogin()) {
                    System.out.println("Please change your password before your first login.");
                    return;
                }
                FXRouter.goTo("facultyStaff", user);
                break;
            case "departmentStaff":
                if (user.isFirstlogin()) {
                    System.out.println("Please change your password before your first login.");
                    return;
                }
                FXRouter.goTo("departmentStaff", user);
                break;
            default:
                System.out.println("Invalid role. Please contact the administrator.");
                throw new IOException("Invalid role.");
        }
    }
}
