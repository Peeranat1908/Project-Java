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
        //FXRouter.goTo("main-advisor");
        //FXRouter.goTo("departmentStaff");
        //FXRouter.goTo("first-page");
        //FXRouter.goTo("main-advisor");
        //FXRouter.goTo("faculty-data-admin");
        //FXRouter.goTo("main-admin");
        //FXRouter.goTo("main-advisor");
        //FXRouter.goTo("student");
        //FXRouter.goTo("major-data-admin");
        //FXRouter.goTo("edit-data-faculty");
        //FXRouter.goTo("edit-data-major");
        //FXRouter.goTo("departmentStaff");
        //FXRouter.goTo("facultyStaff");
        //FXRouter.goTo("first-page");

//        FXRouter.goTo("staff-table-admin");

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
        FXRouter.when("faculty-data-admin", viewPath + "faculty-data-admin.fxml");

        FXRouter.when("appeal-tracking", viewPath + "appeal-list.fxml");
        FXRouter.when("faculty-data-admin", viewPath + "faculty-data-admin.fxml");
        FXRouter.when("user-profile", viewPath + "user-profile.fxml");
        FXRouter.when("studentInMajor", viewPath + "student-in-major.fxml");
        FXRouter.when("facultyStaff", viewPath + "faculty-staff.fxml");

        FXRouter.when("faculty-data-admin", viewPath + "faculty-data-admin.fxml");
        FXRouter.when("appeal-tracking", viewPath + "appeal-list.fxml");
        FXRouter.when("major-data-admin", viewPath + "major-data-admin.fxml");
        FXRouter.when("edit-data-faculty", viewPath + "edit-data-faculty.fxml");
        FXRouter.when("add-new-faculty", viewPath + "addNewFacultyData.fxml");
        FXRouter.when("edit-data-major",  viewPath + "edit-data-major.fxml");
        FXRouter.when("add-new-major", viewPath + "add-new-major.fxml");
        FXRouter.when("departmentStaff", viewPath + "main-major-staff.fxml");
        FXRouter.when("majorEndorser", viewPath + "major-endorser-staff.fxml");
        FXRouter.when("faculty-data-admin", viewPath + "faculty-data-admin.fxml");
        FXRouter.when("appeal-tracking", viewPath + "appeal-list.fxml");
        FXRouter.when("user-profile", viewPath + "user-profile.fxml");
        FXRouter.when("departmentStaff", viewPath + "major-staff.fxml");
        FXRouter.when("facultyStaff", viewPath + "faculty-staff.fxml");
        FXRouter.when("leave-appeal", viewPath + "leave-appeal.fxml");
        FXRouter.when("enroll-appeal", viewPath + "enroll-appeal.fxml");
        FXRouter.when("appeal-detail", viewPath + "appeal-detail.fxml");
        FXRouter.when("dashboard", viewPath + "dashboard.fxml");
        FXRouter.when("user-details", viewPath + "user-detail-admin.fxml");
        FXRouter.when("add-staff", viewPath + "add-staff.fxml");
        FXRouter.when("staff-table-admin", viewPath + "staff-table-admin.fxml");
        FXRouter.when("staff-edit", viewPath + "staffedit.fxml");
    }


    public static void main(String[] args) {
        launch();
    }
}