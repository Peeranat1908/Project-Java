package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import ku.cs.controllers.NavigationHistoryService;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class DashboardController {
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
    public void onMyTeamButtonClick() throws RuntimeException {
        NavigationHistoryService.getInstance().pushPage("dashboard");
        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
