package pl.pjatk.memorygame;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
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

        TextArea textArea = new TextArea();
        VBox root = new VBox(textArea);

        textArea.appendText(readHighScores());

        Scene scene = new Scene(root, 640, 480);

        textArea.setPrefRowCount(100);
        textArea.setWrapText(true);
        textArea.setFont(Font.font ("sans-serif", 24));
        textArea.setEditable(false);

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
        Scene scene = new Scene(fxmlLoader.load(), 340, 180);
        scene.getStylesheets().add(getClass().getResource("gameOption.css").toString());

        final ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("4x3");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("4x4");
        rb2.setToggleGroup(group);

        RadioButton rb3 = new RadioButton("5x4");
        rb3.setToggleGroup(group);

        stage.setTitle("New Game");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Application.PrimaryStage.show();
            }
        });
    }

    private String readHighScores(){
        return "Player 1 (Time: 43:12, grid 4x4)" + "\n" + "Player 1 (Time: 43:12, grid 4x4)" + "\n" + "Player 1 (Time: 43:12, grid 4x4)" + "\n" + "Player 1 (Time: 43:12, grid 4x4)" + "\n";
    }

}