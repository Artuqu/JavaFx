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
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.zone.dto.EmployeeDto;
import pl.zone.factory.PopupFactory;
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
    private final static String viewEmployeeSource = "/javafx/employee/view_employee.fxml";
    private final ObservableList<EmployeeTableModel> data;
    private final ViewEmployeeController viewEmployeeController;
    private final PopupFactory popupFactory;


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
        this.popupFactory = new PopupFactory();
        this.employeeRestClient = new EmployeeRestClient();
        this.data = FXCollections.observableArrayList();
        this.viewEmployeeController = new ViewEmployeeController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTableView();
        addEmployeeButton();
        setRefreshButton();
        setViewButton();
    }


    private void setViewButton() {
        viewButton.setOnAction(x -> {
            EmployeeTableModel employee = employeeView.getSelectionModel().getSelectedItem();
            if (employee == null) {
                return;
            } else {
                loadViewEmployee(employee);
            }

        });
    }

    private void loadViewEmployee(EmployeeTableModel employee) {
        try {
            Stage waitingPopup = popupFactory.createWaitingPopup("Loading employee data...");
            waitingPopup.show();
            Stage viewEmployeeStage = new Stage();
            viewEmployeeStage.initStyle(StageStyle.UNDECORATED);
            viewEmployeeStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewEmployeeSource));
            Scene scene = new Scene((BorderPane) loader.load(), 500, 400);
            viewEmployeeStage.setScene(scene);
            ViewEmployeeController controller = loader.<ViewEmployeeController>getController();
            controller.loadEmployeeData(employee.getIdEmployee(), () -> {
                waitingPopup.close();
                viewEmployeeStage.show();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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
