package ku.cs.controllers.majorStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;
import ku.cs.models.Appeal;
import ku.cs.models.AppealList;
import ku.cs.models.MajorEndorser;
import ku.cs.models.User;
import ku.cs.services.AppealListDatasource;
import ku.cs.services.AppealSharedData;
import ku.cs.services.FXRouter;
import ku.cs.services.MajorEndorserListFileDatasource;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MajorAcceptAppealController {
    private User user;

    @FXML
    private Label typeLabel;
    @FXML
    private Label subjectLabel;
    @FXML
    private Label requestLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Spinner<Integer> daySpinner;
    @FXML
    private Spinner<Integer> monthSpinner;
    @FXML
    private Spinner<Integer> yearSpinner;
    @FXML
    private ChoiceBox<String> endorserBox;
    @FXML private TextField declineTextField;
    @FXML private CheckBox sendingToDean;
    @FXML private Label majorSignatureLabel;
    @FXML private Label majorDateLabel;
    @FXML private Button applyDeclineButton;
    @FXML private Button approveAppealButton;
    @FXML private Button declineButton;
    @FXML private Label signatureLabel;
    @FXML private Label declineLabel;
    @FXML private Label majorApproveWhen;
    @FXML private Label declineWhen;
    @FXML private Label DeclineDateLabel;
    @FXML private Label facultyApproveWhen;
    @FXML private Label facultyApprovedateLabel;
    @FXML private Label facultySignatureLabel;
    @FXML private Label approveWhen;
    @FXML private Label MajorEndorsers;

    private MajorEndorserListFileDatasource approveDataSource;

    private AppealList appealList;
    private AppealListDatasource datasource;
    private String studentID;

    public void initialize() {
        daySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, LocalDate.now().getDayOfMonth()));
        monthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, LocalDate.now().getMonthValue()));
        yearSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, LocalDate.now().getYear(), LocalDate.now().getYear()));

        Appeal appeal = AppealSharedData.getSelectedAppeal();
        if (appeal != null) {
            typeLabel.setText(appeal.getType());
            subjectLabel.setText(appeal.getSubject());
            requestLabel.setText(appeal.getRequest());
            dateLabel.setText(appeal.getDate().toString());
            signatureLabel.setText(appeal.getStudentSignature());


            if(appeal.getDeclineDateTime() != null){
                MajorEndorsers.setVisible(false);
                approveWhen.setVisible(false);
                declineWhen.setVisible(true);
                DeclineDateLabel.setText(appeal.getDeclineDateTime().toString());
                DeclineDateLabel.setVisible(true);
                declineLabel.setText(appeal.getDeclineReason());
                declineLabel.setVisible(true);
            }
            if (appeal.getMajorEndorserDate() != null){
                MajorEndorsers.setVisible(false);
                approveWhen.setVisible(false);
                majorDateLabel.setText(appeal.getMajorEndorserDate().toString());
                majorDateLabel.setVisible(true);
                majorApproveWhen.setVisible(true);
                majorSignatureLabel.setText(appeal.getMajorEndorserSignature());
                majorSignatureLabel.setVisible(true);
            }
            if (appeal.getFacultyEndorserDate() != null){
                MajorEndorsers.setVisible(false);
                approveWhen.setVisible(false);
                facultyApprovedateLabel.setText(appeal.getFacultyEndorserDate().toString());
                facultyApprovedateLabel.setVisible(true);
                facultyApproveWhen.setVisible(true);
                facultySignatureLabel.setText(appeal.getFacultyEndorserSignature());
                facultySignatureLabel.setVisible(true);
            }

        }

        // โหลดรายชื่อจากไฟล์ CSV ลงใน ChoiceBox
        loadEndorsersFromCSV("data/major-endorser.csv");

        if(appeal.getStatus().contains("โดยหัวหน้าภาควิชา") || appeal.getStatus().equals("ปฏิเสธโดยอาจารย์ที่ปรึกษา คำร้องถูกปฏิเสธ")){
            applyDeclineButton.setVisible(false);
            approveAppealButton.setVisible(false);
            daySpinner.setVisible(false);
            monthSpinner.setVisible(false);
            yearSpinner.setVisible(false);
            endorserBox.setVisible(false);
            sendingToDean.setVisible(false);
            declineButton.setVisible(false);
        }

        Object data = FXRouter.getData();
        if (data instanceof Pair) {
            Pair<User, String> pair = (Pair<User, String>) data;
            user = pair.getKey();
            studentID = pair.getValue();
        }
        if (data instanceof User) {
            user = (User) data;
            loadApproveChoice(user);
        }

    }

    public void loadApproveChoice(User user){
        approveDataSource = new MajorEndorserListFileDatasource("data", "major-endorser.csv");
        endorserBox.getItems().clear();
        ObservableList<String> approveName = FXCollections.observableArrayList();
        for (MajorEndorser majorEndorser: approveDataSource.readData().getMajorEndorsers()) {
            if (majorEndorser.getPosition().contains(user.getMajor())) {
                approveName.add(majorEndorser.getName() + " " + majorEndorser.getPosition());
            }
        }

        endorserBox.getItems().addAll(approveName);
    }


    @FXML
    public void onApplyAppealClick() {
        Appeal appeal = AppealSharedData.getSelectedAppeal();
        if (appeal != null) {
            String endorserValue = endorserBox.getValue();
            LocalDate today = LocalDate.now();
            String majorName = user.getMajor();
            if (endorserValue.contains(majorName)) {
                appeal.setMajorEndorserSignature(endorserValue);
                appeal.setMajorEndorserDate(today);
                appeal.setDeclineReason("");
                if (sendingToDean.isSelected()) {
                    appeal.setStatus("อนุมัติโดยหัวหน้าภาควิชา คำร้องส่งต่อให้คณบดี");
                } else {
                    appeal.setStatus("อนุมัติโดยหัวหน้าภาควิชา คำร้องดำเนินการครบถ้วน");
                }
            }

            AppealListDatasource datasource = new AppealListDatasource("data/appeals.csv");
            AppealList appealList = AppealSharedData.getNormalAppealList();
            datasource.writeData(appealList);

            try {
                FXRouter.goTo("departmentStaff", user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void DeclineApplyClick(){
        declineTextField.setVisible(true);
        applyDeclineButton.setVisible(true);
    }
    @FXML
    public void onApplyDeclineButton(){
        String DeclineReason = "ถูกปฎิเสธเนื่องจาก: " + declineTextField.getText();

        Appeal appeal = AppealSharedData.getSelectedAppeal();
        if(appeal != null){
            appeal.setDeclineReason(DeclineReason);
            appeal.setMajorEndorserSignature(endorserBox.getValue());
            appeal.setStatus("ปฏิเสธโดยหัวหน้าภาควิชา คำร้องถูกปฏิเสธ");
            appeal.setMajorEndorserDate(LocalDate.now());
            appeal.setDeclineDateTime(LocalDateTime.now());
        }
        AppealListDatasource datasource = new AppealListDatasource("data/appeals.csv");
        AppealList appealList = AppealSharedData.getNormalAppealList();


        datasource.writeData(appealList);
        try {
            FXRouter.goTo("departmentStaff", user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    // ฟังก์ชันสำหรับโหลดรายชื่อจาก CSV และเพิ่มเข้า ChoiceBox
    private void loadEndorsersFromCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // แทนที่เครื่องหมาย , ด้วยช่องว่าง
                String replacedLine = line.replace(",", " ");
                // เพิ่มข้อมูลลงใน ChoiceBox
                endorserBox.getItems().add(replacedLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void onBackButton() {
        try {
            if (user.getRole().equals("student")) {
                FXRouter.goTo("appeal-tracking", user);
            } else if (user.getRole().equals("advisor")) {
                FXRouter.goTo("advisor-appeal-page", user);
            } else if (user.getRole().equals("departmentStaff")) {
                FXRouter.goTo("departmentStaff", user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}