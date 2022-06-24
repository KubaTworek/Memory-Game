package pl.pjatk.memorygame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AskForNameController {

    @FXML
    public Button confirmBtn;

    @FXML
    public void onConfirmBtn(ActionEvent actionEvent) {
        ((Stage)((Node)(actionEvent.getSource())).getScene().getWindow()).close();
        Application.PrimaryStage.show();
    }
}
