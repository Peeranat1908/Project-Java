package ku.cs.controllers.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ku.cs.models.Theme;
import ku.cs.models.User;
import org.w3c.dom.ls.LSOutput;
import ku.cs.cs211671project.MainApplication;

import java.io.File;
import java.net.URL;

public class SettingController {
    @FXML private ChoiceBox<String> themeChoiceBox;
    @FXML private ChoiceBox<String> fontChoiceBox;
    @FXML private ChoiceBox<String> fontSizeChoiceBox;
    @FXML private Button switchThemeButton;
    @FXML private Button backButton;
    @FXML private AnchorPane mainPage;
    private Scene previousScene;
    @FXML private Button applyButton;


    String lightModePath = new File(System.getProperty("user.dir") + File.separator + "src/main/resources/ku/cs/style/baseOnPageLightMode.css").toURI().toString();
    String darkModePath = new File(System.getProperty("user.dir") + File.separator + "src/main/resources/ku/cs/style/baseOnPageDarkMode.css").toURI().toString();

    @FXML
    public void initialize() {
        loadFontChoice();
        loadFontSizeChoice();
        applyButton.setOnAction(actionEvent -> {applySettings();});
    }

    public void setPreviousScene(Scene previousScene) {
        System.out.println("Previous scene is " + previousScene);
        this.previousScene = previousScene;
    }

    public void loadFontChoice(){
        ObservableList<String> font = FXCollections.observableArrayList("16", "18", "20");
        fontChoiceBox.setItems(font);
    }

    public void loadFontSizeChoice(){
        ObservableList<String> fontSize = FXCollections.observableArrayList("16", "18", "20");
        fontSizeChoiceBox.setItems(fontSize);
    }

    private void applySettings() {
        // เรียกใช้การเปลี่ยนธีมที่นี่
//        changeTheme();
        // คุณสามารถเพิ่มการตั้งค่าอื่นๆ เช่น เปลี่ยนฟอนต์หรือขนาดฟอนต์ที่นี่ได้
    }


//    private void changeTheme() {
//        String selectedTheme = themeChoiceBox.getValue();
//        Stage stage = (Stage) themeChoiceBox.getScene().getWindow();
//
//        String cssPath = "";
//        if ("DarkMode".equals(selectedTheme)) {
//            cssPath = "/ku/cs/style/baseOnPageDarkMode.css";
//        } else {
//            cssPath = "/ku/cs/style/baseOnPageLightMode.css";
//        }
//
//        // ตรวจสอบว่า resource ถูกพบหรือไม่
//        URL resource = getClass().getResource(cssPath);
//        if (resource != null) {
//            System.out.println("CSS Path: " + resource.toExternalForm());
//            stage.getScene().getStylesheets().clear();
//            stage.getScene().getStylesheets().add(resource.toExternalForm());
//        } else {
//            System.err.println("Resource not found: " + cssPath);
//        }
//    }

    @FXML
    public void onSwitchThemeButtonClick() {
        // สลับระหว่างธีม
        if (Theme.getCurrentStyleSheet().equals(lightModePath)) {
            Theme.setCurrentStyleSheet(darkModePath);
        } else {
            Theme.setCurrentStyleSheet(lightModePath);
        }
        Scene currentScene = switchThemeButton.getScene();
        MainApplication.applyStylesheet(currentScene);
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
