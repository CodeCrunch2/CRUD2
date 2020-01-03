package main.java.com.mkudryavtsev.crud2.view.commands.developerCommands;

import main.java.com.mkudryavtsev.crud2.controller.AccountController;
import main.java.com.mkudryavtsev.crud2.controller.DeveloperController;
import main.java.com.mkudryavtsev.crud2.dto.DeveloperDto;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;
import main.java.com.mkudryavtsev.crud2.view.console.ConsoleUtils;

public class SetAccountCommand implements Command {
    private DeveloperController developerController = DeveloperController.getDeveloperController();
    private AccountController accountController = AccountController.getAccountController();
    private ConsoleUtils consoleUtils = ConsoleUtils.getConsoleUtils();
    @Override
    public void execute() {
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

        System.out.println("Введите id аккаунта:");
        String idAccount = consoleUtils.getStringFromConsole();
        if (idAccount.equalsIgnoreCase("cancel")) {
            System.out.println("Операция отменена");
            return;
        }
        try {
            Long.parseLong(idAccount);
        }
        catch (NumberFormatException e) {
            System.out.println("id должен быть числом");
            return;
        }
        if (!accountController.isExist(Long.parseLong(idAccount))) {
            System.out.println("Аккаунта с таким id не существует");
            return;
        }
        DeveloperDto developerDto = developerController.setAccount(idDeveloper, idAccount);
        if(developerDto.getErrorMessage() != null){
            System.out.println(developerDto.getErrorMessage());
        }

    }
}
