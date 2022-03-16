package pl.zone.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.zone.dto.EmployeeDto;

import java.util.Arrays;
import java.util.List;

public class EmployeeRestClient {

    private final String employeeUrl = "http://localhost:8080/employees";

    private final RestTemplate restTemplate;

    public EmployeeRestClient(){
        this.restTemplate = new RestTemplate();
    }

    public List<EmployeeDto> getEmployees(){
        ResponseEntity<EmployeeDto[]> employees = restTemplate.getForEntity(employeeUrl, EmployeeDto[].class);
        return Arrays.asList(employees.getBody());
    }
}
