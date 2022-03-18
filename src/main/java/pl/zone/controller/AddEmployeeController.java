package pl.zone.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {


    @FXML
    private Button cancelButton;

    @FXML
    private BorderPane addEmployeeBorder;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField salaryTextField;

    @FXML
    private Button saveButton;


    private Stage getAddEmployeeStage() {
        return (Stage) addEmployeeBorder.getScene().getWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancelButtonInit();
    }

    private void cancelButtonInit() {
        cancelButton.setOnAction(x -> getAddEmployeeStage().close()
        );
    }


}
