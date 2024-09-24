package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.models.Faculty;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.FacultyListFileDatasource;
import ku.cs.models.FacultyList;

import java.io.IOException;

public class addNewFacultyDataController {
    @FXML private Label errorLabel1;
    @FXML private Label errorLabel2;
    @FXML private Label errorLabel3;
    @FXML
    private TextField facultyId;

    @FXML
    private TextField facultyName;
    @FXML private Button addButton;

    private Datasource<FacultyList> datasource;
    private FacultyList facultyList;


    public void initialize(){
        errorLabel1.setText("");
        errorLabel2.setText("");
        errorLabel3.setText("");
        datasource = new FacultyListFileDatasource("data", "Faculty.csv");
        facultyList = datasource.readData();
        addButton.setOnAction(actionEvent -> {addNewFacultyButtonClicked();});
    }

    public void addNewFacultyButtonClicked(){
        String id = facultyId.getText().trim();
        String name = facultyName.getText().trim();

        if (id.isEmpty() && name.isEmpty()){
            errorLabel1.setText("Please enter a valid faculty id");
            errorLabel2.setText("Please enter a valid faculty name");
            return;
        }
        else if(id.isEmpty()){errorLabel1.setText("Please enter a valid faculty id"); return;}
        else if(name.isEmpty()){errorLabel2.setText("Please enter a valid faculty name"); return;}

        boolean isUpdate = false;
        for (Faculty faculty : facultyList.getFaculties()){
            if (faculty.getFacultyId().equalsIgnoreCase(id) && faculty.getFacultyName().equalsIgnoreCase(name)){
                showError("Faculty already exists");
            }
            if (faculty.getFacultyId().equalsIgnoreCase(id)){
                showError("Faculty already had faculty id");
            } else if (faculty.getFacultyName().equalsIgnoreCase(name)) {
                showError("Faculty already had faculty name");
            }

            facultyList.addNewFaculty(id, name);
            isUpdate = true;
        }
        if (isUpdate){
            datasource.writeData(facultyList);
            showAlert(Alert.AlertType.INFORMATION, "Success!", "Faculty has added successfully!");
        }


    }

    public void showAlert(Alert.AlertType alertType, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showError(String message){
        errorLabel3.setText(message);
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