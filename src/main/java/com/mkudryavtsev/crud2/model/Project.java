package main.java.com.mkudryavtsev.crud2.model;

import java.util.Set;

public class Project extends BaseEntity {
    private Set<Developer> developers;
    private ProjectStatus projectStatus;

    public Project(Set<Developer> developers, ProjectStatus projectStatus) {
        this.developers = developers;
        this.projectStatus = projectStatus;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
