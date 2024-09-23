package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.models.Major;
import ku.cs.models.MajorList;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.MajorListFileDatasource;

import java.io.IOException;

public class addNewMajorDataController {
    @FXML
    private Label errorLabel1;
    @FXML
    private Label errorLabel2;
    @FXML
    private Label errorLabel3;
    @FXML
    private Label errorLabel4;
    @FXML
    private TextField facultyId;
    @FXML
    private TextField majorId;
    @FXML
    private TextField majorName;
    @FXML
    private Button okButton;

    private Datasource<MajorList> datasource;
    private MajorList majorList;

    @FXML
    public void initialize() {
        errorLabel1.setText("");
        errorLabel2.setText("");
        errorLabel3.setText("");
        errorLabel4.setText("");
        datasource = new MajorListFileDatasource("data", "Major.csv");
        majorList = datasource.readData();
        okButton.setOnAction(actionEvent -> {addNewMajorData();});

    }

    public void addNewMajorData() {
        String facultyID = facultyId.getText().trim();
        String majorID = majorId.getText().trim();
        String majorNAME = majorName.getText().trim();

        if (facultyID.isEmpty() && majorID.isEmpty() && majorNAME.isEmpty()) {
            showError("Please fill all fields");
            return;
        } else if (facultyID.isEmpty()) {
            errorLabel1.setText("Faculty ID cannot be empty");
            return;
        } else if (majorID.isEmpty()) {
            errorLabel2.setText("Major ID cannot be empty");
            return;
        } else if (majorNAME.isEmpty()) {
            errorLabel3.setText("Major Name cannot be empty");
            return;
        }

        boolean isUpdate = false;
        for (Major major : majorList.getMajors()){
            if (major.getFacultyId().equalsIgnoreCase(facultyID)){
                if (major.getMajorId().equalsIgnoreCase(majorID)){
                    showError("Major ID already exists");
                } else if (major.getMajorName().equalsIgnoreCase(majorNAME)) {
                    showError("Major Name already exists");
                }
                else {
                    majorList.addNewMajor(facultyID, majorID, majorNAME);
                    isUpdate = true;
                    break;
                }
            }
            else {
                showError("Faculty ID does not match");
            }
        }

        if (isUpdate) {
            datasource.writeData(majorList);
            showAlert(Alert.AlertType.INFORMATION, "Major added successfully");
        }

    }
    public void showAlert(Alert.AlertType alertType, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle("Add New Major");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void showError(String message){
        errorLabel4.setText(message);
    }

    @FXML
    public void backButtonClicked() throws IOException {
        try {
            FXRouter.goTo("faculty-data-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
