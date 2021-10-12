package com.inetum.realdolmen.security;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "/login.xhtml", errorPage = "", useForwardToLogin = true))
@ApplicationScoped
public class TRMIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential c = (UsernamePasswordCredential) credential;
            String userName = c.getCaller();
            if ("RDM".equals(userName)) {
                return new CredentialValidationResult("RDM", Set.of("CONSULTANT"));
            } else if ("Maarten".equals(userName)) {
                return new CredentialValidationResult("Maarten", Set.of("MANAGER"));
            } else {
                return CredentialValidationResult.INVALID_RESULT;
            }
        } else {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }
}
