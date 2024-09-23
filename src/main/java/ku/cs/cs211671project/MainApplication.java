package ku.cs.cs211671project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "CS211 Project", 1280, 720);
        configRoutes();
        FXRouter.goTo("first-page");
//        FXRouter.goTo("staff-table");
    }

    private void configRoutes() {
        String viewPath = "ku/cs/views/";
        FXRouter.when("hello", viewPath + "hello-view.fxml");
        FXRouter.when("first-page", viewPath + "first-page.fxml");
        FXRouter.when("login-page", viewPath + "login.fxml");
        FXRouter.when("register", viewPath + "register.fxml");
        FXRouter.when("student", viewPath + "main-student.fxml");
        FXRouter.when("my-team", viewPath + "my-team.fxml");
        FXRouter.when("resetPassword", viewPath + "resetpassword.fxml");
        FXRouter.when("main-advisor", viewPath + "main-advisor.fxml");
        FXRouter.when("request-page", viewPath + "request-page.fxml");
        FXRouter.when("student-appeal", viewPath + "student-appeal.fxml");
        FXRouter.when("normal-appeal", viewPath + "normal-appeal.fxml");
        FXRouter.when("main-admin", viewPath + "main-admin.fxml");
        FXRouter.when("user-details", viewPath + "userdetail.fxml");
        FXRouter.when("dashboard", viewPath + "dashboard.fxml");
        FXRouter.when("user-details", viewPath + "userdetail.fxml");
        FXRouter.when("add-staff", viewPath + "add-staff.fxml");
        FXRouter.when("staff-table", viewPath + "stafftable.fxml");

    }

    public static void main(String[] args) {
        launch();
    }
}