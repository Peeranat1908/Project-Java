package ku.cs.controllers.majorStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.models.MajorEndorserList;
import ku.cs.models.User;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.MajorEndorserListFileDatasource;

import java.io.IOException;

public class AddMajorEndorserController {
    @FXML private Label nameLabel;
    @FXML private Button confirmButton;
    @FXML private Label errorLabel1;
    @FXML private Label errorLabel2;
    @FXML private Label errorLabel3;
    @FXML private Label errorLabel4;
    @FXML private TextField nameId, positionId, facultyId, majorId;

    private User user;
    private Datasource<MajorEndorserList> datasource;
    private MajorEndorserList majorEndorserList;

    @FXML
    public void initialize() {
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
            updateUI();
        } else {
            nameLabel.setText("Invalid user data");
        }

        clearErrorLabels();

        // Initialize the datasource and read data for MajorEndorserList
        datasource = new MajorEndorserListFileDatasource("data", "major-endorser.csv");
        majorEndorserList = datasource.readData();

        confirmButton.setOnAction(actionEvent -> addMajorEndorser());
    }

    private void addMajorEndorser() {
        String name = nameId.getText().trim();
        String position = positionId.getText().trim();
        String faculty = facultyId.getText().trim();
        String major = majorId.getText().trim();  // เพิ่มการดึงข้อมูลจาก TextField ของ major

        if (isInputValid(name, position, faculty, major)) {
            majorEndorserList.addNewMajorEndorser(name, position, faculty, major); // ใช้ค่าที่ได้จากการป้อนข้อมูล
            datasource.writeData(majorEndorserList); // Corrected datasource writing
            showAlert(Alert.AlertType.INFORMATION, "Success", "Major endorser added successfully");
            clearErrorLabels();
            clearTextFields();
        }
    }

    private boolean isInputValid(String name, String position, String faculty, String major) {
        if (name.isEmpty()) {
            setError(errorLabel1, "Please enter name");
            return false;
        }
        if (position.isEmpty()) {
            setError(errorLabel2, "Please enter position");
            return false;
        }
        if (faculty.isEmpty()) {
            setError(errorLabel3, "Please enter faculty");
            return false;
        }
        if (major.isEmpty()) {
            setError(errorLabel4, "Please enter major");
            return false;
        }
        // Checking for duplicate name in the current list of major endorsers
        if (majorEndorserList.getMajorEndorsers().stream().anyMatch(e -> e.getName().equals(name))) {
            setError(errorLabel4, "This major endorser is already in the database");
            return false;
        }
        return true;
    }

    private void setError(Label label, String message) {
        clearErrorLabels(); // Clear all error labels before setting the new one
        label.setText(message);
    }

    private void clearErrorLabels() {
        errorLabel1.setText("");
        errorLabel2.setText("");
        errorLabel3.setText("");
        errorLabel4.setText(""); // Ensure all labels are cleared
    }

    private void clearTextFields() {
        nameId.clear();
        positionId.clear();
        facultyId.clear();
        majorId.clear(); // เคลียร์ข้อมูลใน majorField ด้วย
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateUI() {
        if (user != null) {
            nameLabel.setText(user.getUsername());
        }
    }

    @FXML
    public void onHomeButtonClick() {
        navigateTo("departmentStaff", user);
    }

    @FXML
    public void onUserProfileButton() {
        navigateTo("user-profile", user);
    }

    private void navigateTo(String route, Object data) {
        try {
            FXRouter.goTo(route, data);
        } catch (IOException e) {
            System.err.println("Navigation to " + route + " failed: " + e.getMessage());
        }
    }
}
