package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.User;
import ku.cs.models.Student;
//import ku.cs.models.Admin;
//import ku.cs.models.Advisor;
//import ku.cs.models.DepartmentStaff;
//import ku.cs.models.FacultyStaff;
import ku.cs.models.UserList;
import ku.cs.models.StudentList;
//import ku.cs.models.AdminList;
//import ku.cs.models.AdvisorList;
//import ku.cs.models.DepartmentStaffList;
//import ku.cs.models.FacultyStaffList;
import ku.cs.services.*;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label errorLabel;
    @FXML
    private TextField UsernameTextField;
    @FXML
    private PasswordField PasswordTextField;

    private UserList userList;

    @FXML
    private void initialize() {
        errorLabel.setText("");

        Datasource<UserList> datasource = new UserListFileDatasource("data", "user.csv");
        userList = datasource.readData();  // Load users into UserList
    }
    @FXML
    public void onLoginButtonClick() {
        try {
            handleLogin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onRegisterButtonClick() {
        try {
            FXRouter.goTo("register");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onResetPasswordButton() {
        try {
            FXRouter.goTo("resetPassword");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleLogin() throws IOException {
        String username = UsernameTextField.getText().trim();
        String password = PasswordTextField.getText().trim();

        // Authenticate user
        User user = userList.authenticate(username, password);

        if (user == null) {
            errorLabel.setText("Username or password is incorrect. Please try again.");
            return; // End the method if login is not successful
        }

        // Update last login information
        userList.updateLastLogin(username);

        // Write updated data to the respective role file
        //updateRoleFile(user);

        // Navigate based on user role
        navigateByRole(user);
    }

//    private void updateRoleFile(User user) throws IOException {
//        switch (user.getRole()) {
//            case "student":
//                if (user instanceof Student) {
//                    updateStudentFile((Student) user);
//                }
//                break;
////            case "admin":
////                updateAdminFile((Admin) user);
////                break;
//            case "advisor":
//                if (user instanceof Advisor) {
//                    updateAdvisorFile((Advisor) user);
//                }
//                break;
//            case "departmentStaff":
//                if (user instanceof DepartmentStaff) {
//                    updateDepartmentStaffFile((DepartmentStaff) user);
//                }
//                break;
//            case "facultyStaff":
//                if (user instanceof FacultyStaff) {
//                    updateFacultyStaffFile((FacultyStaff) user);
//                }
//                break;
//            default:
//                throw new IOException("Invalid role for file update.");
//        }
//    }
//
//    private void updateStudentFile(Student student) throws IOException {
//        StudentListFileDatasource studentDatasource = new StudentListFileDatasource("data", "student-info.csv");
//        StudentList studentList = studentDatasource.readData();
//        studentList.updateLastLogin(student.getUsername());
//        studentDatasource.writeData(studentList);
//    }

//    private void updateAdminFile(Admin admin) throws IOException {
//        AdminFileListDatasource adminDatasource = new AdminListFileDatasource("data", "admin.csv");
//        AdminList adminList = adminDatasource.readData();
//        adminList.updateLastLogin(admin.getUsername());
//        adminDatasource.writeData(adminList);
//    }

//    private void updateAdvisorFile(Advisor advisor) throws IOException {
//        AdvisorListFileDatasource advisorDatasource = new AdvisorListFileDatasource("data", "advisor-info.csv");
//        AdvisorList advisorList = advisorDatasource.readData();
//        advisorList.updateLastLogin(advisor.getUsername());
//        advisorDatasource.writeData(advisorList);
//    }
//
//    private void updateDepartmentStaffFile(DepartmentStaff departmentStaff) throws IOException {
//        DepartmentStaffListFileDatasource departmentStaffDatasource = new DepartmentStaffListFileDatasource("data", "departmentStaff-info.csv");
//        DepartmentStaffList departmentStaffList = departmentStaffDatasource.readData();
//        departmentStaffList.updateLastLogin(departmentStaff.getUsername());
//        departmentStaffDatasource.writeData(departmentStaffList);
//    }
//
//    private void updateFacultyStaffFile(FacultyStaff facultyStaff) throws IOException {
//        FacultyStaffListFileDatasource facultyStaffDatasource = new FacultyStaffListFileDatasource("data", "facultyStaff-info.csv");
//        FacultyStaffList facultyStaffList = facultyStaffDatasource.readData();
//        facultyStaffList.updateLastLogin(facultyStaff.getUsername());
//        facultyStaffDatasource.writeData(facultyStaffList);
//    }

    private void navigateByRole(User user) throws IOException {
        switch (user.getRole()) {
            case "student":
                FXRouter.goTo("student", user);
                break;
            case "admin":
                FXRouter.goTo("main-admin", user);
                break;
            case "advisor":
                FXRouter.goTo("advisor", user);
                break;
            case "facultyStaff":
                FXRouter.goTo("facultyStaff", user);
                break;
            case "departmentStaff":
                FXRouter.goTo("departmentStaff", user);
                break;
            default:
                errorLabel.setText("Invalid role. Please contact the administrator.");
                throw new IOException("Invalid role.");
        }
    }
}