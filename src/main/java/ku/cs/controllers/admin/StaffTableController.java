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


public class StaffTableController {

    @FXML
    private TableView<User> tableView;
    @FXML
    private TextField searchUserTextfield;

    private UserList userList;
    private Datasource<UserList> datasource;

    @FXML
    public void initialize() {
        datasource = new UserListFileDatasource("data", "user.csv");
        userList = datasource.readData();
        showTable(userList);

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
                        FXRouter.goTo("staff-edit", selectedUser);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void filterTable(String searchText) {
        tableView.getItems().clear();

        for (User user : userList.getUsers()) {
            boolean matchesRole = user.getRole().equals("facultyStaff") ||
                    user.getRole().equals("departmentStaff") ||
                    user.getRole().equals("advisor");

            if (matchesRole &&
                    (user.getName().toLowerCase().contains(searchText.toLowerCase()) ||
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
        nameColumn.setPrefWidth(155);

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setPrefWidth(155);

        // เปลี่ยน LocalDate และ LocalTime เป็น Faculty และ Department
        TableColumn<User, String> facultyColumn = new TableColumn<>("Faculty");
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        facultyColumn.setPrefWidth(155);

        TableColumn<User, String> departmentColumn = new TableColumn<>("Major");
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("major"));
        departmentColumn.setPrefWidth(155);

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleColumn.setPrefWidth(125);

        TableColumn<User, Boolean> suspendColumn = new TableColumn<>("Suspend");
        suspendColumn.setCellValueFactory(new PropertyValueFactory<>("suspended"));
        suspendColumn.setPrefWidth(125);
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
        tableView.getColumns().addAll(nameColumn, usernameColumn, facultyColumn, departmentColumn, roleColumn, suspendColumn);

        // แสดงผู้ใช้ใน TableView
        for (User user : userList.getUsers()) {
            if (user.getRole().equals("facultyStaff") || user.getRole().equals("departmentStaff") || user.getRole().equals("advisor")) {
                tableView.getItems().add(user);
            }
        }
    }


    @FXML
    public void addStaffButtonClick() {
        try {
            FXRouter.goTo("add-staff");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    @FXML
    public void dashboardButtonClick() {
        try {
            FXRouter.goTo("dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void manageStaffdataButtonClick() {
        try {
            FXRouter.goTo("staff-table-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void homeButtonClick() {
        try {
            FXRouter.goTo("main-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onManageFacultyButtonClick() {
        try {
            FXRouter.goTo("faculty-data-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




