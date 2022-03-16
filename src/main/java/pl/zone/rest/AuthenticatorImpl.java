package pl.zone.rest;

import org.springframework.web.client.RestTemplate;
import pl.zone.dto.OperatorAuthenticationResult;
import pl.zone.dto.OperatorCredentials;


public class AuthenticatorImpl implements Authenticator {

    private RestTemplate restTemplate;
    private static final String Authentication_Url = "http://localhost:8080/verify";

    public AuthenticatorImpl(){
        this.restTemplate = new RestTemplate();
    }


    @Override
    public void authenticate(OperatorCredentials operatorCredentials, AuthenticationResultHandle authenticationResultHandle) {
        Runnable authenticationTask = () -> authenticationProcess(operatorCredentials, authenticationResultHandle);

        Thread authenticationThread = new Thread(authenticationTask);
        authenticationThread.setDaemon(true);
        authenticationThread.start();

    }

    private void authenticationProcess(OperatorCredentials operatorCredentials, AuthenticationResultHandle authenticationResultHandle) {
//        ResponseEntity<OperatorAuthenticationResult> operatorAuthentication = restTemplate.postForEntity(Authentication_Url, operatorCredentials, OperatorAuthenticationResult.class);
        OperatorAuthenticationResult oar = new OperatorAuthenticationResult();
        oar.setAuthenticated(true);
        oar.setFirstName("Michałek");
        oar.setLastName("Szczur");
        oar.setIdOperator(1L);
//        authenticationResultHandle.handle(operatorAuthentication.getBody());
        authenticationResultHandle.handle(oar);

    }

}
