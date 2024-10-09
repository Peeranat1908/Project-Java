package ku.cs.controllers.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ku.cs.models.User;

public class SettingController {
    @FXML private ChoiceBox<String> themeChoiceBox;
    @FXML private ChoiceBox<String> fontChoiceBox;
    @FXML private ChoiceBox<String> fontSizeChoiceBox;
    @FXML private Button applyButton;
    @FXML private Button backButton;
    private Scene previousScene;

    @FXML
    public void initialize() {
        loadThemeChoice();
        loadFontChoice();
        loadFontSizeChoice();
        themeChoiceBox.getSelectionModel().selectFirst(); // ตั้งค่าเริ่มต้น
        setupThemeChangeListener();
    }

    public void setPreviousScene(Scene previousScene) {
        System.out.println("Previous scene is " + previousScene);
        this.previousScene = previousScene;
    }

    public void loadThemeChoice(){
        ObservableList<String> theme = FXCollections.observableArrayList("LightMode", "DarkMode");
        themeChoiceBox.setItems(theme);
    }

    public void loadFontChoice(){
        ObservableList<String> font = FXCollections.observableArrayList("16", "18", "20");
        fontChoiceBox.setItems(font);
    }

    public void loadFontSizeChoice(){
        ObservableList<String> fontSize = FXCollections.observableArrayList("16", "18", "20");
        fontSizeChoiceBox.setItems(fontSize);
    }

    private void setupThemeChangeListener() {
        themeChoiceBox.setOnAction(event -> {
            String selectedTheme = themeChoiceBox.getValue();
            Stage stage = (Stage) themeChoiceBox.getScene().getWindow();
            if ("DarkMode".equals(selectedTheme)) {
                stage.getScene().getStylesheets().clear();
                stage.getScene().getStylesheets().add(getClass().getResource("/ku/cs/style/baseOnPageDarkMode.css").toExternalForm());
            } else {
                stage.getScene().getStylesheets().clear();
                stage.getScene().getStylesheets().add(getClass().getResource("/ku/cs/style/baseOnPageLightMode.css").toExternalForm());
            }
        });
    }


    @FXML
    public void onBackButtonClicked() {
        // ดึง Stage ปัจจุบัน
        Stage stage = (Stage) backButton.getScene().getWindow();
        // ตั้งค่า Scene เดิมกลับไป
        if (previousScene != null) {
            System.out.println("Returning previous scene");
            stage.setScene(previousScene);
        }
        else {
            System.out.println("Previous scene is null");
        }
    }
}
