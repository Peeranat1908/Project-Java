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

public class EditApproveFacultyStaffController {
    @FXML private TextField nameId;
    @FXML private TextField positionId;

    @FXML private Label errorLabel1;
    @FXML private Label errorLabel2;
    @FXML private Label errorLabel3;
    @FXML private Button confirmButton;
    private ApproveFacultyStaffList approveFacultyStaffList;
    private Datasource<ApproveFacultyStaffList> datasource;

    @FXML
    public void initialize() {
        errorLabel1.setText("");
        errorLabel2.setText("");
        errorLabel3.setText("");
        datasource = new ApproveFacultyStaffListDatasource("data", "approveFacultyStaff.csv");
        confirmButton.setOnAction(actionEvent -> {handleConfirmButton();});

    }

    private void handleConfirmButton() {
        String name = nameId.getText().trim();
        String position = positionId.getText().trim();

        if (!isInputValid(name, position)) return;

        if (updateFacultyStaff(name, position)) {
            datasource.writeData(approveFacultyStaffList);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Data has been updated");
            clearErrorLabels();
        }
    }

    private boolean isInputValid(String name, String position) {
        if (name.isEmpty()) {
            setError(errorLabel1, "Please enter faculty staff name");
            return false;
        }
        if (position.isEmpty()) {
            setError(errorLabel2, "Please enter faculty staff position");
            return false;
        }
        return true;
    }

    private boolean updateFacultyStaff(String name, String position) {
        for (ApproveFacultyStaff staff : approveFacultyStaffList.getApproveFacultyStaffList()) {
            if (staff.getName().equals(name)) {
                if (staff.getPosition().equals(position)) {
                    setError(errorLabel3, "Data is already in the database");
                    return false;
                }
                staff.setPosition(position);
                return true;
            }
        }
        setError(errorLabel3, "No matching faculty staff found");
        return false;
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

    private void showAlert(Alert.AlertType alertType, String title, String message){
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

    @FXML
    public void onAddFacultyStaffButtonClick(){
        try{
            FXRouter.goTo("addApproveFacultyStaff");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
