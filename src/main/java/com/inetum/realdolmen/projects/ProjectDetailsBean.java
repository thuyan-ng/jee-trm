package com.inetum.realdolmen.projects;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.inetum.realdolmen.timeregistrations.TRMService;

@Model
public class ProjectDetailsBean {

    @EJB
    TRMService trmService;

    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void loadProjectById(Long id) {
        project = trmService.getProjectById(id);
    }

}
