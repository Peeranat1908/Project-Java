package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.models.User;
import ku.cs.models.UserList;
import ku.cs.services.Datasource;
import ku.cs.services.UserListFileDatasource;
import ku.cs.services.FXRouter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;

public class MainAdminController {
    @FXML
    private TableView<User> tableView;
    @FXML
    private TextField searchUserTextfield;
    @FXML
    private Pane roleSelectionPane;
    @FXML
    private CheckBox studentCheckBox;
    @FXML
    private CheckBox facultyStaffCheckBox;
    @FXML
    private CheckBox departmentStaffCheckBox;
    @FXML
    private CheckBox advisorCheckBox;

    private UserList userList;
    private Datasource<UserList> datasource;

    @FXML
    public void initialize() {
        datasource = new UserListFileDatasource("data", "user.csv");
        userList = datasource.readData();
        showTable(userList);

        // ฟังการเปลี่ยนแปลงใน TextField สำหรับการค้นหา
        searchUserTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue);
        });
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                User selectedUser = tableView.getSelectionModel().getSelectedItem();
            } else if (event.getClickCount() == 2) {
                User selectedUser = tableView.getSelectionModel().getSelectedItem();
                if (selectedUser != null) {
                    try {
                        FXRouter.goTo("user-details", selectedUser);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        roleSelectionPane.setVisible(false);
    }

    private void filterTable(String searchText) {
        tableView.getItems().clear();

        for (User user : userList.getUsers()) {
            boolean matchesRole = false;

            // ตรวจสอบว่าผู้ใช้ตรงกับบทบาทที่เลือก
            if (studentCheckBox.isSelected() && user.getRole().equals("student")) {
                matchesRole = true;
            }
            if (facultyStaffCheckBox.isSelected() && user.getRole().equals("facultyStaff")) {
                matchesRole = true;
            }
            if (departmentStaffCheckBox.isSelected() && user.getRole().equals("departmentStaff")) {
                matchesRole = true;
            }
            if (advisorCheckBox.isSelected() && user.getRole().equals("advisor")) {
                matchesRole = true;
            }

            // ตรวจสอบว่าผู้ใช้ตรงกับคำค้นหาหรือไม่
            if ((matchesRole || (!studentCheckBox.isSelected() && !facultyStaffCheckBox.isSelected() &&
                    !departmentStaffCheckBox.isSelected() && !advisorCheckBox.isSelected())) &&
                    (user.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                            user.getSurname().toLowerCase().contains(searchText.toLowerCase()) ||
                            user.getUsername().toLowerCase().contains(searchText.toLowerCase()))) {
                tableView.getItems().add(user);
            }
        }
    }

    private void showTable(UserList userList) {
        tableView.getItems().clear(); // เคลียร์ตารางก่อน

        userList.sortUsersByLastLogin();

        // สร้างคอลัมน์
        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(140);

        TableColumn<User, String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameColumn.setPrefWidth(140);

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setPrefWidth(140);

        TableColumn<User, LocalDate> dateColumn = new TableColumn<>("Last Login Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("lastLoginDate"));
        dateColumn.setPrefWidth(140);

        TableColumn<User, LocalTime> timeColumn = new TableColumn<>("Last Login Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("lastLoginTime"));
        timeColumn.setPrefWidth(140);

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleColumn.setPrefWidth(110);

        TableColumn<User, Boolean> suspendColumn = new TableColumn<>("Suspend");
        suspendColumn.setCellValueFactory(new PropertyValueFactory<>("suspended"));
        suspendColumn.setPrefWidth(110);
        suspendColumn.setCellFactory(column -> new TableCell<User, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item ? "Suspended" : "Active");
                }
            }
        });

        tableView.getColumns().clear();
        tableView.getColumns().addAll(nameColumn, surnameColumn, usernameColumn, dateColumn, timeColumn, roleColumn, suspendColumn);

        // แสดงผู้ใช้ใน TableView
        for (User user : userList.getUsers()) {
            tableView.getItems().add(user);
        }
    }

    @FXML
    private void roleselectedclick() {
        roleSelectionPane.setVisible(!roleSelectionPane.isVisible());
    }

    @FXML
    private void enterselectedRoleClick() {
        filterTable(searchUserTextfield.getText());
    }

    @FXML
    public void onMyTeamButtonClick() {
        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onLogoutButtonClick() {
        try {
            FXRouter.goTo("login-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
