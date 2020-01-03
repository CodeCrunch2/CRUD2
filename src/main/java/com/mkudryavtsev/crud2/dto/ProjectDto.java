package main.java.com.mkudryavtsev.crud2.dto;

import main.java.com.mkudryavtsev.crud2.model.ProjectStatus;

import java.util.Set;

public class ProjectDto extends BaseDto {
    private Set<DeveloperDto> developerDtoSet;
    private ProjectStatus projectStatus;

    public Set<DeveloperDto> getDeveloperDtoSet() {
        return developerDtoSet;
    }

    public void setDeveloperDtoSet(Set<DeveloperDto> developerDtoSet) {
        this.developerDtoSet = developerDtoSet;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
