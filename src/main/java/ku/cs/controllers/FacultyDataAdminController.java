package ku.cs.controllers;

import ku.cs.services.FXRouter;

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
