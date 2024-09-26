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
    @FXML private TextField nameId, positionId, facultyId;

    @FXML private Label errorLabel1;
    @FXML private Label errorLabel2;
    @FXML private Label errorLabel3;
    @FXML private Label errorLabel4;
    @FXML private Button confirmButton;
    private ApproveFacultyStaffList approveFacultyStaffList;
    private Datasource<ApproveFacultyStaffList> datasource;

    @FXML
    public void initialize() {
        errorLabel1.setText("");
        errorLabel2.setText("");
        errorLabel3.setText("");
        errorLabel4.setText("");
        datasource = new ApproveFacultyStaffListDatasource("data", "approveFacultyStaff.csv");
        approveFacultyStaffList = datasource.readData();
        confirmButton.setOnAction(actionEvent -> {handleConfirmButton();});
    }

    private void handleConfirmButton() {
        String name = nameId.getText().trim();
        String position = positionId.getText().trim();
        String faculty = facultyId.getText().trim();

        if (!isInputValid(name, position, faculty)) return;

        if (updateFacultyStaff(name, position, faculty)) {
            datasource.writeData(approveFacultyStaffList);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Data has been updated");
            clearErrorLabels();
            clearTextFiled();
        }
    }

    private boolean isInputValid(String name, String position, String faculty) {
        if (name.isEmpty()) {
            setError(errorLabel1, "Please enter faculty staff name");
            return false;
        }
        if (position.isEmpty()) {
            setError(errorLabel2, "Please enter faculty staff position");
            return false;
        }
        if (faculty.isEmpty()){
            setError(errorLabel3, "Please enter faculty staff faculty");
            return false;
        }
        return true;
    }

    private boolean updateFacultyStaff(String name, String position, String faculty) {
        for (ApproveFacultyStaff staff : approveFacultyStaffList.getApproveFacultyStaffList()) {
            if (staff.getName().equals(name)) {
                if (staff.getPosition().equals(position) && staff.getFaculty().equals(faculty)) {
                    setError(errorLabel4, "Data is already in the database");
                    return false;
                }
                staff.setPosition(position);
                staff.setFaculty(faculty);
                return true;
            }
        }
        setError(errorLabel4, "No matching faculty staff found");
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
        errorLabel4.setText("");
    }
    private void clearTextFiled(){
        nameId.clear();
        positionId.clear();
        facultyId.clear();
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
