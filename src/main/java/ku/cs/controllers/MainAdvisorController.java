package ku.cs.controllers;

import javafx.fxml.FXML;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class MainAdvisorController extends StudentController{
    private String previousPage;


    @FXML
    public void onMyTeamButtonClick() throws RuntimeException {
//        Object temp = FXRouter.getData();
//        if (temp instanceof String) {
//            previousPage = (String)temp;
//        }

        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
