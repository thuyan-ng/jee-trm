package com.inetum.realdolmen.timeregistrations;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

@Model
public class TimeRegistrationBean implements Serializable {

    @EJB
    TRMService trmService;

    private List<TimeRegistration> timeRegistrations;

    public List<TimeRegistration> getTimeRegistrations() {
        return timeRegistrations;
    }

    @PostConstruct
    private void loadTimeRegistrations() {
        timeRegistrations = trmService.getAllTimeRegistrations();
    }

}
