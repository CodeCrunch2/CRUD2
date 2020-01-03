package main.java.com.mkudryavtsev.crud2.view.commands.projectCommands;

import main.java.com.mkudryavtsev.crud2.view.ProjectView;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;

public class CancelCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Операция отменена\n");
        ProjectView.isICancelled = true;
    }
}
