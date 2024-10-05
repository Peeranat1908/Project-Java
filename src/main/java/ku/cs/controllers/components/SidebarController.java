package ku.cs.controllers.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class SidebarController {
    private Sidebar sidebar;
    @FXML Button backButton;
    @FXML Button settingsButton;

    public void setSidebar(Sidebar sidebar) {
        this.sidebar = sidebar;
    }

    @FXML
    public void initialize() {
        backButton.setOnAction(actionEvent -> {closeSidebar();});
        settingsButton.setOnAction(actionEvent -> {onSettingsButtonClick();});
    }
    public void closeSidebar() {
        if (sidebar != null) {
            sidebar.closeSidebar(); // เรียกใช้ฟังก์ชัน closeSidebar() ของ Sidebar ที่ถูกตั้งค่า
        }
    }
    @FXML
    public void onLogoutButtonClick() {
        try{
            FXRouter.goTo("login-page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onSettingsButtonClick() {
        try {
            // เก็บ Scene ปัจจุบันเพื่อใช้ย้อนกลับในหน้า Setting
            Stage stage = (Stage) backButton.getScene().getWindow(); // ใช้ backButton หรือปุ่มอื่นที่มีการใช้งานอยู่ใน Sidebar
            Scene currentScene = stage.getScene();

            // โหลดหน้าต่าง Setting
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ku/cs/views/other/setting.fxml"));
            Parent root = loader.load();

            // ตั้งค่า Scene ก่อนหน้าสำหรับ SettingController เพื่อสามารถย้อนกลับได้
            SettingController controller = loader.getController();
            controller.setPreviousScene(currentScene);

            // แสดงหน้าต่าง Setting
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

