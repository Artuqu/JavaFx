package pl.zone.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.zone.dto.EmployeeDto;
import pl.zone.handler.SaveEmployeeHandler;

import java.util.Arrays;
import java.util.List;

public class EmployeeRestClient {

    private final String employeeUrl = "http://localhost:8080/employees";

    private final RestTemplate restTemplate;

    public EmployeeRestClient() {
        this.restTemplate = new RestTemplate();
    }

    public List<EmployeeDto> getEmployees() {
        ResponseEntity<EmployeeDto[]> employees = restTemplate.getForEntity(employeeUrl, EmployeeDto[].class);
        return Arrays.asList(employees.getBody());
    }

    public void saveEmployee(EmployeeDto dto, SaveEmployeeHandler handler) {
        ResponseEntity<EmployeeDto> employeeDtoResponseEntity = restTemplate.postForEntity(employeeUrl, dto, EmployeeDto.class);
        if (HttpStatus.OK.equals(employeeDtoResponseEntity.getStatusCode())) {
            handler.handler();
        } else {
//            TODO implement
        }
    }

    public EmployeeDto getEmployee(Long idEmployee) {
        String url = employeeUrl + "/" + idEmployee;
        ResponseEntity<EmployeeDto> responseEntity = restTemplate.getForEntity(url, EmployeeDto.class);
        if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
            return responseEntity.getBody();
        }else {
//            TODO implementation
            throw new RuntimeException("Can't load employee");
        }
    }
}
