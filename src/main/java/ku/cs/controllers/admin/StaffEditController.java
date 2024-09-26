package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.Faculty;
import ku.cs.models.Major;
import ku.cs.models.User;
import ku.cs.models.UserList;
import ku.cs.services.FXRouter;
import ku.cs.services.FacultyListFileDatasource;
import ku.cs.services.MajorListFileDatasource;
import ku.cs.services.UserListFileDatasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class StaffEditController {

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
    @FXML
    private Pane editStaffPane;
    @FXML
    private ChoiceBox<String> facultyChoiceBox;
    @FXML
    private ChoiceBox<String> majorChoiceBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField advisorIdTextfield;
    @FXML
    private Label advisorIdLabel;
    @FXML
    private Label majorChoiceBoxLabel;


    private UserList userList;
    private UserListFileDatasource datasource;
    private User user;
    private FacultyListFileDatasource facultyDatasource;
    private MajorListFileDatasource majorDatasource;

    public void initialize() {
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
        }
        displayUserInfo();
        editStaffPane.setVisible(false);

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
            IdorDepartmentLabel.setText(""); // ไม่แสดงภาควิชา
        } else if (user.getRole().equals("departmentStaff")) {
            userTextLabel.setText("ข้อมูลเจ้าหน้าที่ภาควิชา");
        }

        if (!user.getRole().equals("facultyStaff")) {
            IdorDepartmentLabel.setText("ภาควิชา: " + user.getMajor());

        }
        if (user.getRole().equals("facultyStaff")) {
            majorChoiceBoxLabel.setVisible(false);

        }if (!user.getRole().equals("advisor")) {
            advisorIdTextfield.setVisible(false);
            advisorIdLabel.setVisible(false);

        }


        String profilePicPath = user.getProfilePicturePath();
        if (profilePicPath == null || profilePicPath.isEmpty()) {
            profilePicPath = "/images/profileDeafault2.png";
        }

        Image profileImage = new Image(getClass().getResourceAsStream(profilePicPath));
        imagecircle.setFill(new ImagePattern(profileImage));
        imagecircle.setRadius(75);
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
    public void enterEditButtonClick() {
        String newName = nameTextField.getText();
        if (!newName.isEmpty()) {
            user.setName(newName);
        }

        String newUsername = usernameTextField.getText();
        if (!newUsername.isEmpty()) {
            user.setUsername(newUsername);
        }

        String newPassword = passwordTextField.getText();
        if (!newPassword.isEmpty()) {
            user.setPassword(newPassword);
        }

        String newId = advisorIdTextfield.getText();
        if (!newId.isEmpty()) {
            user.setId(newId);
        }

        String selectedFaculty = facultyChoiceBox.getValue();
        if (selectedFaculty != null) {
            user.setFaculty(selectedFaculty);
        }

        String selectedMajor = majorChoiceBox.getValue();
        if (selectedMajor != null) {
            user.setMajor(selectedMajor);
        }

        datasource = new UserListFileDatasource("data", "user.csv");
        userList = datasource.readData();

        for (User findUser : userList.getUsers()) {
            if (findUser.getUsername().equals(user.getUsername())) {
                findUser.setName(user.getName());
                findUser.setPassword(user.getPassword());
                findUser.setFaculty(user.getFaculty());
                findUser.setMajor(user.getMajor());
                findUser.setId(user.getId());
                break;
            }
        }

        UserListFileDatasource userDatasource = new UserListFileDatasource("data", "user.csv");
        userDatasource.writeData(userList);

        displayUserInfo();
        editStaffPane.setVisible(false);
    }
    @FXML
    private void editStaffButtonClick() {
        editStaffPane.setVisible(true);
        loadFacultyChoices();
        majorChoiceBox.setDisable(true);
        if (user.getRole().equals("advisor")) {
            majorChoiceBox.setVisible(false);
        }
        if (user.getRole().equals("facultyStaff")) {
            majorChoiceBox.setVisible(false);
        } else {
            majorChoiceBox.setVisible(true);
            facultyChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
//                    loadMajorChoices(newValue);
                    majorChoiceBox.setDisable(false);
                } else {
                    majorChoiceBox.setDisable(true);
                }
            });
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
