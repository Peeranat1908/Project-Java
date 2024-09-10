package ku.cs.controllers;

import javafx.fxml.FXML;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class RequestController {

    @FXML
    public void onBackButtonClick(){
        try{
            FXRouter.goTo("main-advisor");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
