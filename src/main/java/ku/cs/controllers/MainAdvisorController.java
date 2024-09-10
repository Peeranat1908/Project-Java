package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.StudentAdvisorList;
import ku.cs.models.StudentAdvisor;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.StudentListFileAdvisorDatasource;

import java.io.IOException;

public class MainAdvisorController {

    private String previousPage;
    @FXML
    private TableView<StudentAdvisor> studentAdvisorTableView;
    @FXML
    private TextField searchTextField;

    private StudentAdvisorList studentAdvisorList;

    private Datasource<StudentAdvisorList> datasource;

    @FXML
    public void initialize() {
        datasource = new StudentListFileAdvisorDatasource("data", "studentAdvisor.csv");
        studentAdvisorList = datasource.readData();
        showTable(studentAdvisorList);
    }
        private void showTable(StudentAdvisorList studentAdvisorList) {
            // กำหนด column ให้มี title ว่า ID และใช้ค่าจาก getter id ของ object Student
            TableColumn<StudentAdvisor, String> idColumn = new TableColumn<>("Student ID");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

            // กำหนด column ให้มี title ว่า Name และใช้ค่าจาก getter name ของ object Student
            TableColumn<StudentAdvisor, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

            // กำหนด column ให้มี title ว่า Score และใช้ค่าจาก getter score ของ object Student
            TableColumn<StudentAdvisor, String> facultyColumn = new TableColumn<>("Faculty");
            facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));

            TableColumn<StudentAdvisor, String> majorColumn = new TableColumn<>("Major");
            majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));
            // ล้าง column เดิมทั้งหมดที่มีอยู่ใน table แล้วเพิ่ม column ใหม่
            studentAdvisorTableView.getColumns().clear();
            studentAdvisorTableView.getColumns().add(idColumn);
            studentAdvisorTableView.getColumns().add(nameColumn);
            studentAdvisorTableView.getColumns().add(facultyColumn);
            studentAdvisorTableView.getColumns().add(majorColumn);

            studentAdvisorTableView.getItems().clear();

            for (StudentAdvisor studentAdvisor : studentAdvisorList.getStudentAdvisor()){
                studentAdvisorTableView.getItems().add(studentAdvisor);
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
    public void onRequestButtonClick(){
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
