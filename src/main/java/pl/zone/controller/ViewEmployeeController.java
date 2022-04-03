package pl.zone.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.zone.dto.EmployeeDto;
import pl.zone.handler.EmployeeLoadedHandler;
import pl.zone.rest.EmployeeRestClient;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewEmployeeController implements Initializable {

    private final EmployeeRestClient employeeRestClient;

    @FXML
    private Button closeButton;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField salaryTextField;

    @FXML
    private BorderPane viewEmployeeBorder;

    public ViewEmployeeController() {
        employeeRestClient = new EmployeeRestClient();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancelButtonInit();
//        loadEmployeeData();
        firstNameTextField.setEditable(false);
        lastNameTextField.setEditable(false);
        salaryTextField.setEditable(false);
    }

    public void loadEmployeeData(Long idEmployee, EmployeeLoadedHandler handler) {
        Thread thread = new Thread(() -> {
            EmployeeDto dto = employeeRestClient.getEmployee(idEmployee);
            Platform.runLater(() -> {
                firstNameTextField.setText(dto.getFirstName());
                lastNameTextField.setText(dto.getLastName());
                salaryTextField.setText(dto.getSalary());
                handler.handle();
            });

        });
        thread.start();
    }


    private void cancelButtonInit() {
        closeButton.setOnAction(x -> getStage().close());
    }

    private Stage getStage() {
        return (Stage) viewEmployeeBorder.getScene().getWindow();
    }
}
