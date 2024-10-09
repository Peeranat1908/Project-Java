package ku.cs.controllers.facultyStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ku.cs.controllers.components.AppealItemController;
import ku.cs.models.Appeal;
import ku.cs.models.AppealList;
import ku.cs.models.User;
import ku.cs.services.AppealListDatasource;
import ku.cs.services.AppealSharedData;
import ku.cs.services.AppealSortComparator;
import ku.cs.services.FXRouter;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class FacultyAppealController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox appealVBox;

    @FXML
    private Label noAppealsLabel;

    @FXML
    private TextField searchTextField;

    private AppealListDatasource datasource;
    private AppealList appealList;
    private User user;

    @FXML
    public void initialize() {
        datasource = new AppealListDatasource("data/appeals.csv");
        appealList = datasource.readData();
        AppealSharedData.setNormalAppealList(appealList);

        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
        }
        loadAppeals(null, null);
    }
    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("facultyStaff", user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadAppeals(String filterType, String searchQuery) {
        // Fetch the list of appeals in the major
        List<Appeal> appeals = appealList.getsAppeals();

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

}
