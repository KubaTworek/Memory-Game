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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController {
    @FXML
    public Button playBtn;
    @FXML
    public TextField widthGame;
    @FXML
    public TextField heightGame;
    @FXML
    public Label warningGame;

    Label time = new Label();
    static int widthGaps;
    static int heightGaps;
    static String timeData;

    @FXML
    public void onPlayBtn(ActionEvent actionEvent) throws IOException {
        if(isGameRight(Integer.parseInt(widthGame.getText()), Integer.parseInt(heightGame.getText()))){
            (((Node) (actionEvent.getSource())).getScene().getWindow()).hide();
            creatingNewGame(widthGame.getText(), heightGame.getText());
        } else {
            warningGame.setText("Wpisz prawidłowy rozmiar\nPlansza musi mieć parzystą liczbę kafelków i nie większą niż 12");
            warningGame.setStyle("-fx-text-fill: red;-fx-text-alignment: center;");
        }
    }

    public boolean isGameRight(int width, int height){
        int size = width*height;
        return size % 2 == 0 && size < 13;
    }

    private void creatingNewGame(String width, String height) throws IOException {

        AtomicInteger counter = new AtomicInteger();
        AtomicInteger lastIndex = new AtomicInteger();
        AtomicInteger prelastIndex = new AtomicInteger();
        List<Button> buttonList = new ArrayList<>();

        Thread timerThread = new Thread(() -> {
            int seconds = 0;
            int minutes = 0;
            while (true) {
                try {
                    Thread.sleep(1000); //1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seconds++;
                int finalSeconds = seconds;
                Platform.runLater(() -> {
                    time.setText(minutes + ":" + finalSeconds);
                });
            }
        });
        timerThread.start();

        Stage stage = new Stage();

        heightGaps = Integer.parseInt(height);
        widthGaps = Integer.parseInt(width);

        FlowPane flowPane = new FlowPane();

        String[] imgs = {"ciri.png", "ciri.png", "geralt.png", "geralt.png", "iorweth.png", "iorweth.png", "jaskier.png", "jaskier.png", "triss.png", "triss.png", "yen.png", "yen.png"};
        List<String> imgsArr = new ArrayList<>();

        for (int i = 0; i < widthGaps * heightGaps; i++) {
            imgsArr.add(imgs[i]);
        }

        Collections.shuffle(imgsArr);

        for (int i = 0; i < widthGaps * heightGaps; i++) {
            Button button = new Button();
            button.setPrefHeight(125);
            button.setPrefWidth(125);
            String imgSource = imgsArr.get(i);
            button.setId(imgSource);
            buttonList.add(button);
            Image card = new Image(getClass().getResourceAsStream("img/karta.png"));
            button.setGraphic(new ImageView(card));
            button.setStyle("-fx-cursor: hand");

            button.setOnAction(event -> {
                counter.getAndIncrement();

                if (counter.get() % 2 == 1) {
                    buttonList.get(lastIndex.intValue()).setGraphic(new ImageView(card));
                    buttonList.get(prelastIndex.intValue()).setGraphic(new ImageView(card));
                }

                Button clickedButton = (Button) event.getSource();
                Image image = new Image(getClass().getResourceAsStream("img/" + clickedButton.getId()));
                button.setGraphic(new ImageView(image));

                if (counter.get() % 2 == 0 && (buttonList.indexOf(clickedButton) != buttonList.indexOf(buttonList.get(lastIndex.intValue())))) {
                    if (buttonList.get(lastIndex.intValue()).getId().equals(clickedButton.getId())) {
                        clickedButton.setDisable(true);
                        buttonList.get(lastIndex.intValue()).setDisable(true);
                    } else {
                        buttonList.get(lastIndex.intValue()).setDisable(false);
                    }
                }

                prelastIndex.set(lastIndex.get());
                lastIndex.set(buttonList.indexOf(clickedButton));

                if (isGameEnd(buttonList)) {
                    try {
                        timeData = time.getText();
                        endGame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                }

            });
            flowPane.getChildren().add(button);
        }

        Scene scene = new Scene(flowPane, 141*heightGaps, 133*widthGaps);
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }

    public boolean isGameEnd(List<Button> buttons) {
        for (Button button : buttons) {
            if (!button.isDisabled()) {
                return false;
            }
        }
        return true;
    }

    public void endGame() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("askForName-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 280);
        scene.getStylesheets().add(GameController.class.getResource("main.css").toString());

        stage.setTitle("Rank");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Application.PrimaryStage.show();
            }
        });
    }

    @FXML
    public Button confirmBtn;
    @FXML
    public TextField inputName;
    @FXML
    public Label warningName;

    @FXML
    public void onConfirmBtn(ActionEvent actionEvent) throws IOException {
        if (!inputName.getText().trim().isBlank()) {
            FileWriter zapis = new FileWriter("highscores.txt", true);
            BufferedWriter out = new BufferedWriter(zapis);
            out.write(inputName.getText().trim() + " (Time: " + timeData + ", grid " + widthGaps + "x" + heightGaps + ")\n");
            out.close();
            ((Stage) ((Node) (actionEvent.getSource())).getScene().getWindow()).close();
            Application.PrimaryStage.show();
        } else {
            warningName.setText("Wpisz prawidłowe imię");
            warningName.setStyle("-fx-text-fill: red;");
        }
    }
}
