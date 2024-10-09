package ku.cs.controllers.majorStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import ku.cs.controllers.components.AppealItemController;
import ku.cs.models.*;
import ku.cs.services.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;


public class MainMajorStaffController{
    @FXML
    private VBox appealVBox;

    @FXML
    private Label noAppealsLabel;

    @FXML
    private TextField searchTextField;
    @FXML private Label nameLabel;
    private User user;
    private AppealList appealList;
    private AppealListDatasource datasource;
    private AppealList appealListInMajor;

    @FXML
    private void initialize() {
        Object data = FXRouter.getData();
        datasource = new AppealListDatasource("data/appeals.csv");
        appealList = datasource.readData();
        AppealSharedData.setNormalAppealList(appealList);
        if (data instanceof User) {
            user = (User) data;
            updateUI();
        } else {
            nameLabel.setText("Invalid user data");
        }
        Datasource<StudentList> studentDatasource = new StudentListFileDatasource("data", "student-info.csv");
        StudentList studentList = studentDatasource.readData();
        StudentList studentsListInMajor = studentList.getStudentsListBYMajor(user.getMajor());
        appealListInMajor = appealList.findAppealByStudentID(studentsListInMajor);
        loadAppeals(null, null);

    }

    private void loadAppeals(String filterType, String searchQuery) {
        // Fetch the list of appeals in the major
        List<Appeal> appeals = appealListInMajor.getsAppeals();

        // If there are no appeals, show "no appeals" message
        if (appeals == null || appeals.isEmpty()) {
            noAppealsLabel.setVisible(true);
            appealVBox.getChildren().clear(); // Clear the list
            return; // Exit early if there's nothing to process
        }
        appeals = appeals.stream()
                .filter(appeal -> appeal.getStatus().equals("อนุมัติโดยอาจารย์ที่ปรึกษา คำร้องส่งต่อให้หัวหน้าภาควิชา") ||
                        appeal.getStatus().contains("หัวหน้าภาควิชา") ||
                        appeal.getStatus().equals("ใบคำร้องใหม่ คำร้องส่งต่อให้อาจารย์ที่ปรึกษา"))
                .collect(Collectors.toList());


        // Filter appeals by type, if a filterType is provided
        if (filterType != null && !filterType.isEmpty()) {
            appeals = appeals.stream()
                    .filter(appeal -> appeal.getType().equals(filterType))
                    .collect(Collectors.toList());
        }

        // Filter appeals by search query, if provided
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            String lowerCaseQuery = searchQuery.trim().toLowerCase();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            appeals = appeals.stream()
                    .filter(appeal ->
                            appeal.getSubject().toLowerCase().contains(lowerCaseQuery) || // Search by subject
                                    appeal.getRequest().toLowerCase().contains(lowerCaseQuery) || // Search by request content
                                    appeal.getDate().format(dateFormatter).contains(lowerCaseQuery) || // Search by date
                                    appeal.getStudentSignature().toLowerCase().contains(lowerCaseQuery) // Search by student signature
                    ).collect(Collectors.toList());
        }

        // Sort appeals using the comparator
        appeals.sort(new AppealSortComparator(filterType));

        // Update the UI based on the filtered appeals
        appealVBox.getChildren().clear(); // Clear previous list
        if (appeals.isEmpty()) {
            noAppealsLabel.setVisible(true); // Show "no appeals" message if list is empty
        } else {
            noAppealsLabel.setVisible(false); // Hide "no appeals" message if appeals are present
            // Load each filtered appeal into the VBox
            appeals.forEach(appeal -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ku/cs/views/student/appeal-item.fxml"));
                    Pane pane = loader.load();

                    AppealItemController controller = loader.getController();
                    controller.setAppealData(appeal); // Set appeal data for the view

                    appealVBox.getChildren().add(pane); // Add pane to VBox
                } catch (IOException e) {
                    e.printStackTrace(); // Log error in case of failure
                }
            });
        }
    }

    @FXML   //สำหรับ search หาคำร้อง
    public void onSearchButtonClick() {
        String searchQuery = searchTextField.getText();
        loadAppeals(null, searchQuery);
    }

    @FXML   //กรองเเค่คำร้องทั่วไป
    public void showNormalAppealsOnly() {
        loadAppeals("คำร้องทั่วไป:", null);
    }

    @FXML   //กรองเเค่ใบลาพักการศึกษา
    public void showLeaveAppealsOnly() {
        loadAppeals("ใบลาพักการศึกษา:", null);
    }

    @FXML   //กรองเเค่คำร้องขอลงทะเบียนเรียน
    public void showEnrollAppealsOnly() {
        loadAppeals("ขอลงทะเบียนเรียน:", null);
    }
    @FXML   //เห็นคำร้องทุกประเภท
    public void showAllAppeals() {
        loadAppeals(null, null);
    }





    private void updateUI() {
        if (user != null) {
            nameLabel.setText(user.getUsername());

        }
    }

    @FXML
    public void onAddEndorserButton(){
        navigateTo("add-major-endorser", user);
    }
    @FXML
    public void onUserProfileButton(){
        navigateTo("user-profile", user);
    }

    @FXML
    public void onStudentListButton(){
        navigateTo("student-in-major", user);
    }

    private void navigateTo(String route, Object data) {
        try {
            FXRouter.goTo(route, data);
        } catch (IOException e) {
            System.err.println("Navigation to " + route + " failed: " + e.getMessage());
        }
    }


}
