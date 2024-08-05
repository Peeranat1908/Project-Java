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
        FXRouter.bind(this, stage, "CS211 Project", 1440, 1024);
        configRoutes();
<<<<<<< HEAD
        FXRouter.goTo("first-page");
        FXRouter.goTo("loginpage");
=======
        FXRouter.goTo("student");
>>>>>>> feature/studentpage
    }

    private void configRoutes() {
        String viewPath = "ku/cs/views/";
        FXRouter.when("hello", viewPath + "hello-view.fxml");
<<<<<<< HEAD
        FXRouter.when("first-page", viewPath + "first-page.fxml");
        FXRouter.when("loginpage", viewPath + "login.fxml");
        FXRouter.when("register", viewPath + "register.fxml");
=======
        FXRouter.when("student", viewPath + "main-student.fxml");
>>>>>>> feature/studentpage
    }

    public static void main(String[] args) {
        launch();
    }
}