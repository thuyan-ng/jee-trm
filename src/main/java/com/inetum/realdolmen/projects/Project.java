package com.inetum.realdolmen.projects;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.inetum.realdolmen.timeregistrations.TimeRegistration;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @Positive
    private Integer hours;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TimeRegistration> registrations = new HashSet<>();
    @Version
    private Long version = 0L;
    @Min(0)
    private Integer remainingHours;

    // Constructors
    public Project(int hours) {
        this.setHours(hours);
    }

    public Project() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getHours() {
        return this.hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Set<TimeRegistration> getRegistrations() {
        return this.registrations;
    }

    public void setRegistrations(Set<TimeRegistration> registrations) {
        this.registrations = registrations;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getRemainingHours() {
        if (remainingHours == null)
            remainingHours = hours;
        return this.remainingHours;
    }

    public void setRemainingHours(Integer remainingHours) {
        this.remainingHours = remainingHours;
    }

}
