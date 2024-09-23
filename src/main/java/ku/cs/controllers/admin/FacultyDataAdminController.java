package ku.cs.controllers.admin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.Faculty;
import ku.cs.models.FacultyList;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.FacultyListFileDatasource;

import java.io.IOException;

public class FacultyDataAdminController {
    @FXML private TableView<Faculty> facultyDataAdminTableView;

    private FacultyList facultyList;

    private Datasource<FacultyList> datasource;

    @FXML
    public void initialize(){
        datasource = new FacultyListFileDatasource("data", "Faculty.csv");
        facultyList = datasource.readData();
        showTable(facultyList);

        facultyDataAdminTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Faculty>() {
            @Override
            public void changed(ObservableValue observableValue, Faculty oldValue, Faculty newValue) {
                if (newValue != null){
                }
            }
        });
    }

    private void showTable(FacultyList facultyList){
        // กำหนด column ให้มี title ว่า ID และใช้ค่าจาก attribute id ของ object Student
        TableColumn<Faculty, String> idColumn = new TableColumn<>("Faculty ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));//เรียกมาจาก getter

        // กำหนด column ให้มี title ว่า Name และใช้ค่าจาก attribute name ของ object Student
        TableColumn<Faculty, String> nameColumn = new TableColumn<>("Faculty Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // ล้าง column เดิมทั้งหมดที่มีอยู่ใน table แล้วเพิ่ม column ใหม่
        facultyDataAdminTableView.getColumns().clear();
        facultyDataAdminTableView.getColumns().add(idColumn);
        facultyDataAdminTableView.getColumns().add(nameColumn);
        facultyDataAdminTableView.getItems().clear();

        // ใส่ข้อมูล Student ทั้งหมดจาก studentList ไปแสดงใน TableView
        for (Faculty faculty: facultyList.getFaculties()) {
            facultyDataAdminTableView.getItems().add(faculty);
        }
    }


    public void onHomeButtonClick() {
        try {
            FXRouter.goTo("main-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
