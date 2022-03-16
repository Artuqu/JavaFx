package pl.zone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.zone.dto.EmployeeDto;
import pl.zone.rest.EmployeeRestClient;
import pl.zone.table.EmployeeTableModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EmployeeController implements Initializable {

    private final EmployeeRestClient employeeRestClient;

    public EmployeeController() {
        this.employeeRestClient = new EmployeeRestClient();
    }


    @FXML
    private TableView<EmployeeTableModel> employeeView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn firstName = new TableColumn("First Name");
        firstName.setMinWidth(100);
        firstName.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("firstName"));

        TableColumn lastName = new TableColumn("Last Name");
        lastName.setMinWidth(100);
        lastName.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("lastName"));

        TableColumn salary = new TableColumn("Salary");
        salary.setMinWidth(100);
        salary.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("salary"));

        employeeView.getColumns().addAll(firstName, lastName, salary);

        ObservableList<EmployeeTableModel> data = FXCollections.observableArrayList();
        loadAllEmployee(data);

        employeeView.setItems(data);
    }

    private void loadAllEmployee(ObservableList<EmployeeTableModel> data) {
        Thread thread = new Thread(() ->
        {
            List<EmployeeDto> employeesList = employeeRestClient.getEmployees();
            data.addAll(employeesList.stream()
                    .map(EmployeeTableModel::of)
                    .collect(Collectors.toList()));
        }
        );
        thread.start();
    }
}
