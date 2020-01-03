package main.java.com.mkudryavtsev.crud2.view.commands;

import main.java.com.mkudryavtsev.crud2.view.commands.accountCommands.*;
import main.java.com.mkudryavtsev.crud2.view.commands.mainCommands.UnknownCommand;

public class AccountCommandFactory implements CommandFactory {
    @Override
    public Command getCommand(String command) {
        if (command.equalsIgnoreCase("create account")) {
            return new CreateAccountCommand();
        }
        else if (command.equalsIgnoreCase("show accounts")) {
            return new ShowAccountsCommand();
        }
        else if (command.equalsIgnoreCase("change username")) {
            return new ChangeUsernameCommand();
        }
        else if (command.equalsIgnoreCase("delete account")) {
            return new DeleteAccountCommand();
        }
        else if (command.equalsIgnoreCase("cancel")) {
            return new CancelCommand();
        }
        else return new UnknownCommand();
    }
}
