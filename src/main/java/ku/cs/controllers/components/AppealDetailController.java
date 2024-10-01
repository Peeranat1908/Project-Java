package ku.cs.controllers.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.Appeal;
import ku.cs.models.User;
import ku.cs.services.AppealSharedData;
import ku.cs.services.FXRouter;

import java.io.IOException;


public class AppealDetailController {
    @FXML
    private Label typeLabel;
    @FXML
    private Label subjectLabel;
    @FXML
    private Label requestLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label signatureLabel;
    @FXML
    private Label majorDateLabel;
    @FXML
    private Label majorSignatureLabel;
    private User user;



    public void initialize() {
        Appeal appeal = AppealSharedData.getSelectedAppeal();
        if (appeal != null) {
            typeLabel.setText(appeal.getType());
            subjectLabel.setText(appeal.getSubject());
            requestLabel.setText(appeal.getRequest());
            dateLabel.setText(appeal.getDate().toString());
            signatureLabel.setText(appeal.getStudentSignature());
            majorSignatureLabel.setText(appeal.getMajorEndorserSignature());
        }

        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
        }
    }

    @FXML
    public void onBackButtonClick() {
        try {
            if(user.getRole().equals("student")){
                FXRouter.goTo("appeal-tracking", user);
            }
            else if(user.getRole().equals("advisor")){
                FXRouter.goTo("advisor-appeal-page", user);
            }
            else if(user.getRole().equals("departmentStaff")){
                FXRouter.goTo("departmentStaff",user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
