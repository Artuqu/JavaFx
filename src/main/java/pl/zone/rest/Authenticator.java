package pl.zone.rest;

import pl.zone.dto.OperatorCredentials;
import pl.zone.handler.AuthenticationResultHandle;

public interface Authenticator {

    void authenticate(OperatorCredentials operatorCredentials, AuthenticationResultHandle authenticationResultHandle);

}
