package pl.pjatk.memorygame.Model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Card {
    private Button button;
    private String imgSource;

    public Card(Button button, String imgSource) {
        this.button = button;
        this.imgSource = imgSource;
        this.button.setPrefHeight(125);
        this.button.setPrefWidth(125);
        this.button.setId(imgSource);
        Image cardGraphic = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/karta.png")));
        this.button.setGraphic(new ImageView(cardGraphic));
        this.button.setStyle("-fx-cursor: hand");
    }

    public Button getButton() {
        return button;
    }
}
