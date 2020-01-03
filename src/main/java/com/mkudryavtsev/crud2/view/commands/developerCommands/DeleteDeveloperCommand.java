package main.java.com.mkudryavtsev.crud2.view.commands.developerCommands;

import main.java.com.mkudryavtsev.crud2.controller.DeveloperController;
import main.java.com.mkudryavtsev.crud2.dto.DeveloperDto;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;
import main.java.com.mkudryavtsev.crud2.view.console.ConsoleUtils;

public class DeleteDeveloperCommand implements Command {
    private DeveloperController developerController = DeveloperController.getDeveloperController();
    private ConsoleUtils consoleUtils = ConsoleUtils.getConsoleUtils();
    @Override
    public void execute() {
        System.out.println("Введите id разработчика:");
        String idString = consoleUtils.getStringFromConsole();
        if (idString.equalsIgnoreCase("cancel")) {
            System.out.println("Операция отменена");
            return;
        }
        try {
            Long.parseLong(idString);
        }
        catch (NumberFormatException e) {
            System.out.println("id должен быть числом");
            return;
        }
        if (!developerController.isExist(Long.parseLong(idString))) {
            System.out.println("Разработчика с таким id не существует");
            return;
        }
        DeveloperDto developerDto = developerController.deleteDeveloper(idString);
        if(developerDto.getErrorMessage() != null){
            System.out.println(developerDto.getErrorMessage());
        }
    }
}
