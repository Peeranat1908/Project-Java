package ku.cs.controllers.majorStaff;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import ku.cs.models.Appeal;
import ku.cs.models.AppealList;
import ku.cs.models.User;
import ku.cs.services.AppealListDatasource;
import ku.cs.services.AppealSharedData;
import ku.cs.services.FXRouter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

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
    @FXML private Label majorSignatureLabel;
    @FXML private Label majorDateLabel;

    private AppealList appealList;
    private AppealListDatasource datasource;

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
            majorSignatureLabel.setText(appeal.getMajorEndorserSignature());
            majorDateLabel.setText(appeal.getMajorEndorserDate().toString());
        }

        // โหลดรายชื่อจากไฟล์ CSV ลงใน ChoiceBox
        loadEndorsersFromCSV("data/major-endorser.csv");

        Object data = FXRouter.getData();
        if (data instanceof User) {
            user = (User) data;
        }
    }

    @FXML
    public void onApplyAppealClick() {
        Appeal appeal = AppealSharedData.getSelectedAppeal();
        if (appeal != null) {
            String endorserValue = endorserBox.getValue();
            appeal.setMajorEndorserSignature("อนุมัติโดย " + endorserValue);

            // Check if the endorser's name contains "หัวหน้าภาควิชา"
            if (endorserValue.contains("ภาควิชาวิทยาการคอมพิวเตอร์")) {
                appeal.setMajorEndorserSignature(appeal.getMajorEndorserSignature() + " คำร้องดำเนินการครบถ้วน");
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
