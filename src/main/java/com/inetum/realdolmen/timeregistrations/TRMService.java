package com.inetum.realdolmen.timeregistrations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.inetum.realdolmen.projects.Project;

import java.util.List;

@Stateless
public class TRMService {

    @PersistenceContext
    EntityManager entityManager;

    public void createProject(Project p) {
        entityManager.persist(p);
    }

    public List<Project> getAllProjects() {
        return entityManager.createQuery("select p from Project p", Project.class).getResultList();
    }

    public Project getProjectById(Long id) {
        return entityManager
                .createQuery("select p from Project p left join fetch p.registrations where p.id = ?1", Project.class)
                .setParameter(1, id).getSingleResult();
    }

    public void createTimeRegistration(TimeRegistration timeRegistration) {
        entityManager.persist(timeRegistration);
    }

    public void updateTimeLeft(Long projectId, Integer addedHours) {
        Project p = getProjectById(projectId);
        p.setRemainingHours(p.getRemainingHours() - addedHours);
        entityManager.persist(p);
    }

    public List<TimeRegistration> getAllTimeRegistrations() {
        return entityManager.createQuery("select tr from TimeRegistration tr", TimeRegistration.class).getResultList();
    }

}