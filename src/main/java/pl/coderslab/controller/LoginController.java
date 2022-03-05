package pl.coderslab.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.coderslab.factory.PopupFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Stage getStage() {
        return (Stage) loginAnchorPane.getScene().getWindow();
    }

    private PopupFactory popupFactory;

    public LoginController() {
        popupFactory = new PopupFactory();
    }

    @FXML
    private Button exitButton;
    @FXML
    private Button loginButton;
    @FXML
    private AnchorPane loginAnchorPane;
    @FXML
    private TextField loginTextField;
    @FXML
    private TextField passwordTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exitButtonInitialize();
        loginButtonInitialize();
    }

    private void loginButtonInitialize() {
        loginButton.setOnAction(x -> performAuthentication());
    }

    private void performAuthentication() {
        Stage waitingPopup = popupFactory.createWaitingPopup("Connecting to server...");
        waitingPopup.show();
        String login = loginTextField.getText();
        String password = passwordTextField.getText();

        System.out.println("Your login: " + login + ", password: " + password);
    }

    private void exitButtonInitialize() {
        exitButton.setOnAction(x -> getStage().close());
    }
}
