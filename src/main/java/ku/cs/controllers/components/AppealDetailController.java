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
    private Label declineLabel;
    @FXML
    private Label DeclineDateLabel;
    @FXML
    private Label majorApprovedateLabel;
    @FXML
    private Label facultyApprovedateLabel;
    @FXML
    private Label declineWhen;
    @FXML
    private Label majorApproveWhen;
    @FXML
    private Label facultyApproveWhen;
    @FXML
    private Label majorSignatureLabel;
    @FXML
    private Label facultySignatureLabel;

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

            if(appeal.getDeclineDateTime() != null){
                declineWhen.setVisible(true);
                DeclineDateLabel.setText(appeal.getDeclineDateTime().toString());
                DeclineDateLabel.setVisible(true);
                declineLabel.setText(appeal.getDeclineReason());
                declineLabel.setVisible(true);
            }
            if (appeal.getMajorEndorserDate() != null){
                majorApprovedateLabel.setText(appeal.getMajorEndorserDate().toString());
                majorApprovedateLabel.setVisible(true);
                majorApproveWhen.setVisible(true);
                majorSignatureLabel.setText(appeal.getMajorEndorserSignature());
                majorSignatureLabel.setVisible(true);
            }
            if (appeal.getFacultyEndorserDate() != null){
                facultyApprovedateLabel.setText(appeal.getFacultyEndorserDate().toString());
                facultyApprovedateLabel.setVisible(true);
                facultyApproveWhen.setVisible(true);
                facultySignatureLabel.setText(appeal.getFacultyEndorserSignature());
                facultySignatureLabel.setVisible(true);
            }



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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
