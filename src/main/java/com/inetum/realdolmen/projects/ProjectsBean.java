package com.inetum.realdolmen.projects;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.inetum.realdolmen.timeregistrations.TRMService;

@Model
public class ProjectsBean implements Serializable {

    @EJB
    TRMService trmService;

    private List<Project> projects;

    @PostConstruct
    private void loadProjects() {
        projects = trmService.getAllProjects();
    }

    public List<Project> getProjects() {
        loadProjects();
        return projects;
    }

}
