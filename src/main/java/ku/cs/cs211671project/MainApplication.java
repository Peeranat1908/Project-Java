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
        FXRouter.bind(this, stage, "CS211 Project", 1024, 768);
        configRoutes();
<<<<<<< HEAD
        FXRouter.goTo("first-page");
=======
        FXRouter.goTo("loginpage");
>>>>>>> feature/Login-Page
    }

    private void configRoutes() {
        String viewPath = "ku/cs/views/";
        FXRouter.when("hello", viewPath + "hello-view.fxml");
<<<<<<< HEAD
        FXRouter.when("first-page", viewPath + "first-page.fxml");
=======
        FXRouter.when("loginpage", viewPath + "login.fxml");
>>>>>>> feature/Login-Page
    }

    public static void main(String[] args) {
        launch();
    }
}