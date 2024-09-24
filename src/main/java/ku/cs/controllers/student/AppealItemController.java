package ku.cs.controllers.student;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.Appeal;

public class AppealItemController {
    @FXML
    private Label subjectLabel;
    @FXML
    private Label requestLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label signatureLabel;

    public void setAppealData(Appeal appeal) {
        subjectLabel.setText(appeal.getSubject());
        requestLabel.setText(appeal.getRequest());
        dateLabel.setText(appeal.getDate().toString());
        signatureLabel.setText(appeal.getStudentSignature());
    }
}
