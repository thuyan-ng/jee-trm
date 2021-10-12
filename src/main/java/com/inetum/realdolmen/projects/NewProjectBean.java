package com.inetum.realdolmen.projects;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.inetum.realdolmen.timeregistrations.TRMService;

@Model
public class NewProjectBean {

    @NotNull
    @Positive
    private Integer hours;

    @NotBlank
    private String title;

    @EJB
    TRMService trmService;

    @Inject
    ProjectsBean projectsBean;

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void createProject() {
        Project p = new Project(hours);
        p.setTitle(title);
        trmService.createProject(p);
        projectsBean.getProjects().add(p);
    }

}
