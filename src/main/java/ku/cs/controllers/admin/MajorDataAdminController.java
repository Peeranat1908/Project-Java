package ku.cs.controllers.admin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.Faculty;
import ku.cs.models.Major;
import ku.cs.models.MajorList;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.MajorListFileDatasource;

import java.io.IOException;

public class MajorDataAdminController {
    @FXML private TableView<Major> majorDataAdminTableView;

    private MajorList majorList;

    private Datasource<MajorList> datasource;

    @FXML
    public void initialize() {
        datasource = new MajorListFileDatasource("data", "Major.csv");
        majorList = datasource.readData();
        if (majorList != null){
            String facultyId = (String) FXRouter.getData(); // รับ facultyId ที่ส่งมา
            filterByFacultyId(facultyId); // กรองข้อมูลด้วย facultyId
        }
        else {
            System.out.println("Failed to load major list.");
        }

        majorDataAdminTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Major>() {
            @Override
            public void changed(ObservableValue observableValue, Major oldValue, Major newValue) {
                if (newValue != null);
            }
        });
    }

    private void filterByFacultyId(String facultyId) {
        MajorList filteredList = new MajorList();
        for (Major major : majorList.getMajors()) {
            if (major.getFacultyId().equals(facultyId)) {
                filteredList.addNewMajor(major.getFacultyId(), major.getMajorId(), major.getMajorName());
            }
        }
        showTable(filteredList);
    }

    private void showTable(MajorList majorList){
        TableColumn<Major, String> facultyIDCol = new TableColumn<>("Faculty ID");
        facultyIDCol.setCellValueFactory(new PropertyValueFactory<>("facultyId"));

        TableColumn<Major, String> majorIdCol = new TableColumn<>("Major ID");
        majorIdCol.setCellValueFactory(new PropertyValueFactory<>("majorId"));

        TableColumn<Major, String> majorNameCol = new TableColumn<>("Major Name");
        majorNameCol.setCellValueFactory(new PropertyValueFactory<>("majorName"));


        majorDataAdminTableView.getColumns().clear();
        majorDataAdminTableView.getColumns().add(facultyIDCol);
        majorDataAdminTableView.getColumns().add(majorIdCol);
        majorDataAdminTableView.getColumns().add(majorNameCol);
        majorDataAdminTableView.getItems().clear();

        for (Major major : majorList.getMajors()){
            majorDataAdminTableView.getItems().add(major);
        }
    }

    @FXML
    public void onMyTeamButtonClick(){
        try{
            FXRouter.goTo("my-team");
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onHomeButtonClick() {
        try {
            FXRouter.goTo("main-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onLogOutButtonClick(){
        try {
            FXRouter.goTo("main-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onBackButtonClick() {
        try{
            FXRouter.goTo("faculty-data-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
