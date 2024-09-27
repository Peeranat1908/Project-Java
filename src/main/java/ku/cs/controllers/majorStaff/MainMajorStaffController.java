package ku.cs.controllers.majorStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import ku.cs.controllers.components.AppealItemController;
import ku.cs.models.Appeal;
import ku.cs.models.AppealList;
import ku.cs.models.User;
import ku.cs.services.AppealSharedData;
import ku.cs.services.AppealListDatasource;
import ku.cs.services.AppealSortComparator;
import ku.cs.services.FXRouter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;


public class MainMajorStaffController{
    @FXML private Label nameLabel;
    private User user;

    @FXML
    private void initialize() {
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
            updateUI();
        } else {

            nameLabel.setText("Invalid user data");
        }
    }

    private void updateUI() {
        if (user != null) {
            nameLabel.setText(user.getUsername());

        }
    }

    @FXML
    public void onEditEndorserButton(){
        navigateTo("edit-major-endorser", user);
    }
    @FXML
    public void onUserProfileButton(){
        navigateTo("user-profile", user);
    }

    private void navigateTo(String route, Object data) {
        try {
            FXRouter.goTo(route, data);
        } catch (IOException e) {
            System.err.println("Navigation to " + route + " failed: " + e.getMessage());
        }
    }


}

