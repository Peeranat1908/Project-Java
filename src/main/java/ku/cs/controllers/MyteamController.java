package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.services.FXRouter;

import java.io.IOException;

public class MyteamController {
    @FXML private ImageView backImage;
    @FXML private ImageView groupImage;
    @FXML private ImageView augustyouthImage;
    @FXML private ImageView frameImage;
    @FXML private ImageView peteImage;
    @FXML private ImageView nickImage;
    @FXML private Button teamButton;
    @FXML private Label teamnameLabel;
    @FXML private Button backbutton;

    @FXML
    public void initialize(){
        Image back = new Image(getClass().getResourceAsStream("/images/Back.png"));
        Image group = new Image(getClass().getResourceAsStream("/images/Group.png"));
        Image augustyouth = new Image(getClass().getResourceAsStream("/images/augustyouth.png"));
        Image frame = new Image(getClass().getResourceAsStream("/images/frame.png"));
        Image pete = new Image(getClass().getResourceAsStream("/images/pete.png"));
        Image nick = new Image(getClass().getResourceAsStream("/images/nick.png"));
        backImage.setImage(back);
        groupImage.setImage(group);
        augustyouthImage.setImage(augustyouth);
        frameImage.setImage(frame);
        peteImage.setImage(pete);
        nickImage.setImage(nick);

    }
    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("hello");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
