package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.services.FXRouter;
import java.io.IOException;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class LoginController {

    @FXML
    protected void onBLoginuttonClick() {
        try {
            FXRouter.goTo("hello");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    }
