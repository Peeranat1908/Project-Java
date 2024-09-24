package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.*;
import ku.cs.services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;

public class AddStaffController {

    @FXML
    private TextField advisorIdTextfield;
    @FXML
    private ChoiceBox<String> facultyChoiceBox;
    @FXML
    private ChoiceBox<String> majorChoiceBox;
    @FXML
    private ChoiceBox<String> roleChoiceBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label advisorIdLabel;
    @FXML
    private Label majorChoiceBoxLabel;
    @FXML
    private Label addStaffLabel;

    private UserList userList;
    private UserListFileDatasource datasource;
    private User user;
    private FacultyListFileDatasource facultyDatasource;
    private MajorListFileDatasource majorDatasource;

    @FXML
    public void initialize() {
        addStaffLabel.setText("");
        loadRoleChoices();
        loadFacultyChoices();

        roleChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handleRoleSelection(newValue);
        });
        loadData();
    }
    private void loadData() {
        Datasource<UserList> userDatasource = new UserListFileDatasource("data", "user.csv");
        userList = userDatasource.readData();
    }

    private void loadRoleChoices() {
        roleChoiceBox.getItems().addAll("อาจารย์ที่ปรึกษา", "เจ้าหน้าที่คณะ", "เจ้าหน้าที่ภาควิชา");
    }

    private void handleRoleSelection(String role) {
        if ("เจ้าหน้าที่คณะ".equals(role)) {
            majorChoiceBoxLabel.setVisible(false);
            majorChoiceBox.setVisible(false);
        } else {
            majorChoiceBoxLabel.setVisible(true);
            majorChoiceBox.setVisible(true);
        }

        if (!"อาจารย์ที่ปรึกษา".equals(role)) {
            advisorIdLabel.setVisible(false);
            advisorIdTextfield.setVisible(false);
        } else {
            advisorIdLabel.setVisible(true);
            advisorIdTextfield.setVisible(true);
        }

    }

    private void loadMajorChoices(String facultyId) {
        majorDatasource = new MajorListFileDatasource("data", "major.csv");
        majorChoiceBox.getItems().clear();

        ObservableList<String> majorNames = FXCollections.observableArrayList();
        for (Major major : majorDatasource.readData().getMajors()) {
            if (major.getFacultyId().equals(facultyId)) {
                majorNames.add(major.getMajorName());
            }
        }
        majorChoiceBox.getItems().addAll(majorNames);
    }

    private void loadFacultyChoices() {
        facultyDatasource = new FacultyListFileDatasource("data", "faculty.csv");
        facultyChoiceBox.getItems().clear();

        ObservableList<String> facultyNames = FXCollections.observableArrayList();
        for (Faculty faculty : facultyDatasource.readData().getFaculties()) {
            facultyNames.add(faculty.getFacultyName());
        }
        facultyChoiceBox.getItems().addAll(facultyNames);

        facultyChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                String selectedFacultyId = facultyDatasource.readData().getFaculties().stream()
                        .filter(faculty -> faculty.getFacultyName().equals(newValue))
                        .findFirst()
                        .map(Faculty::getFacultyId)
                        .orElse(null);

                if (selectedFacultyId != null) {
                    majorChoiceBox.setDisable(false);

                    processSelectedFacultyId(selectedFacultyId);
                }
            } else {
                majorChoiceBox.setDisable(true);
            }
        });
    }
    private void processSelectedFacultyId(String facultyId) {
        loadMajorChoices(facultyId);
    }
    @FXML
    public void addStaffButtonClick() {
        String name = nameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String role = roleChoiceBox.getValue();
        String faculty = facultyChoiceBox.getValue();
        String major = majorChoiceBox.getValue();
        String advisorId = advisorIdTextfield.getText();

        // ตรวจสอบว่ามีข้อมูลว่างหรือไม่
        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || role == null || faculty == null) {
            addStaffLabel.setText("กรุณากรอกข้อมูลให้ครบถ้วน");
            return;
        }

        // แปลงบทบาท
        if (role.equals("อาจารย์ที่ปรึกษา")) {
            role = "advisor";
        } else if (role.equals("เจ้าหน้าที่คณะ")) {
            role = "facultyStaff";
        } else if (role.equals("เจ้าหน้าที่ภาควิชา")) {
            role = "departmentStaff";
        }

        // ตรวจสอบชื่อผู้ใช้
        if (userList.findUserByUsername(username) != null) {
            addStaffLabel.setText("ชื่อผู้ใช้ " + username + " มีอยู่แล้ว กรุณาใช้ชื่ออื่น");
            return;
        }

        // ตรวจสอบชื่อ
        if (userList.findUserByName(name) != null) {
            addStaffLabel.setText("ชื่อ " + name + " มีอยู่แล้ว กรุณาใช้ชื่ออื่น");
            return;
        }

        // ตรวจสอบ advisorId ถ้ามี
        if (advisorId != null && userList.findUserById(advisorId) != null) {
            addStaffLabel.setText("ID " + advisorId + " มีอยู่แล้ว กรุณาใช้ ID อื่น");
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User newUser = null;

        // สร้างผู้ใช้ใหม่ตามบทบาท
        if (role.equals("advisor")) {
            newUser = new User(name, username, hashedPassword, null, null, role, null, false, faculty, major, false, advisorId);
        } else if (role.equals("facultyStaff")) {
            newUser = new User(name, username, hashedPassword, null, null, role, null, false, faculty, null, false, null);
        } else if (role.equals("departmentStaff")) {
            newUser = new User(name, username, hashedPassword, null, null, role, null, false, faculty, major, false, null);
        } else {
            throw new IllegalArgumentException("บทบาทไม่ถูกต้อง");
        }

        // เพิ่มผู้ใช้ใหม่ในรายชื่อผู้ใช้
        userList.addUser(newUser);
        UserListFileDatasource userDatasource = new UserListFileDatasource("data", "user.csv");
        userDatasource.writeData(userList);
        addStaffLabel.setText("เพิ่มผู้ใช้สำเร็จ: " + username);
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
    @FXML
    public void dashboardButtonClick() {
        try {
            FXRouter.goTo("dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void manageStaffdataButtonClick() {
        try {
            FXRouter.goTo("staff-table-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void homeButtonClick() {
        try {
            FXRouter.goTo("main-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
