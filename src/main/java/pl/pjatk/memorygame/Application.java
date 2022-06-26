package pl.pjatk.memorygame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {

    public static Stage PrimaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mainPage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 840, 440);
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toString());

        PrimaryStage = stage;
        stage.setTitle("Memory The Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}