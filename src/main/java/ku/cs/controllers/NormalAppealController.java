package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import ku.cs.services.FXRouter;
import java.io.IOException;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import ku.cs.models.NormalAppeal;
import ku.cs.models.NormalAppealList;
import java.time.LocalDate;
import ku.cs.services.AppealSharedData;

public class NormalAppealController {
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextField requestTextField;
    @FXML
    private Spinner<Integer> daySpinner;
    @FXML
    private Spinner<Integer> monthSpinner;
    @FXML
    private Spinner<Integer> yearSpinner;
    @FXML
    private TextField signatureTextField;
    private NormalAppealList normalAppealList;

    @FXML
    public void initialize() {
        daySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, LocalDate.now().getDayOfMonth()));
        monthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, LocalDate.now().getMonthValue()));
        yearSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, LocalDate.now().getYear(), LocalDate.now().getYear()));

        normalAppealList = new NormalAppealList();
    }

    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("student-appeal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onApplyAppealClick() {
        String subject = subjectTextField.getText();
        String request = requestTextField.getText();
        int day = daySpinner.getValue();
        int month = monthSpinner.getValue();
        int year = yearSpinner.getValue();
        LocalDate date = LocalDate.of(year, month, day);
        String studentSignature = signatureTextField.getText();

        NormalAppeal appeal = new NormalAppeal(subject, request, date, studentSignature);

        AppealSharedData.getNormalAppealList().addAppeal(appeal);
        clearFields();
    }

    @FXML
    public void onResetAppealClick() {
        clearFields();
    }


    private void clearFields() {
        subjectTextField.clear();
        requestTextField.clear();
        daySpinner.getValueFactory().setValue(LocalDate.now().getDayOfMonth());
        monthSpinner.getValueFactory().setValue(LocalDate.now().getMonthValue());
        yearSpinner.getValueFactory().setValue(LocalDate.now().getYear());
        signatureTextField.clear();
    }

}
