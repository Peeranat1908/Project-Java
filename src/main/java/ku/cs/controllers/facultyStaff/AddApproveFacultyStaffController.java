package ku.cs.controllers.facultyStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.models.ApproveFacultyStaff;
import ku.cs.models.ApproveFacultyStaffList;
import ku.cs.services.ApproveFacultyStaffListDatasource;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class AddApproveFacultyStaffController {
    @FXML
    private Button confirmButton;
    @FXML
    private Label errorLabel1;
    @FXML
    private Label errorLabel2;
    @FXML
    private Label errorLabel3;
    @FXML
    private TextField nameId;
    @FXML
    private TextField positionId;

    private Datasource<ApproveFacultyStaffList> datasource;
    private ApproveFacultyStaffList approveFacultyStaffList;

    @FXML
    public void initialize() {
        errorLabel1.setText("");
        errorLabel2.setText("");
        errorLabel3.setText("");
        datasource = new ApproveFacultyStaffListDatasource("data", "approveFacultyStaff.csv");
        approveFacultyStaffList = datasource.readData();
        confirmButton.setOnAction(actionEvent -> {addApproveFacultyStaff();});
    }

    private void addApproveFacultyStaff() {
        String name = nameId.getText().trim();
        String position = positionId.getText().trim();

        if (isInputValid(name, position)) {
            approveFacultyStaffList.addNewApproveFacultyStaff(name, position);
            datasource.writeData(approveFacultyStaffList);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Faculty staff added successfully");
            clearErrorLabels();
        }
    }

    private boolean isInputValid(String name, String position) {
        if (name.isEmpty()) {
            setError(errorLabel1, "Please enter name");
            return false;
        }
        if (position.isEmpty()) {
            setError(errorLabel2, "Please enter position");
            return false;
        }
        for (ApproveFacultyStaff staff : approveFacultyStaffList.getApproveFacultyStaffList()) {
            if (staff.getName().equals(name)) {
                setError(errorLabel1, "Name already exists");
                return false;
            }
            if (staff.getPosition().equals(position)) {
                setError(errorLabel2, "Position already exists");
                return false;
            }
        }
        return true;
    }

    private void setError(Label label, String message) {
        clearErrorLabels();
        label.setText(message);
    }

    private void clearErrorLabels() {
        errorLabel1.setText("");
        errorLabel2.setText("");
        errorLabel3.setText("");
    }

    public void showAlert(Alert.AlertType alertType, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void onBackButtonClick(){
        try{
            FXRouter.goTo("approve-faculty-staff");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
