package main.java.com.mkudryavtsev.crud2.view.commands.accountCommands;

import main.java.com.mkudryavtsev.crud2.controller.AccountController;
import main.java.com.mkudryavtsev.crud2.dto.AccountDto;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;

import java.util.List;

public class ShowAccountsCommand implements Command {
    private AccountController accountController = AccountController.getAccountController();
    @Override
    public void execute() {
        List<AccountDto> accountDtoList = accountController.showAccounts();
        if (accountDtoList == null) {
            System.out.println("Список аккаунтов пуст");
            return;
        }
        accountDtoList.forEach(a -> System.out.println("id: " + a.getId() + " username: " + a.getUserName()));

    }
}
