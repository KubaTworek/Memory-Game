package pl.pjatk.memorygame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class Controller {

    @FXML
    public Button highScoresBtn;
    @FXML
    public Button exitBtn;
    @FXML
    public Button newGameBtn;
    @FXML
    public Label sNumber;

    @FXML
    public void onNewGameBtnClick(ActionEvent actionEvent) throws IOException {
        creatingNewGameOptions();
    }

    @FXML
    public void onHighScoresBtnClick(ActionEvent actionEvent) throws IOException {
        creatingHighScoreStage();
    }

    @FXML
    public void onExitBtnClick() {
        Platform.exit();
    }

    private void creatingHighScoreStage() throws IOException {
        Application.PrimaryStage.hide();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("highScoresPage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        scene.getStylesheets().add(getClass().getResource("style.css").toString());

        stage.setTitle("High Scores");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Application.PrimaryStage.show();
            }
        });
    }

    private void creatingNewGameOptions() throws IOException {
        Application.PrimaryStage.hide();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("newGameOptions-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        scene.getStylesheets().add(getClass().getResource("style.css").toString());

        stage.setTitle("New Game");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Application.PrimaryStage.show();
            }
        });
    }

}