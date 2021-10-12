package com.inetum.realdolmen.security;

import javax.enterprise.inject.Model;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Model
public class LogoutBean {

    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        request.getSession().invalidate();
        return "/login.xhtml?faces-redirect=true";
    }
}
