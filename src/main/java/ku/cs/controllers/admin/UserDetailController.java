package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.User;

public class UserDetailController {

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

    private User user;

    // รับข้อมูลผู้ใช้เมื่อเริ่มต้น
    public void initialize(User user) {
        this.user = user;
        displayUserInfo();
    }

    // ฟังก์ชันที่ใช้แสดงข้อมูลผู้ใช้
    private void displayUserInfo() {
        // เซ็ต nameLabel ด้วยชื่อและนามสกุลของผู้ใช้
        nameLabel.setText(user.getName() + " " + user.getSurname());

        // เซ็ต usernameLabel ด้วยชื่อผู้ใช้
        usernameLabel.setText(user.getUsername());
//        facultyLabel.setText();
        // เซ็ต facultyLabel ขึ้นอยู่กับบทบาทของผู้ใช้
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

        // ตั้งค่ารูปโปรไฟล์ ถ้าไม่มีให้ใช้รูป default
        String profilePicPath = user.getProfilePicturePath();
        if (profilePicPath == null || profilePicPath.isEmpty()) {
            profilePicPath = "/images/default-profile-pic.jpg";  // ใช้รูป default ถ้าไม่มีรูปโปรไฟล์
        }
        profilePidctureImageview.setImage(new Image(profilePicPath));
    }
}
