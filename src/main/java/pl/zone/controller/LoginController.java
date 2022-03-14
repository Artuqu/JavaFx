package pl.zone.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.zone.dto.OperatorCredentials;
import pl.zone.factory.PopupFactory;
import pl.zone.rest.AuthenticatorImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Stage getStage() {
        return (Stage) loginAnchorPane.getScene().getWindow();
    }

    private static final String appFXML = "/javafx/app.fxml";
    private static final String appTitle = "ERP Application";

    private final PopupFactory popupFactory;

    private final AuthenticatorImpl authenticator;

    public LoginController() {
        popupFactory = new PopupFactory();
        authenticator = new AuthenticatorImpl();
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
        OperatorCredentials dto = new OperatorCredentials(login, password);
//        dto.setLogin(login);
//        dto.setPassword(password);
        authenticator.authenticate(dto, authenticationResult -> Platform.runLater(() -> {
            System.out.println(authenticationResult.isAuthenticated() + " " + authenticationResult);
            waitingPopup.close();
            if (authenticationResult.isAuthenticated()) {
                openAppAndCloseLoginStage();
            } else {
                incorrectCredentials();
            }
        }));
        System.out.println("Your login: " + login + ", password: " + password);
    }

    private void incorrectCredentials() {
        //TODO
        System.out.println("Incorrect credentials");
    }

    private void openAppAndCloseLoginStage(){
        Stage appStage = new Stage();
        Parent appRoot = null;
        try {
           appRoot = FXMLLoader.load(getClass().getResource(appFXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(appRoot, 1024, 800);
        appStage.setTitle(appTitle);
        appStage.setScene(scene);
        appStage.show();
        getStage().close();
    }

    private void exitButtonInitialize() {
        exitButton.setOnAction(x -> getStage().close());
    }
}
