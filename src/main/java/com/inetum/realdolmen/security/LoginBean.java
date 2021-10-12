package com.inetum.realdolmen.security;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Model
public class LoginBean {

    private String username;
    private String password;

    @Inject
    SecurityContext securityContext;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();

        AuthenticationStatus status = securityContext.authenticate((HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(username, password)));

        if (AuthenticationStatus.SEND_FAILURE == status) {
            System.out.println("Invalid credential");
            fc.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid credentials", "Wrong credentials..."));
        } else if (AuthenticationStatus.SUCCESS == status) {
            return "time-registration?faces-redirect=true";
        } else if (AuthenticationStatus.SEND_CONTINUE == status) {
            fc.responseComplete();
        }
        return null;
    }
}
