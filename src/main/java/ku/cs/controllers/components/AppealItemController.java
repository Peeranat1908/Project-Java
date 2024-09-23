package ku.cs.controllers.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Label;
import ku.cs.models.Appeal;
import ku.cs.services.AppealSharedData;
import ku.cs.services.FXRouter;
import javafx.scene.layout.Pane;
import java.io.IOException;

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


    private Appeal appeal;

    public void setAppealData(Appeal appeal) {
        this.appeal = appeal;
        typeLabel.setText(appeal.getType());
        subjectLabel.setText(appeal.getSubject());
        dateLabel.setText(appeal.getDate().toString());
        signatureLabel.setText(appeal.getStudentSignature());
        statusLabel.setText(appeal.getStatus());

        typeLabel.getParent().setOnMouseClicked(event -> showAppealDetails());
    }

    private void showAppealDetails() {
        try {
            AppealSharedData.setSelectedAppeal(appeal);
            FXRouter.goTo("appeal-detail");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
