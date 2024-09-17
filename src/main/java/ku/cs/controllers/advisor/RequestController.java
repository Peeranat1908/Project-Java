package ku.cs.controllers.advisor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class RequestController {

    @FXML
    private Button acceptButtonClick;
    @FXML
    private Button rejectButtonClick;

    @FXML
    public void onBackButtonClick(){
        try{
            FXRouter.goTo("main-advisor");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
