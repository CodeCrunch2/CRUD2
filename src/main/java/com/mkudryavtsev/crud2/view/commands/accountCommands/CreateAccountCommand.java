package main.java.com.mkudryavtsev.crud2.view.commands.accountCommands;

import main.java.com.mkudryavtsev.crud2.controller.AccountController;
import main.java.com.mkudryavtsev.crud2.dto.AccountDto;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;
import main.java.com.mkudryavtsev.crud2.view.console.ConsoleUtils;

public class CreateAccountCommand implements Command {
    private ConsoleUtils consoleUtils = ConsoleUtils.getConsoleUtils();
    private AccountController accountController = AccountController.getAccountController();
    @Override
    public void execute() {
        System.out.println("Введите имя пользователя:");
        String userName = consoleUtils.getStringFromConsole();
        if (userName.equalsIgnoreCase("cancel")) {
            System.out.println("Операция отменена");
            return;
        }
        AccountDto accountDto = accountController.createAccount(userName);
        if(accountDto.getErrorMessage() != null){
            System.out.println(accountDto.getErrorMessage());
        }

    }
}
