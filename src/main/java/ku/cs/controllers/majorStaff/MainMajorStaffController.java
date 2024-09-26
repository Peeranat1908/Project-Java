package ku.cs.controllers.majorStaff;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ku.cs.models.Student;
import ku.cs.models.StudentList;
import ku.cs.models.User;
import ku.cs.services.Datasource;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class MainMajorStaffController {
    @FXML
    private Label errorLabel;
    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButtonClick;

    private StudentList studentList;

    private Datasource<StudentList> datasource;
    private User user;

    @FXML
    public void initialize() {
//        errorLabel.setText("");
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
        }
    }


    @FXML
    public void onMyTeamButtonClick() throws RuntimeException {
//        Object temp = FXRouter.getData();
//        if (temp instanceof String) {
//            previousPage = (String)temp;
//        }

        try {
            FXRouter.goTo("my-team");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onRequestButtonClick() {
        try {
            FXRouter.goTo("request-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onStudentListButtonClick(){
        try {
            FXRouter.goTo("studentInMajor",user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onEndorserButtonClick(){
        try {
            FXRouter.goTo("majorEndorser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
