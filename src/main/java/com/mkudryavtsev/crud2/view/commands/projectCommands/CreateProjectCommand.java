package main.java.com.mkudryavtsev.crud2.view.commands.projectCommands;

import main.java.com.mkudryavtsev.crud2.controller.ProjectController;
import main.java.com.mkudryavtsev.crud2.dto.ProjectDto;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;

public class CreateProjectCommand implements Command {
    private ProjectController projectController = ProjectController.getProjectController();
    @Override
    public void execute() {
        ProjectDto projectDto = projectController.createProject();
        if(projectDto.getErrorMessage() != null){
            System.out.println(projectDto.getErrorMessage());
        }
    }
}
