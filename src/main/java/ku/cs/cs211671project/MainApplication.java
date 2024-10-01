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
        //FXRouter.goTo("main-admin");
        //FXRouter.goTo("majorEndorser");
        //FXRouter.goTo("main-admin");
        //FXRouter.goTo("facultyStaff");
        //FXRouter.goTo("main-admin");
    }
    private void configRoutes() {
        String viewPath = "ku/cs/views/";
        FXRouter.when("hello", viewPath + "other/hello-view.fxml");
        FXRouter.when("first-page", viewPath + "other/first-page.fxml");
        FXRouter.when("login-page", viewPath + "login/login.fxml");
        FXRouter.when("register", viewPath + "login/register.fxml");
        FXRouter.when("student", viewPath + "student/main-student.fxml");
        FXRouter.when("my-team", viewPath + "other/my-team.fxml");
        FXRouter.when("resetPassword", viewPath + "login/resetpassword.fxml");
        FXRouter.when("main-advisor", viewPath + "advisor/main-advisor.fxml");
        FXRouter.when("advisor-appeal-page", viewPath + "advisor/advisor-appeal-page.fxml");
        FXRouter.when("student-appeal", viewPath + "student/student-appeal.fxml");
        FXRouter.when("normal-appeal", viewPath + "student/normal-appeal.fxml");
        FXRouter.when("main-admin", viewPath + "admin/main-admin.fxml");
        FXRouter.when("faculty-data-admin", viewPath + "facultyStaff/faculty-data-admin.fxml");
        FXRouter.when("appeal-tracking", viewPath + "student/appeal-list.fxml");
        FXRouter.when("user-profile", viewPath + "student/user-profile.fxml");
        FXRouter.when("studentInMajor", viewPath + "departmentStaff/student-in-major.fxml");
        FXRouter.when("facultyStaff", viewPath + "facultyStaff/faculty-staff.fxml");
        FXRouter.when("faculty-data-admin", viewPath + "admin/faculty-data-admin.fxml");
        FXRouter.when("appeal-tracking", viewPath + "student/appeal-list.fxml");
        FXRouter.when("major-data-admin", viewPath + "admin/major-data-admin.fxml");
        FXRouter.when("edit-data-faculty", viewPath + "admin/edit-data-faculty.fxml");
        FXRouter.when("add-new-faculty", viewPath + "admin/addNewFacultyData.fxml");
        FXRouter.when("edit-data-major",  viewPath + "admin/edit-data-major.fxml");
        FXRouter.when("add-new-major", viewPath + "admin/add-new-major.fxml");
        FXRouter.when("departmentStaff", viewPath + "departmentStaff/main-major-staff.fxml");
        FXRouter.when("majorEndorser", viewPath + "departmentStaff/major-endorser-staff.fxml");
        FXRouter.when("faculty-data-admin", viewPath + "admin/faculty-data-admin.fxml");
        FXRouter.when("appeal-tracking", viewPath + "student/appeal-list.fxml");
        FXRouter.when("facultyStaff", viewPath + "facultyStaff/faculty-staff.fxml");
        FXRouter.when("leave-appeal", viewPath + "student/leave-appeal.fxml");
        FXRouter.when("enroll-appeal", viewPath + "student/enroll-appeal.fxml");
        FXRouter.when("appeal-detail", viewPath + "student/appeal-detail.fxml");
        FXRouter.when("dashboard", viewPath + "admin/dashboard.fxml");
        FXRouter.when("user-details", viewPath + "admin/user-detail-admin.fxml");
        FXRouter.when("add-staff", viewPath + "admin/add-staff.fxml");
        FXRouter.when("staff-table-admin", viewPath + "admin/staff-table-admin.fxml");
        FXRouter.when("staff-edit", viewPath + "admin/staffedit.fxml");
        FXRouter.when("approve-faculty-staff", viewPath + "facultyStaff/approver-faculty.fxml");
        FXRouter.when("editApproveFacultyStaff", viewPath + "facultyStaff/edit-approve-faculty-staff.fxml");
        FXRouter.when("addApproveFacultyStaff", viewPath + "facultyStaff/add-approve-faculty-staff.fxml");
    }


    public static void main(String[] args) {
        launch();
    }
}