package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ku.cs.models.Faculty;
import ku.cs.models.FaculyList;
import ku.cs.models.Major;
import ku.cs.models.MajorList;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.FacultyListFileDatasource;
import ku.cs.services.MajorListFileDatasource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class editDataFaculty {
    @FXML private TextField facultyId;
    @FXML private TextField facultyName;
    @FXML private Button okButton;
    private Datasource<FaculyList> datasource;
    private FaculyList faculyList;
    private String csvFilePath = "data/Faculty.csv";
    @FXML
    public void initialize(){
        datasource = new FacultyListFileDatasource("data", "Faculty.csv");
        faculyList = datasource.readData();
        String facultyId = (String) FXRouter.getData();
        okButton.setOnAction(event -> {
            try {
                okButtonClicked();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    public void onBackButtonClick(){
        try{
            FXRouter.goTo("faculty-data-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void okButtonClicked() throws IOException {
        String Id = facultyId.getText();
        String Name = facultyName.getText();

        List<String[]> facultyList = new ArrayList<>();
        boolean isUpdated = false;

        try(BufferedReader br = new BufferedReader(new FileReader(csvFilePath))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(data[1].equals(Id)){ // เปรียบเทียบ faculty id ถ้าเจอให้แก้ไข
                    data[2] = Name;
                    isUpdated = true;
                }
                facultyList.add(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(isUpdated){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))){
                for (String[] faculty : facultyList){
                    bw.write(String.join(",", faculty));
                    bw.newLine();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
            showAlert(Alert.AlertType.INFORMATION, "Successfully Updated", "Faculty Updated.");

        }
        else{
            showAlert(Alert.AlertType.ERROR, "Error", "Faculty Not Updated.");
        }


    }

    private void showAlert(Alert.AlertType alertType, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

}
