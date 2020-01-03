package main.java.com.mkudryavtsev.crud2.view.commands.projectCommands;

import main.java.com.mkudryavtsev.crud2.controller.DeveloperController;
import main.java.com.mkudryavtsev.crud2.controller.ProjectController;
import main.java.com.mkudryavtsev.crud2.dto.ProjectDto;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;
import main.java.com.mkudryavtsev.crud2.view.console.ConsoleUtils;

public class DeleteDeveloperCommand implements Command {
    private ProjectController projectController = ProjectController.getProjectController();
    private DeveloperController developerController = DeveloperController.getDeveloperController();
    private ConsoleUtils consoleUtils = ConsoleUtils.getConsoleUtils();
    @Override
    public void execute() {
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
        System.out.println("Введите id разработчика:");
        String idDeveloper = consoleUtils.getStringFromConsole();
        if (idDeveloper.equalsIgnoreCase("cancel")) {
            System.out.println("Операция отменена");
            return;
        }
        try {
            Long.parseLong(idDeveloper);
        }
        catch (NumberFormatException e) {
            System.out.println("id должен быть числом");
            return;
        }
        if (!developerController.isExist(Long.parseLong(idDeveloper))) {
            System.out.println("Разработчика с таким id не существует");
            return;
        }
        ProjectDto projectDto = projectController.deleteDeveloper(idProject, idDeveloper);
        if(projectDto.getErrorMessage() != null){
            System.out.println(projectDto.getErrorMessage());
        }

    }
}
