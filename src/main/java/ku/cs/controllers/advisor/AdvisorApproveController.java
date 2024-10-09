package ku.cs.controllers.advisor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import ku.cs.models.Appeal;
import ku.cs.models.AppealList;
import ku.cs.models.User;
import ku.cs.services.AppealListDatasource;
import ku.cs.services.AppealSharedData;
import ku.cs.services.FXRouter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class AdvisorApproveController {
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

    @FXML Label DeclineWhen;

    @FXML Label DeclineDateLabel;

    @FXML private TextField declineTextField;

    @FXML private Button confirmButton;

    @FXML private Button declineButton;

    private User user;

    private String studentID;

    @FXML
    public void initialize() {
        setDefault();
        Appeal appeal = AppealSharedData.getSelectedAppeal();
        if (appeal != null) {
            typeLabel.setText(appeal.getType());
            subjectLabel.setText(appeal.getSubject());
            requestLabel.setText(appeal.getRequest());
            dateLabel.setText(appeal.getDate().toString());
            signatureLabel.setText(appeal.getStudentSignature());
            if(appeal.getStatus().equals("ปฏิเสธโดยอาจารย์ที่ปรึกษา คำร้องถูกปฏิเสธ")){
                confirmButton.setVisible(false);
                declineButton.setVisible(false);
                DeclineWhen.setVisible(true);
                DeclineDateLabel.setVisible(true);
                LocalDateTime time = appeal.getDeclineDateTime();
                DeclineDateLabel.setText(time.getDayOfMonth() + "/" + time.getMonth() + "/" + time.getYear() + "  " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
            } else if(appeal.getStatus().equals("อนุมัติโดยอาจารย์ที่ปรึกษา คำร้องส่งต่อให้หัวหน้าภาควิชา")) {
                confirmButton.setVisible(false);
                declineButton.setVisible(false);
            }
        }

        Object data = FXRouter.getData();
        if (data instanceof Pair) {
            Pair<User, String> pair = (Pair<User, String>) data;
            user = pair.getKey();
            studentID = pair.getValue();
        }
    }

    @FXML
    public void onApproveAppealClick(){
        Appeal appeal = AppealSharedData.getSelectedAppeal();
        if(appeal != null){
            appeal.setStatus("อนุมัติโดยอาจารย์ที่ปรึกษา คำร้องส่งต่อให้หัวหน้าภาควิชา");
            long second = new Date().getTime();
            appeal.setSecond(second);
        }
        AppealListDatasource datasource = new AppealListDatasource("data/appeals.csv");
        AppealList appealList = AppealSharedData.getNormalAppealList();

        datasource.writeData(appealList);
        try {
            FXRouter.goTo("advisor-appeal-page", new Pair<>(user, appeal.getStudentID()));
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
        String DeclineReason = "ถูกปฎิเสธเนื่องจาก" + declineTextField.getText();

        Appeal appeal = AppealSharedData.getSelectedAppeal();
        if(appeal != null){
            appeal.setDeclineReason(DeclineReason);
            appeal.setStatus("ปฏิเสธโดยอาจารย์ที่ปรึกษา คำร้องถูกปฏิเสธ");
            long second = new Date().getTime();
            appeal.setSecond(second);
            LocalDateTime declineDate = LocalDateTime.now();
            appeal.setDeclineDateTime(declineDate);
        }
        AppealListDatasource datasource = new AppealListDatasource("data/appeals.csv");
        AppealList appealList = AppealSharedData.getNormalAppealList();

        datasource.writeData(appealList);
        try {
            FXRouter.goTo("advisor-appeal-page", new Pair<>(user, studentID));
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

    private void setDefault(){
        confirmButton.setVisible(true);
        declineButton.setVisible(true);
    }


    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("advisor-appeal-page", new Pair<>(user, studentID));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
