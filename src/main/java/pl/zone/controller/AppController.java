package pl.zone.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    public AppController() {
    }


    @FXML
    private Pane appPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadView();
    }

    private void loadView() {
        try {
            BorderPane borderPane = FXMLLoader.load(getClass().getResource("/javafx/employee/employee.fxml"));
            appPane.getChildren().add(borderPane);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e + " Can't find a view");
        }

    }
}
