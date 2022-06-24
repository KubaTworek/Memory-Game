package pl.pjatk.memorygame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class GameOptionController {
    @FXML
    public Button playBtn;

    @FXML
    public void onPlayBtn(ActionEvent actionEvent) throws IOException {
        (((Node)(actionEvent.getSource())).getScene().getWindow()).hide();
        creatingNewGame();
    }

    private void creatingNewGame() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("newGame-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        scene.getStylesheets().add(getClass().getResource("main.css").toString());

        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }
}
