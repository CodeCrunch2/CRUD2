package main.java.com.mkudryavtsev.crud2.view.commands.accountCommands;

import main.java.com.mkudryavtsev.crud2.view.AccountView;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;

public class CancelCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Операция отменена\n");
        AccountView.isICancelled = true;
    }
}
