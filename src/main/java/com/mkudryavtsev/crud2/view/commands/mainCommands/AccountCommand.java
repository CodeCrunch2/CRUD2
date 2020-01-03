package main.java.com.mkudryavtsev.crud2.view.commands.mainCommands;


import main.java.com.mkudryavtsev.crud2.view.AccountView;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;

public class AccountCommand implements Command {
    private AccountView accountView = AccountView.getAccountView();
    @Override
    public void execute() {
        accountView.accountView();
    }
}
