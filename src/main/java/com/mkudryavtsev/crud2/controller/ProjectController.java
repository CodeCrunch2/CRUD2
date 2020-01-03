package main.java.com.mkudryavtsev.crud2.controller;

import main.java.com.mkudryavtsev.crud2.dto.ProjectDto;
import main.java.com.mkudryavtsev.crud2.mapper.ProjectMapper;
import main.java.com.mkudryavtsev.crud2.model.Developer;
import main.java.com.mkudryavtsev.crud2.model.Project;
import main.java.com.mkudryavtsev.crud2.model.ProjectStatus;
import main.java.com.mkudryavtsev.crud2.repository.csv.CsvDeveloperRepositoryImpl;
import main.java.com.mkudryavtsev.crud2.repository.csv.CsvProjectRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectController {
    private static ProjectController projectController;
    private CsvProjectRepository csvProjectRepository = new CsvProjectRepository();
    private CsvDeveloperRepositoryImpl csvDeveloperRepository = new CsvDeveloperRepositoryImpl();
    private ProjectMapper projectMapper = new ProjectMapper();
    private ProjectController(){

    }
    public static ProjectController getProjectController() {
        if (projectController == null) {
            projectController = new ProjectController();
        }
        return projectController;
    }
    public ProjectDto createProject(){
        Project project = new Project(null,null);
        project.setId(csvProjectRepository.getNextID());
        csvProjectRepository.save(project);
        return projectMapper.projectToProjectDto(project);
    }
    public List<ProjectDto> showProjects() {
        List<ProjectDto> projectDtoList;
        List<Project> projectList = csvProjectRepository.getAll();
        if (projectList.isEmpty()) {
            return null;
        }
        projectDtoList = projectList.stream().map(p -> projectMapper.projectToProjectDto(p)).collect(Collectors.toList());
        return projectDtoList;
    }
    public boolean isExist(Long id) {
        List<Project> projectList = csvProjectRepository.getAll();
        if (!projectList.isEmpty()) {
            for (Project project : projectList) {
                if (new Long(project.getId()).equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }
    public ProjectDto addDeveloper(String idProject, String idDeveloper){
        Project project = csvProjectRepository.getById(Long.parseLong(idProject));
        Developer developer = csvDeveloperRepository.getById(Long.parseLong(idDeveloper));
        Set<Developer> developerSet = project.getDevelopers();
        ProjectDto projectDto;
        if (developerSet == null) {
            developerSet = new HashSet<>();
        }
        else {
            for (Developer d: developerSet) {
                if (d.getId() == developer.getId()) {
                    projectDto = projectMapper.exceptionMessageToProjectDto("Разработчик с таким id уже состоит в проекте");
                    return projectDto;
                }
            }
        }
        developerSet.add(developer);
        project.setDevelopers(developerSet);
        csvProjectRepository.update(project);
        projectDto = projectMapper.projectToProjectDto(project);
        return projectDto;

    }
    public ProjectDto deleteDeveloper(String idProject, String idDeveloper) {
        Project project = csvProjectRepository.getById(Long.parseLong(idProject));
        Developer developer = csvDeveloperRepository.getById(Long.parseLong(idDeveloper));
        Set<Developer> developerSet = project.getDevelopers();
        Set<Developer> newDeveloperSet = new HashSet<>();
        ProjectDto projectDto;
        if (developerSet == null) {
            projectDto = projectMapper.exceptionMessageToProjectDto("Разработчик с таким id не состоит в проекте");
            return projectDto;
        }
        int count = 0;
        for (Developer d: developerSet) {
            if(d.getId() == developer.getId()) count++;
            else newDeveloperSet.add(d);
        }
        if(count < 1) {
            projectDto = projectMapper.exceptionMessageToProjectDto("Разработчик с таким id не состоит в проекте");
            return projectDto;
        }
        project.setDevelopers(newDeveloperSet);
        csvProjectRepository.update(project);
        projectDto = projectMapper.projectToProjectDto(project);
        return projectDto;
    }

    public ProjectDto setStatus(String idProject, ProjectStatus projectStatus) {
        Project project = csvProjectRepository.getById(Long.parseLong(idProject));
        project.setProjectStatus(projectStatus);
        csvProjectRepository.update(project);
        return projectMapper.projectToProjectDto(project);
    }

}
