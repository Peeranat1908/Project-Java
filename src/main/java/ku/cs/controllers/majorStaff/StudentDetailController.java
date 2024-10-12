package ku.cs.controllers.majorStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
import ku.cs.controllers.NavigationHistoryService;
import ku.cs.models.Student;
import ku.cs.models.StudentList;
import ku.cs.models.User;
import ku.cs.models.UserList;
import ku.cs.services.FXRouter;
import ku.cs.services.StudentListFileDatasource;
import ku.cs.services.UserListFileDatasource;

import java.io.IOException;

public class StudentDetailController {
    @FXML
    private Label userTextLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label advisorLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label advisorIdLabel;
    @FXML
    private Circle imagecircle;
    @FXML
    private Pane editStaffPane;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField advisorIdTextfield;
    @FXML
    private TextField idTextField;
    @FXML
    private Label errorLabel;


    private UserList userList;
    private UserListFileDatasource datasource;
    private User userStudent;
    private User user;
    private Student student;
    private StudentList studentList;
    private StudentListFileDatasource studentFileDatasource;
    @FXML
    public void initialize() {
        errorLabel.setText("");
        datasource = new UserListFileDatasource("data", "user.csv");
        userList = datasource.readData();
        studentFileDatasource = new StudentListFileDatasource("data", "student-info.csv");
        studentList = studentFileDatasource.readData();
        editStaffPane.setVisible(false);
        Object data = FXRouter.getData();
        if (data instanceof Pair) {
            Pair<User, Student> userStudentPair = (Pair<User, Student>) data;
            user = userStudentPair.getKey();
            student = userStudentPair.getValue();
            displayUserInfo(student);

        }
    }
    private void displayUserInfo(Student student1) {
        userTextLabel.setText("ข้อมูลนิสิต");
        nameLabel.setText(student1.getName());
        idLabel.setText(student1.getId());
        emailLabel.setText(student1.getEmail());
        User findAdvisor = userList.findUserById(student1.getAdvisorID());
        String advisorName;
        if (findAdvisor == null) {
            advisorName = "ยังไม่มีอาจารย์ที่ปรึกษา";
        } else {
            advisorName = findAdvisor.getName();
        }
        advisorLabel.setText(advisorName);
        advisorIdLabel.setText( student1.getAdvisorID());
        userStudent = userList.findUserByUsername(student1.getUsername());
        String profilePicPath = userStudent.getProfilePicturePath();
        if (profilePicPath == null || profilePicPath.isEmpty()) {
            profilePicPath = "/images/profileDeafault2.png";
        }
        Image profileImage = new Image(getClass().getResourceAsStream(profilePicPath));
        imagecircle.setFill(new ImagePattern(profileImage));
        imagecircle.setRadius(75);
    }
    @FXML
    public void enterEditButtonClick() {
        Student findStudent = studentList.findStudentByUsername(student.getUsername());
        User findUser = userList.findUserByUsername(student.getUsername());

        String newName = nameTextField.getText();
        if (!newName.isEmpty()) {
            findStudent.setName(newName);
            findUser.setName(newName);
        }

        String newId = idTextField.getText();
        if (!newId.isEmpty()) {
            if (studentList.findStudentById(newId) != null) {
                errorLabel.setText("Id " + newId + " มีอยู่แล้ว");
                return;
            }
            findStudent.setId(newId);
            findUser.setId(newId);
        }

        String newEmail = emailTextField.getText();
        if (!newEmail.isEmpty()) {
            if (studentList.findStudentByEmail(newEmail) != null) {
                errorLabel.setText("Email " + newEmail + " มีอยู่แล้ว");
                return;
            }
            findStudent.setEmail(newEmail);
        }


        String newAdvisorId = advisorIdTextfield.getText().isEmpty() ? null : advisorIdTextfield.getText();
        findStudent.setAdvisorID(newAdvisorId);

        studentFileDatasource.writeData(studentList);
        datasource.writeData(userList);

        nameTextField.clear();
        idTextField.clear();
        emailTextField.clear();
        advisorIdTextfield.clear();
        errorLabel.setText("");

        displayUserInfo(findStudent);

        editStaffPane.setVisible(false);
    }
    @FXML
    private void editStudentButtonClick() {
        editStaffPane.setVisible(!editStaffPane.isVisible());
    }
    @FXML
    public void onMyTeamButtonClick() throws RuntimeException {
        NavigationHistoryService.getInstance().pushPage("student-detail-major-staff");
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
    @FXML
    public void homeButtonClick() {
        try {
            FXRouter.goTo("departmentStaff",user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
