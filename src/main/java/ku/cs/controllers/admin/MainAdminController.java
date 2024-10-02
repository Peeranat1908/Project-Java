package ku.cs.controllers.admin;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

            if (!user.getRole().equals("admin") &&
                    (matchesRole || (!studentCheckBox.isSelected() && !facultyStaffCheckBox.isSelected() &&
                            !departmentStaffCheckBox.isSelected() && !advisorCheckBox.isSelected())) &&
                    (user.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                            user.getUsername().toLowerCase().contains(searchText.toLowerCase()))) {
                tableView.getItems().add(user);
            }
        }
    }

    private void showTable(UserList userList) {
        tableView.getItems().clear();

        userList.sortUsersByLastLogin();

        // สร้างคอลัมน์
        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(155);

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setPrefWidth(155);

        TableColumn<User, LocalDate> dateColumn = new TableColumn<>("Last Login Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("lastLoginDate"));
        dateColumn.setPrefWidth(155);

        TableColumn<User, LocalTime> timeColumn = new TableColumn<>("Last Login Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("lastLoginTime"));
        timeColumn.setPrefWidth(155);

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
        TableColumn<User, ImageView> profilePictureColumn = new TableColumn<>("Profile Picture");
        profilePictureColumn.setCellValueFactory(cellData -> {
            String profilePath = cellData.getValue().getProfilePicturePath();
            if (profilePath == null || profilePath.isEmpty()) {
                profilePath = "/images/profileDeafault2.png";
            }
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(profilePath)));
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            return new SimpleObjectProperty<>(imageView);
        });
        profilePictureColumn.setPrefWidth(100);

        tableView.getColumns().clear();
        tableView.getColumns().addAll(profilePictureColumn,nameColumn, usernameColumn, dateColumn, timeColumn, roleColumn, suspendColumn);

        for (User user : userList.getUsers()) {
            if (!user.getRole().equals("admin")) {
                tableView.getItems().add(user);
            }
        }
    }

    @FXML
    private void RoleSelectedButtonClick() {
        roleSelectionPane.setVisible(!roleSelectionPane.isVisible());
    }

    @FXML
    private void enterselectedRoleButtonClick() {
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

    @FXML
    public void dashboardButtonClick() {
        try {
            FXRouter.goTo("dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void manageStaffDataButtonClick() {
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