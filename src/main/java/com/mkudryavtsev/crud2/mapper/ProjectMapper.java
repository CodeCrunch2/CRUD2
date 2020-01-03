package main.java.com.mkudryavtsev.crud2.mapper;

import main.java.com.mkudryavtsev.crud2.dto.DeveloperDto;
import main.java.com.mkudryavtsev.crud2.dto.ProjectDto;
import main.java.com.mkudryavtsev.crud2.model.Developer;
import main.java.com.mkudryavtsev.crud2.model.Project;

import java.util.HashSet;
import java.util.Set;

public class ProjectMapper {
    private DeveloperMapper developerMapper = new DeveloperMapper();
    public ProjectDto projectToProjectDto(Project project) {
        if (project == null) {
            return null;
        }
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        if (project.getProjectStatus() != null) {
            projectDto.setProjectStatus(project.getProjectStatus());
        }
        if (project.getDevelopers() != null) {
            Set<DeveloperDto> developerDtoSet = new HashSet<>();
            for (Developer developer: project.getDevelopers()) {
                developerDtoSet.add(developerMapper.developerToDto(developer));
            }
            projectDto.setDeveloperDtoSet(developerDtoSet);
        }
        return projectDto;
    }

    public ProjectDto exceptionMessageToProjectDto(String message) {
        if(message == null){
            return null;
        }
        ProjectDto projectDto = new ProjectDto();
        projectDto.setErrorMessage(message);
        return projectDto;
    }
}
