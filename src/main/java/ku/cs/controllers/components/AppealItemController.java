package ku.cs.controllers.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import ku.cs.models.Appeal;
import ku.cs.models.User;
import ku.cs.services.AppealSharedData;
import ku.cs.services.FXRouter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class AppealItemController {
    @FXML
    private Label typeLabel;
    @FXML
    private Label subjectLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label signatureLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label timeLabel;

    private Appeal appeal;

    private User user;

    public void initialize(){
        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
        }
    }

    public void setAppealData(Appeal appeal) {
        this.appeal = appeal;
        typeLabel.setText(appeal.getType());
        subjectLabel.setText(appeal.getSubject());
        dateLabel.setText(appeal.getDate().toString());
        signatureLabel.setText(appeal.getStudentSignature());
        statusLabel.setText(appeal.getStatus());
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = appeal.getSendtime().format(timeFormatter);
        timeLabel.setText(formattedTime);

        if ("อนุมัติโดยอาจารย์ที่ปรึกษา คำร้องส่งต่อให้หัวหน้าภาควิชา".equals(appeal.getStatus())) {
            statusLabel.setTextFill(javafx.scene.paint.Color.GREEN); //สถานะเป็นอนุมัติ
        } else if("ปฏิเสธโดยอาจารย์ที่ปรึกษา คำร้องถูกปฏิเสธ".equals(appeal.getStatus())){
            statusLabel.setTextFill(Color.RED); //สถา่นะเป็นปฎิเสธ
        }

        typeLabel.getParent().setOnMouseClicked(event -> showAppealDetails());
    }

    private void showAppealDetails() {
        try {
            if (user == null) {
                System.out.println("User is null, can't proceed");
                return;
            }
            AppealSharedData.setSelectedAppeal(appeal);

            if(user.getRole().equals("student")) {
                FXRouter.goTo("appeal-detail", user);
            }else if(user.getRole().equals("departmentStaff")){
                FXRouter.goTo("major-accept-appeal", user);
            }else if(user.getRole().equals("advisor")){
                FXRouter.goTo("advisor-approve", user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}