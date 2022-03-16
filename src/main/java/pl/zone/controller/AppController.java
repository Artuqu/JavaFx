package pl.zone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.zone.table.EmployeeTableModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

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
        data.add(new EmployeeTableModel("Szymon", "Żuczek", "4000"));
        data.add(new EmployeeTableModel("Marek", "Dorabek", "8000"));
        data.add(new EmployeeTableModel("Mietek", "Koza", "14000"));

        employeeView.setItems(data);
    }
}
