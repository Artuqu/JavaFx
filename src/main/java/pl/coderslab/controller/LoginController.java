package pl.coderslab.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button onExitButtonClick;

    @FXML
    private VBox onLoginButtonClick;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onExitButtonClick.setOnAction(x -> System.out.println("Works"));
    }
}
