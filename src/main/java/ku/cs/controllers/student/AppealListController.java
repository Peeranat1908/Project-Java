package ku.cs.controllers.student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import ku.cs.models.NormalAppeal;
import ku.cs.models.NormalAppealList;
import ku.cs.services.AppealSharedData;
import ku.cs.services.FXRouter;

import java.io.IOException;
import java.util.List;

public class AppealListController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox appealVBox;

    @FXML
    private Label noAppealsLabel;

    private NormalAppealList normalAppealList;

    @FXML
    public void initialize() {
        //เอาคำร้องจาก SharedData
        normalAppealList = AppealSharedData.getNormalAppealList();
        loadAppeals();
    }
    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("student");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAppeals() {

        List<NormalAppeal> appeals = normalAppealList.getsAppeals();

        if(appeals.isEmpty()){
            noAppealsLabel.setVisible(true);
        }else{
            noAppealsLabel.setVisible(false);
            for (NormalAppeal appeal : appeals) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ku/cs/views/appeal-item.fxml"));
                    Pane pane = loader.load();

                    AppealItemController controller = loader.getController();
                    controller.setAppealData(appeal);

                    appealVBox.getChildren().add(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }


}
