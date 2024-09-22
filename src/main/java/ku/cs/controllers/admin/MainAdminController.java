package ku.cs.controllers.admin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class MainAdminController {

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

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    FXRouter.goTo("user-details", newValue);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        searchUserTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue);
        });
    }

    private void filterTable(String searchText) {
        tableView.getItems().clear();

        for (User user : userList.getUsers()) {
            if (user.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                    user.getSurname().toLowerCase().contains(searchText.toLowerCase()) ||
                    user.getUsername().toLowerCase().contains(searchText.toLowerCase())) {
                tableView.getItems().add(user);
            }
        }
    }

    private void showTable(UserList userList) {
        // Create columns
        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, LocalDate> dateColumn = new TableColumn<>("Last Login Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("lastLoginDate"));

        TableColumn<User, LocalTime> timeColumn = new TableColumn<>("Last Login Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("lastLoginTime"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<User, Boolean> suspendColumn = new TableColumn<>("Suspend");
        suspendColumn.setCellValueFactory(new PropertyValueFactory<>("suspended")); // ใช้ getter

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

        // Add users to TableView
        for (User user : userList.getUsers()) {
            tableView.getItems().add(user);
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


}
