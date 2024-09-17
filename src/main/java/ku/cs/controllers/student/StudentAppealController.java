package ku.cs.controllers.student;

import javafx.fxml.FXML;
import ku.cs.services.FXRouter;
import java.io.IOException;


public class StudentAppealController {
    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("student");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onNormalAppealClick() {
        try {
            FXRouter.goTo("normal-appeal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
