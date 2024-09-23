package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.User;
import ku.cs.models.UserList;
import ku.cs.services.FXRouter;
import ku.cs.services.UserListFileDatasource;

import java.io.IOException;

public class StaffEditController {
    @FXML
    private Label suspendlabel;
    @FXML
    private Label userTextLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label facultyLabel;
    @FXML
    private Label IdorDepartmentLabel;

    @FXML
    private Circle imagecircle;

    private UserList userList;
    private UserListFileDatasource datasource;
    private User user;


    public void initialize() {
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
        }
        suspendlabel.setText("");
        displayUserInfo();
    }


    private void displayUserInfo() {
        nameLabel.setText("ชื่อ: " + user.getName());
        usernameLabel.setText("ชื่อผู้ใช้: " + user.getUsername());
        facultyLabel.setText("คณะ: " + user.getFaculty());


        if (user.getRole().equals("student")) {
            userTextLabel.setText("ข้อมูลนิสิต");
        } else if (user.getRole().equals("advisor")) {
            userTextLabel.setText("ข้อมูลอาจารย์ที่ปรึกษา");
        } else if (user.getRole().equals("facultyStaff")) {
            userTextLabel.setText("ข้อมูลเจ้าหน้าที่คณะ");
        } else if (user.getRole().equals("departmentStaff")) {
            userTextLabel.setText("ข้อมูลเจ้าหน้าที่ภาควิชา");
        }

        if (user.getRole().equals("facultyStaff")) {
            IdorDepartmentLabel.setText("");
        } else {
            IdorDepartmentLabel.setText("ภาควิชา: " +user.getDepartment());
        }


        String profilePicPath = user.getProfilePicturePath();
        if (profilePicPath == null || profilePicPath.isEmpty()) {
            profilePicPath = "/images/profileDeafault2.png"; // รูปเริ่มต้น
        }

        // โหลดรูปภาพ
        Image profileImage = new Image(getClass().getResourceAsStream(profilePicPath));

        // ใช้ ImagePattern สำหรับเติมรูปภาพลงใน Circle
        imagecircle.setFill(new ImagePattern(profileImage));

        // ตั้งรัศมี (radius) ถ้าจำเป็น
        imagecircle.setRadius(75);;

    }


    @FXML
    private void onListButtonClick() {
        try {
            FXRouter.goTo("main-admin");
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
    @FXML
    public void onLogoutButtonClick() {
        try {
            FXRouter.goTo("login-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
