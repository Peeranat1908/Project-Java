package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.UserCredential;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;
import ku.cs.services.UserCredentialsListFileDatasource;
import ku.cs.models.UserCredentialList;
import java.io.IOException;

public class FacultyDataAdminController {

    public void onHomeButtonClick() {
        try {
            FXRouter.goTo("main-admin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
