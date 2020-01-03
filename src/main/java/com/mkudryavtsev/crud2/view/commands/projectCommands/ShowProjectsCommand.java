package main.java.com.mkudryavtsev.crud2.view.commands.projectCommands;

import main.java.com.mkudryavtsev.crud2.controller.ProjectController;
import main.java.com.mkudryavtsev.crud2.dto.ProjectDto;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;

import java.util.List;

public class ShowProjectsCommand implements Command {
    private ProjectController projectController = ProjectController.getProjectController();
    @Override
    public void execute() {
        List<ProjectDto> projectDtoList = projectController.showProjects();
        if (projectDtoList.isEmpty()) {
            System.out.println("Список проектов пуст");
            return;
        }
        projectDtoList.forEach(p -> {
            System.out.print("id: " + p.getId());
            System.out.print(" Developers id: ");
            if (p.getDeveloperDtoSet() == null) {
                System.out.print("null ");
            }
            else {
                p.getDeveloperDtoSet().forEach(d -> System.out.print(d.getId() + ", "));
            }
            System.out.print("Status: ");
            if (p.getProjectStatus() == null) {
                System.out.println("null");
            }
            else {
                System.out.println(p.getProjectStatus());
            }
        });
    }
}
