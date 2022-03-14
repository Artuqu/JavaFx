package pl.zone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperatorCredentials {

    private String login;
    private String password;


}
