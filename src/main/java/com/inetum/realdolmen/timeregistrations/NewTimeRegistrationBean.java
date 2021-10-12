package com.inetum.realdolmen.timeregistrations;

import java.security.Principal;
import java.time.ZoneId;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.inetum.realdolmen.projects.Project;

@Model
public class NewTimeRegistrationBean {

    @EJB
    TRMService trmService;

    @Inject
    TimeRegistrationBean timeRegistrationBean;

    @Inject
    private Principal principal;

    @NotNull
    private Long projectId;

    @Positive
    @Max(8)
    private Integer hours;

    @NotNull
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public boolean doesNotExceedRemainingHours() {
        Project p = trmService.getProjectById(projectId);
        return p.getRemainingHours() - hours >= 0;
    }

    public void create() {
        if (doesNotExceedRemainingHours()) {
            TimeRegistration timeRegistration = new TimeRegistration();
            timeRegistration.setConsultant(principal.getName());
            timeRegistration.setHours(hours);
            timeRegistration.setDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            Project project = trmService.getProjectById(projectId);
            timeRegistration.setProject(project);

            timeRegistrationBean.getTimeRegistrations().add(timeRegistration);
            trmService.createTimeRegistration(timeRegistration);
            trmService.updateTimeLeft(projectId, timeRegistration.getHours());
        } else {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Hours exceed remaining hours for this project", "TR failed"));
        }
    }

}
