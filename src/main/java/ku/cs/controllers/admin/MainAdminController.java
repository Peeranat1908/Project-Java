package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class MainAdminController {

    @FXML
    public void onLogOutButtonClick() {
        try {
            FXRouter.goTo("login-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onMyTeamButtonClick() {
        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
<<<<<<< HEAD:src/main/java/ku/cs/controllers/admin/MainAdminController.java

    @FXML
    public void onFacultyButtonClick(){
        try {
            FXRouter.goTo("facultyData-admin");
        } catch (IOException e) {
=======
    @FXML
    public void onManageFacultyButtonClick(){
        try{
            FXRouter.goTo("faculty-data-admin");
        } catch(IOException e){
>>>>>>> feature/student-system-2-1:src/main/java/ku/cs/controllers/MainAdminController.java
            throw new RuntimeException(e);
        }
    }
}
