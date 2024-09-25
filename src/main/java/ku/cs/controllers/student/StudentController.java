package ku.cs.controllers.student;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.User;
import ku.cs.services.FXRouter;


import java.io.IOException;

public class StudentController {
    @FXML
    private Label usernameLabel;


    private User user;

    @FXML
    private void initialize() {
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

        }
    }

    // Method สำหรับการคลิกปุ่มเพื่อไปยังหน้าทีมของฉัน
    @FXML
    public void onMyTeamButtonClick() {
        navigateTo("my-team");
    }

    // Method สำหรับการคลิกปุ่มเพื่อไปยังหน้าการยื่นคำร้องของนักเรียน
    @FXML
    public void selectAppealButtonClick() {
        navigateTo("student-appeal");
    }

    // Method สำหรับการคลิกปุ่มเพื่อไปยังหน้าการติดตามคำร้อง
    @FXML
    public void onAppealTrackingClick() {
        navigateTo("appeal-tracking");
    }

    @FXML
    public void onPictureClick() {
        navigateTo("user-profile", user);
    }

    private void navigateTo(String route, Object data) {
        try {
            FXRouter.goTo(route, data); // ส่งข้อมูลไปยัง route ที่กำหนด
        } catch (IOException e) {
            System.err.println("Navigation to " + route + " failed: " + e.getMessage());
        }
    }


    // Method สำหรับนำทางไปยังหน้าต่างๆ
    private void navigateTo(String route) {
        try {
            FXRouter.goTo(route);
        } catch (IOException e) {
            System.err.println("Navigation to " + route + " failed: " + e.getMessage());
        }
    }
}
