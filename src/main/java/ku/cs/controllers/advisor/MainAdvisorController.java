package ku.cs.controllers.advisor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import ku.cs.models.StudentAdvisorList;
import ku.cs.models.StudentAdvisor;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.StudentListFileAdvisorDatasource;

import java.io.IOException;

public class MainAdvisorController {

    private String previousPage;
    @FXML
    private Label errorLabel;
    @FXML
    private TableView<StudentAdvisor> studentAdvisorTableView;
    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButtonClick;

    private StudentAdvisorList studentAdvisorList;

    private Datasource<StudentAdvisorList> datasource;

    @FXML
    public void initialize() {
        errorLabel.setText("");
        datasource = new StudentListFileAdvisorDatasource("data", "studentAdvisor.csv");
        studentAdvisorList = datasource.readData();
        showTable(studentAdvisorList);

        studentAdvisorTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudentAdvisor>() {
            @Override
            public void changed(ObservableValue observableValue, StudentAdvisor oldValue, StudentAdvisor newValue) {
                if (newValue != null){
                }
            }
        });

        searchButtonClick.setOnAction(actionEvent -> searchStudent());
    }

    private void showTable(StudentAdvisorList studentAdvisorList) {
        // กำหนด column ให้มี title ว่า ID และใช้ค่าจาก getter id ของ object StudentAdvisor
        TableColumn<StudentAdvisor, String> idColumn = new TableColumn<>("Student ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // กำหนด column ให้มี title ว่า Name และใช้ค่าจาก getter name ของ object StudentAdvisor
        TableColumn<StudentAdvisor, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // กำหนด column ให้มี title ว่า Faculty และใช้ค่าจาก getter faculty ของ object StudentAdvisor
        TableColumn<StudentAdvisor, String> facultyColumn = new TableColumn<>("Faculty");
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));

        //กำหนด column ให้มี title ว่า Major และใช้ค่าจาก getter major ของ object StudentAdvisor
        TableColumn<StudentAdvisor, String> majorColumn = new TableColumn<>("Major");
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));
        // ล้าง column เดิมทั้งหมดที่มีอยู่ใน table แล้วเพิ่ม column ใหม่
        studentAdvisorTableView.getColumns().clear();
        studentAdvisorTableView.getColumns().add(idColumn);
        studentAdvisorTableView.getColumns().add(nameColumn);
        studentAdvisorTableView.getColumns().add(facultyColumn);
        studentAdvisorTableView.getColumns().add(majorColumn);

        studentAdvisorTableView.getItems().clear();

        for (StudentAdvisor studentAdvisor : studentAdvisorList.getStudentAdvisor()) {
            studentAdvisorTableView.getItems().add(studentAdvisor);
        }

    }

    private void searchStudent() {
        String searchQuery = searchTextField.getText().trim();
        if (searchQuery.isEmpty()) {
            studentAdvisorTableView.getItems().setAll(studentAdvisorList.getStudentAdvisor());
            errorLabel.setText("กรุณาใส่ข้อมูล");
            return;
        }

        StudentAdvisor fondStudent = studentAdvisorList.findStudentById(searchQuery);
        if (fondStudent == null) {
            fondStudent = studentAdvisorList.findStudentByName(searchQuery);
        }

        if (fondStudent != null) {
            studentAdvisorTableView.getItems().setAll(fondStudent);
        } else {
            studentAdvisorTableView.getItems().clear();
        }
    }

    @FXML
    public void onMyTeamButtonClick() throws RuntimeException {
//        Object temp = FXRouter.getData();
//        if (temp instanceof String) {
//            previousPage = (String)temp;
//        }

        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onRequestButtonClick() {
        try {
            FXRouter.goTo("request-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


//    @FXML
//    public void onRequestButton(){
//        try{
//            FXRouter.goTo();
//        }
//    }


}
