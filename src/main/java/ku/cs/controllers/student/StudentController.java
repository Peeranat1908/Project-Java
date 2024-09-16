package ku.cs.controllers.student;

import javafx.fxml.FXML;
import ku.cs.services.FXRouter;
import java.io.IOException;

public class StudentController

{
    @FXML
    public void onMyTeamButtonClick() {
        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void selectAppealButtonClick() {
        try {
            FXRouter.goTo("student-appeal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onAppealTrackingClick(){
        try {
            FXRouter.goTo("appeal-tracking");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
