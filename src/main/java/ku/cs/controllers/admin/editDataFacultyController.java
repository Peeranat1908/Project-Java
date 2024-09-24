package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.models.Faculty;
import ku.cs.models.FacultyList;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.FacultyListFileDatasource;

import java.io.*;

public class editDataFacultyController {
    @FXML private TextField facultyId;
    @FXML private TextField facultyName;
    @FXML private Button okButton;
    private Datasource<FacultyList> datasource;
    private FacultyList facultyList;
    private FacultyList faculyList;
    @FXML private Label errorLabel1;
    @FXML private Label errorLabel2;
    @FXML private Label errorLabel3;
    @FXML
    public void initialize(){
        errorLabel1.setText("");
        errorLabel2.setText("");
        errorLabel3.setText("");
        datasource = new FacultyListFileDatasource("data", "Faculty.csv");
        facultyList = datasource.readData();
        okButton.setOnAction(event -> {
            try {
                okButtonClicked();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void okButtonClicked() throws IOException {
        String Id = facultyId.getText().trim();
        String Name = facultyName.getText().trim();
        if (Id.isEmpty() && Name.isEmpty()) {
            showError("Please enter a data");
            return;
        } else if (Id.isEmpty()) {
            errorLabel1.setText("Please enter a valid faculty id.");
            return;
        }
        else if (Name.isEmpty()) {
            errorLabel2.setText("Please enter a valid faculty name.");
            return;
        }

        boolean isUpdated = false;
        for (Faculty faculty : facultyList.getFaculties()){
            if (faculty.getFacultyId().equals(Id)){
                faculty.setFacultyName(Name);
                isUpdated = true;
                break;
            } else if (faculty.getFacultyName().equals(Name)) {
                faculty.setFacultyId(Id);
                isUpdated = true;
                break;
            }
        }
        if (isUpdated){
            datasource.writeData(facultyList);
            showAlert(Alert.AlertType.INFORMATION, "Success!", "Faculty updated successfully!");
        }
        else {
            showError("Faculty not found!, Please enter again.");
        }

    }

    private void showAlert(Alert.AlertType alertType, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }
    private void showError(String message){
        errorLabel3.setText(message);

    }

    @FXML
    public void onBackButtonClick(){
        try{
            FXRouter.goTo("faculty-data-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onAddNewFacultyButtonClicked(){
        try {
            FXRouter.goTo("add-new-faculty");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
