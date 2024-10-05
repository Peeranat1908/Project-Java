package ku.cs.controllers.student;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.controllers.NavigationHistoryService;
import ku.cs.models.User;
import ku.cs.models.Student;
import ku.cs.services.FXRouter;

import java.io.IOException;
public class StudentController {
    @FXML
    private Label usernameLabel;

    private User user;

    private Student student;

    @FXML Label errorLabel;



    @FXML
    private void initialize() {
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;

            if (user instanceof Student) {
                student = (Student) user;
            } else {

                System.out.println("User is not a Student");
            }

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
    public void onMyTeamButtonClick() throws RuntimeException {
        NavigationHistoryService.getInstance().pushPage("student");
        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Method สำหรับการคลิกปุ่มเพื่อไปยังหน้าการยื่นคำร้องของนักเรียน
    @FXML
    public void selectAppealButtonClick() {
            if(student.getAdvisorID() == null || student.getAdvisorID().equals("\"\"")) {
                errorLabel.setVisible(true);
            }else{
                navigateTo("student-appeal", user);
            }
    }

    // Method สำหรับการคลิกปุ่มเพื่อไปยังหน้าการติดตามคำร้อง
    @FXML
    public void onAppealTrackingClick() {
        try{
            FXRouter.goTo("appeal-tracking", user);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
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

}
