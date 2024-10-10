package ku.cs.controllers.facultyStaff;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.controllers.NavigationHistoryService;
import ku.cs.models.ApproveFacultyStaff;
import ku.cs.models.ApproveFacultyStaffList;
import ku.cs.models.User;
import ku.cs.services.ApproveFacultyStaffListDatasource;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class ApproverFacultyStaffController {
    @FXML
    private TableView<ApproveFacultyStaff> approveFacultyStaffTableView;

    private ApproveFacultyStaffList approveFacultyStaffList;
    private Datasource<ApproveFacultyStaffList> datasource;

    private User user;

    @FXML
    public void initialize() {
        datasource = new ApproveFacultyStaffListDatasource("data", "approveFacultyStaff.csv");
        approveFacultyStaffList = datasource.readData();
        showTable(approveFacultyStaffList);
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;

        }

    }
    private void showTable(ApproveFacultyStaffList approveFacultyStaffList) {
        // กำหนด column ให้มี title ว่า Name และใช้ค่าจาก getter name ของ object StudentAdvisor
        TableColumn<ApproveFacultyStaff, String> nameColumn = new TableColumn<>("ชื่อ");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // กำหนด column ให้มี title ว่า Faculty และใช้ค่าจาก getter faculty ของ object StudentAdvisor
        TableColumn<ApproveFacultyStaff, String> roleColumn = new TableColumn<>("ตำแหน่ง");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<ApproveFacultyStaff, String> facultyColumn = new TableColumn<>("คณะ");
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));

        // ล้าง column เดิมทั้งหมดที่มีอยู่ใน table แล้วเพิ่ม column ใหม่
        approveFacultyStaffTableView.getColumns().clear();
        approveFacultyStaffTableView.getColumns().add(nameColumn);
        approveFacultyStaffTableView.getColumns().add(roleColumn);
        approveFacultyStaffTableView.getColumns().add(facultyColumn);

        approveFacultyStaffTableView.getItems().clear();

        for (ApproveFacultyStaff approveFacultyStaff: approveFacultyStaffList.getApproveFacultyStaffList()) {
            approveFacultyStaffTableView.getItems().add(approveFacultyStaff);
        }
    }

    @FXML
    public void onEditApproveFacultyStaff(){
        try {
            FXRouter.goTo("editApproveFacultyStaff");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onHomeButton(){
        try{
            FXRouter.goTo("facultyStaff");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onMyTeamButtonClick() throws RuntimeException {
        NavigationHistoryService.getInstance().pushPage("approve-faculty-staff");
        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
