package pl.zone.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.zone.dto.EmployeeDto;
import pl.zone.factory.PopupFactory;
import pl.zone.rest.EmployeeRestClient;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    private final PopupFactory popupFactory;
    private final EmployeeRestClient employeeRestClient;

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

    public AddEmployeeController() {
        popupFactory = new PopupFactory();
        employeeRestClient = new EmployeeRestClient();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancelButtonInit();
        saveButtonInit();
    }

    private void saveButtonInit() {
        saveButton.setOnAction(x -> {
            EmployeeDto employeeDto = createEmployeeDto();
            Stage waitingPopup = popupFactory.createWaitingPopup("Connecting to the server...");
            waitingPopup.show();
            employeeRestClient.saveEmployee(employeeDto, () -> {
                waitingPopup.close();
                Stage infoPopup = popupFactory.createInfoPopup("Employee has been saved", () -> {
                    getAddEmployeeStage().close();
                });
                infoPopup.show();
            });

        });
    }

    private EmployeeDto createEmployeeDto() {
        EmployeeDto dto = new EmployeeDto();
        dto.setFirstName(firstNameTextField.getText());
        dto.setLastName(lastNameTextField.getText());
        dto.setSalary(salaryTextField.getText());
        return dto;
    }

    private void cancelButtonInit() {
        cancelButton.setOnAction(x -> getAddEmployeeStage().close()
        );
    }



}
