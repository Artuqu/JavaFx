package pl.zone.dto;

import lombok.Data;

@Data
public class OperatorAuthenticationResult {

    private Long idOperator;
    private String firstName;
    private String lastName;
    private boolean authenticated;
}
