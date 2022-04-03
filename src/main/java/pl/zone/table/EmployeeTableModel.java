package pl.zone.table;

import javafx.beans.property.SimpleStringProperty;
import pl.zone.dto.EmployeeDto;


public class EmployeeTableModel {

    private final Long idEmployee;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty salary;

    public EmployeeTableModel(Long idEmployee, String firstName, String lastName, String salary) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.salary = new SimpleStringProperty(salary);
        this.idEmployee = idEmployee;
    }

    public static EmployeeTableModel of(EmployeeDto employeeDto) {
        return new EmployeeTableModel(employeeDto.getIdEmployee(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getSalary());
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getSalary() {
        return salary.get();
    }

    public SimpleStringProperty salaryProperty() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary.set(salary);
    }

    public Long getIdEmployee() {return idEmployee;}
}
