package pl.zone.rest;

import pl.zone.dto.OperatorCredentials;

public interface Authenticator {

    void authenticate(OperatorCredentials operatorCredentials, AuthenticationResultHandle authenticationResultHandle);

}
