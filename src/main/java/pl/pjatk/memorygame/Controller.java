package pl.pjatk.memorygame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
        scene.getStylesheets().add(getClass().getResource("main.css").toString());

        stage.setTitle("New Game");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Application.PrimaryStage.show();
            }
        });
    }

    private String readHighScores() throws FileNotFoundException {
        File file = new File("highscores.txt");
        Scanner in = new Scanner(file);

        StringBuilder highScores = new StringBuilder("");
        while(in.hasNextLine()){
            highScores.append(in.nextLine() + '\n');
        }

        return highScores.toString();
    }
}