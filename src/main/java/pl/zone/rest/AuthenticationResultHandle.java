package pl.zone.rest;

import pl.zone.dto.OperatorAuthenticationResult;

@FunctionalInterface
public interface AuthenticationResultHandle {

void handle (OperatorAuthenticationResult operatorAuthenticationResult);
}
