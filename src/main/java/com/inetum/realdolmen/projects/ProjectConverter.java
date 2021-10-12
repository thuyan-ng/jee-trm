package com.inetum.realdolmen.projects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.inetum.realdolmen.timeregistrations.TRMService;

@FacesConverter(forClass = Project.class, managed = true)
public class ProjectConverter implements Converter<Project> {

    @Inject
    TRMService trmService;

    @Override
    public Project getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            long id = Long.parseLong(value);
            return trmService.getProjectById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Project value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(value.getId());
    }
}