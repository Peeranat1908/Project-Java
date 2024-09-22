package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import ku.cs.models.User;
import ku.cs.models.UserList;
import ku.cs.services.FXRouter;
import ku.cs.services.UserListFileDatasource;

import java.io.IOException;

public class UserDetailController {
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
    private ImageView profilePidctureImageview;

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
        nameLabel.setText("ชื่อ: " + user.getName() + " " + user.getSurname());
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

        // ถ้า role เป็น facultyStaff ให้เซ็ต IdorDepartmentLabel ว่าง
        if (user.getRole().equals("facultyStaff")) {
            IdorDepartmentLabel.setText("");
        } else {
//            // แสดงข้อมูลเพิ่มเติมใน IdorDepartmentLabel ขึ้นอยู่กับบทบาท
//            IdorDepartmentLabel.setText(user.getRole().equals("student") ? user.getId() :
//                    user.getRole().equals("departmentStaff") ? user.getDepartment() : "");
        }


//        String profilePicPath = user.getProfilePicturePath();
//        if (profilePicPath == null || profilePicPath.isEmpty()) {
//            profilePicPath = "images/profileDeafault2.png";  // ใช้รูป default ถ้าไม่มีรูปโปรไฟล์
//        }
//
//        profilePidctureImageview.setImage(new Image(profilePicPath));
//        profilePidctureImageview.setFitWidth(200);
//        profilePidctureImageview.setFitHeight(150);
//        profilePidctureImageview.setPreserveRatio(true);
//
//        Circle clip = new Circle(100, 75, 75); // centerX, centerY, radius
//        profilePidctureImageview.setClip(clip);
    }
    @FXML
    public void suspendbuttonclick() {
        datasource = new UserListFileDatasource("data", "user.csv");
        userList = datasource.readData();
        String username = user.getUsername();

        User user = userList.findUserByUsername(username);
        if (user != null) {
            user.setSuspended(true);
            datasource.writeData(userList);
            suspendlabel.setText("ผู้ใช้ " + username + " ถูกระงับเรียบร้อยแล้ว.");

        }
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
