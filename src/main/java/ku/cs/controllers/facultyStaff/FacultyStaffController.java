package ku.cs.controllers.facultyStaff;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.Student;
import ku.cs.models.StudentList;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.MajorStaffListFileDataSource;

import java.io.IOException;

public class FacultyStaffController {
    @FXML
    private Label errorLabel;
    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButtonClick;

    private StudentList studentList;

    private Datasource<StudentList> datasource;

    @FXML
    public void initialize() {
//        errorLabel.setText("");
        datasource = new MajorStaffListFileDataSource("data", "student-in-faculty.csv");
        studentList = datasource.readData();
        showTable(studentList);

        studentTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue observableValue, Student oldValue, Student newValue) {
                if (newValue != null){
                }
            }
        });

        //searchButtonClick.setOnAction(actionEvent -> searchStudent());
    }

    private void showTable(StudentList studentList) {
        // กำหนด column ให้มี title ว่า ID และใช้ค่าจาก getter id ของ object StudentAdvisor
        TableColumn<Student, String> idColumn = new TableColumn<>("Student ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // กำหนด column ให้มี title ว่า Name และใช้ค่าจาก getter name ของ object StudentAdvisor
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // กำหนด column ให้มี title ว่า Faculty และใช้ค่าจาก getter faculty ของ object StudentAdvisor
        TableColumn<Student, String> emailColumn = new TableColumn<>("email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // ล้าง column เดิมทั้งหมดที่มีอยู่ใน table แล้วเพิ่ม column ใหม่
        studentTableView.getColumns().clear();
        studentTableView.getColumns().add(idColumn);
        studentTableView.getColumns().add(nameColumn);
        studentTableView.getColumns().add(emailColumn);

        studentTableView.getItems().clear();

        for (Student student: studentList.getStudents()) {
            studentTableView.getItems().add(student);
        }
    }

//    private void searchStudent() {
//        String searchQuery = searchTextField.getText().trim();
//        if (searchQuery.isEmpty()) {
//            studentTableView.getItems().setAll(studentAdvisorList.getStudentAdvisor());
////            errorLabel.setText("กรุณาใส่ข้อมูล");
//            return;
//        }

//        StudentAdvisor fondStudent = studentAdvisorList.findStudentById(searchQuery);
//        if (fondStudent == null) {
//            fondStudent = studentAdvisorList.findStudentByName(searchQuery);
//        }
//
//        if (fondStudent != null) {
//            studentTableView.getItems().setAll(fondStudent);
//        } else {
//            studentTableView.getItems().clear();
//        }
//    }

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
    public void onApproveFacultyStaffButtonClick() {
        try{
            FXRouter.goTo("approve-faculty-staff");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
