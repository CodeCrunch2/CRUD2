package main.java.com.mkudryavtsev.crud2.view.commands.developerCommands;

import main.java.com.mkudryavtsev.crud2.view.DeveloperView;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;

public class CancelCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Операция отменена\n");
        DeveloperView.isICancelled = true;
    }
}
