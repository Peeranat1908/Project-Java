package ku.cs.controllers.facultyStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ku.cs.controllers.MyteamController;
import ku.cs.controllers.components.AppealItemController;
import ku.cs.controllers.components.Sidebar;
import ku.cs.controllers.components.SidebarController;
import ku.cs.models.*;
import ku.cs.services.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class FacultyStaffController implements Sidebar {
    @FXML private Label errorLabel;
    @FXML private TextField searchTextField;
    private User user;
    @FXML private VBox appealVBox;
    @FXML private Label noAppealsLabel;
    @FXML private Label nameLabel;
    private AppealList appealList;
    private AppealListDatasource datasource;
    private AppealList appealListInFaculty;
    @FXML Button myTeamButton;
    @FXML
    private AnchorPane sidebar;
    @FXML
    private AnchorPane mainPage;    @FXML
    private Button toggleSidebarButton; // ปุ่มสำหรับแสดง/ซ่อน Sidebar


    @FXML
    public void initialize() {
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
        StudentList studentsListInFaculty = studentList.getStudentsListBYFaculty(user.getFaculty());
        appealListInFaculty = appealList.findAppealByStudentID(studentsListInFaculty);
        loadAppeals(null, null);
        loadSidebar();
        toggleSidebarButton.setOnAction(actionEvent -> {toggleSidebar();});
    }

    private void loadAppeals(String filterType, String searchQuery) {
        // Fetch the list of appeals in the major
        List<Appeal> appeals = appealListInFaculty.getsAppeals();

        // If there are no appeals, show "no appeals" message
        if (appeals == null || appeals.isEmpty()) {
            noAppealsLabel.setVisible(true);
            appealVBox.getChildren().clear(); // Clear the list
            return; // Exit early if there's nothing to process
        }
        appeals = appeals.stream()
                .filter(appeal -> appeal.getStatus().equals("อนุมัติโดยหัวหน้าภาควิชา คำร้องส่งต่อให้คณบดี") ||
                        appeal.getStatus().contains("คณบดี"))
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
    public void onUserProfileButton() {
        navigateTo("user-profile", user);
    }

    @FXML
    public void onApproveStaff(){
        navigateTo("approve-faculty-staff", user);
    }

    @FXML
    public void onMyTeamButtonClick() {
        try {
            // เก็บ Scene ปัจจุบันเพื่อใช้ย้อนกลับ
            Stage stage = (Stage) myTeamButton.getScene().getWindow();
            Scene currentScene = stage.getScene();

            // โหลดหน้าต่าง My Team
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ku/cs/views/other/my-team.fxml"));
            Parent root = loader.load();

            // ตั้งค่า Scene ก่อนหน้าให้ MyteamController เพื่อใช้ในการย้อนกลับ
            MyteamController controller = loader.getController();  // แก้เป็น MyteamController
            controller.setPreviousScene(currentScene);  // ต้องมีการตั้ง method setPreviousScene ใน MyteamController

            // เปลี่ยน Scene ไปหน้า My Team
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void navigateTo(String route, Object data) {
        try {
            FXRouter.goTo(route, data);
        } catch (IOException e) {
            System.err.println("Navigation to " + route + " failed: " + e.getMessage());
        }
    }


    @Override
    public void loadSidebar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ku/cs/views/other/sidebar.fxml"));
            AnchorPane loadedSidebar = loader.load();

            // ดึง SidebarController จาก FXML Loader
            SidebarController sidebarController = loader.getController();
            sidebarController.setSidebar(this); // กำหนด MainAdminController เป็น Sidebar เพื่อให้สามารถปิดได้

            sidebar = loadedSidebar; // กำหนด sidebar ที่โหลดเสร็จแล้ว
            sidebar.setVisible(false); // ปิด sidebar ไว้ในค่าเริ่มต้น
            mainPage.getChildren().add(sidebar); // เพิ่ม sidebar ไปยัง mainPage
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void toggleSidebar() {
        if (sidebar != null){
            sidebar.setVisible(!sidebar.isVisible());
            if (sidebar.isVisible()){
                sidebar.toFront(); //ให้ sidebar แสดงด้านหน้าสุด
            }
            else {
                sidebar.toBack();
            }
        }
    }

    @Override
    public void closeSidebar() {
        if (sidebar != null){
            sidebar.setVisible(false);
            sidebar.toBack();
        }
    }
}