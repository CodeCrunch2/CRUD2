package main.java.com.mkudryavtsev.crud2.view.commands.projectCommands;

import main.java.com.mkudryavtsev.crud2.controller.ProjectController;
import main.java.com.mkudryavtsev.crud2.dto.ProjectDto;
import main.java.com.mkudryavtsev.crud2.model.ProjectStatus;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;
import main.java.com.mkudryavtsev.crud2.view.console.ConsoleUtils;

public class SetStatusCommand implements Command {
    private ProjectController projectController = ProjectController.getProjectController();
    private ConsoleUtils consoleUtils = ConsoleUtils.getConsoleUtils();

    @Override
    public void execute() {
        ProjectStatus projectStatus;
        System.out.println("Введите id проекта:");
        String idProject = consoleUtils.getStringFromConsole();
        if (idProject.equalsIgnoreCase("cancel")) {
            System.out.println("Операция отменена");
            return;
        }
        try {
            Long.parseLong(idProject);
        }
        catch (NumberFormatException e) {
            System.out.println("id должен быть числом");
            return;
        }
        if (!projectController.isExist(Long.parseLong(idProject))) {
            System.out.println("Проекта с таким id не существует");
            return;
        }
        System.out.println("Введите статус проекта из представленных: ACTIVE, FINISHED, DELETED");
        String statusString = consoleUtils.getStringFromConsole();
        try {
            projectStatus = ProjectStatus.valueOf(statusString);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Введен неправильный статус проекта");
            return;
        }
        ProjectDto projectDto = projectController.setStatus(idProject, projectStatus);
        if(projectDto.getErrorMessage() != null){
            System.out.println(projectDto.getErrorMessage());
        }

    }
}
