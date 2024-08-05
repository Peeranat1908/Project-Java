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
<<<<<<< HEAD
        FXRouter.bind(this, stage, "CS211 Project", 1440, 1024);
        configRoutes();
        FXRouter.goTo("first-page");
        FXRouter.goTo("loginpage");
        FXRouter.goTo("student");
=======
        FXRouter.bind(this, stage, "CS211 Project", 1280, 720);
        configRoutes();
        FXRouter.goTo("my-team");
>>>>>>> feature/my-team
    }

    private void configRoutes() {
        String viewPath = "ku/cs/views/";
        FXRouter.when("hello", viewPath + "hello-view.fxml");
<<<<<<< HEAD
        FXRouter.when("first-page", viewPath + "first-page.fxml");
        FXRouter.when("loginpage", viewPath + "login.fxml");
        FXRouter.when("register", viewPath + "register.fxml");
        FXRouter.when("student", viewPath + "main-student.fxml");
=======
        FXRouter.when("my-team", viewPath + "my-team.fxml");
>>>>>>> feature/my-team
    }

    public static void main(String[] args) {
        launch();
    }
}