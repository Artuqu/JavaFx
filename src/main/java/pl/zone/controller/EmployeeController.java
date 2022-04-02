package pl.zone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.zone.dto.EmployeeDto;
import pl.zone.rest.EmployeeRestClient;
import pl.zone.table.EmployeeTableModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EmployeeController implements Initializable {

    private final EmployeeRestClient employeeRestClient;
    private final static String addEmployeeSource = "/javafx/employee/add_employee.fxml";
    private ObservableList<EmployeeTableModel> data;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button refreshButton;


    @FXML
    private Button editButton;

    @FXML
    private Button viewButton;

    @FXML
    private TableView<EmployeeTableModel> employeeView;

    public EmployeeController() {
        this.employeeRestClient = new EmployeeRestClient();
        this.data = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTableView();
        addEmployeeButton();
        setRefreshButton();
    }

    private void addEmployeeButton() {
        addButton.setOnAction(x -> {
            Stage addEmployee = new Stage();
            addEmployee.initStyle(StageStyle.UNDECORATED);
            addEmployee.initModality(Modality.APPLICATION_MODAL);
            try {
                Parent parentEmployee = FXMLLoader.load(getClass().getResource(addEmployeeSource));
                Scene scene = new Scene(parentEmployee, 500, 400);
                addEmployee.setScene(scene);
                addEmployee.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
    }

    private void createTableView() {
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


        loadAllEmployee();

        employeeView.setItems(data);
    }

    private void loadAllEmployee() {
        Thread thread = new Thread(() ->
        {
            List<EmployeeDto> employeesList = employeeRestClient.getEmployees();
            data.clear();
            data.addAll(employeesList.stream()
                    .map(EmployeeTableModel::of)
                    .collect(Collectors.toList()));
        }
        );
        thread.start();
    }


    private void setRefreshButton() {
        refreshButton.setOnAction(x -> loadAllEmployee());
    }
}
