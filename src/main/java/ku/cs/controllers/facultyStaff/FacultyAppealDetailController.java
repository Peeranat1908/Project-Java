package ku.cs.controllers.facultyStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.models.Appeal;
import ku.cs.models.AppealList;
import ku.cs.models.User;
import ku.cs.services.AppealListDatasource;
import ku.cs.services.AppealSharedData;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class FacultyAppealDetailController {
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
    private Button resetDecline;
    @FXML
    private Button applyDecline;

    @FXML
    private TextField declineTextField;


    private User user;

    @FXML
    public void initialize() {
        Appeal appeal = AppealSharedData.getSelectedAppeal();
        if (appeal != null) {
            typeLabel.setText(appeal.getType());
            subjectLabel.setText(appeal.getSubject());
            requestLabel.setText(appeal.getRequest());
            dateLabel.setText(appeal.getDate().toString());
            signatureLabel.setText(appeal.getStudentSignature());
        }

        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
        }
    }

    @FXML
    public void onApproveAppealClick(){
        Appeal appeal = AppealSharedData.getSelectedAppeal();
        if(appeal != null){
            appeal.setStatus("อนุมัติโดยคณบดี คำร้องดำเนินการครบถ้วน");
        }
        AppealListDatasource datasource = new AppealListDatasource("data/appeals.csv");
        AppealList appealList = AppealSharedData.getNormalAppealList();

        datasource.writeData(appealList);
        try {
            FXRouter.goTo("facultyAppeal", user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onDeclineAppealClick(){
        declineTextField.setVisible(true);
        resetDecline.setVisible(true);
        applyDecline.setVisible(true);
    }

    @FXML
    public void DeclineApplyClick(){
        String DeclineReason = declineTextField.getText();

        Appeal appeal = AppealSharedData.getSelectedAppeal();
        if(appeal != null){
            appeal.setDeclineReason(DeclineReason);
            appeal.setStatus("ปฏิเสธโดยคณบดี คำร้องถูกปฏิเสธ");
        }
        AppealListDatasource datasource = new AppealListDatasource("data/appeals.csv");
        AppealList appealList = AppealSharedData.getNormalAppealList();

        datasource.writeData(appealList);
        try {
            FXRouter.goTo("facultyAppeal", user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onResetDeclineClick(){
        clearFields();
    }

    private void clearFields() {
        declineTextField.clear();
    }




    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("facultyAppeal", user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
