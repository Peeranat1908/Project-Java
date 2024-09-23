package ku.cs.controllers.admin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.Faculty;
import ku.cs.models.StudentAdvisor;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.FacultyListFileDatasource;
import ku.cs.models.FacultyList;

import java.io.IOException;
import java.util.Optional;

public class FacultyDataAdminController {
    @FXML private TableView<Faculty> facultyDataAdminTableView;

    private FacultyList faculyList;

    private Datasource<FacultyList> datasource;

    @FXML
    public void initialize() {
        datasource = new FacultyListFileDatasource("data", "Faculty.csv");
        faculyList = datasource.readData();
        if (faculyList != null){
            showTable(faculyList);
        }
        else {
            System.out.println("Failed to load faculty list.");
        }

        facultyDataAdminTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Faculty>() {
            @Override
            public void changed(ObservableValue observableValue, Faculty oldValue, Faculty newValue) {
                if (newValue != null) {
                    try{
                        FXRouter.goTo("major-data-admin", newValue.getFacultyId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }


    private void showTable(FacultyList faculyList){
        // กำหนด column ให้มี title ว่า ID และใช้ค่าจาก attribute id ของ object Student
        TableColumn<Faculty, String> idColumn = new TableColumn<>("Faculty ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("facultyId"));//เรียกมาจาก getter

        // กำหนด column ให้มี title ว่า Name และใช้ค่าจาก attribute name ของ object Student
        TableColumn<Faculty, String> nameColumn = new TableColumn<>("Faculty Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("facultyName"));

        // ล้าง column เดิมทั้งหมดที่มีอยู่ใน table แล้วเพิ่ม column ใหม่
        facultyDataAdminTableView.getColumns().clear();
        facultyDataAdminTableView.getColumns().add(idColumn);
        facultyDataAdminTableView.getColumns().add(nameColumn);
        facultyDataAdminTableView.getItems().clear();

        // ใส่ข้อมูล Student ทั้งหมดจาก studentList ไปแสดงใน TableView
        for (Faculty faculty: faculyList.getFaculties()) {
            facultyDataAdminTableView.getItems().add(faculty);
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
    public void onEditFacultyButtonClick(){
        try {
            FXRouter.goTo("edit-data-faculty");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}