package main.java.com.mkudryavtsev.crud2.view.commands;

import main.java.com.mkudryavtsev.crud2.view.commands.mainCommands.UnknownCommand;
import main.java.com.mkudryavtsev.crud2.view.commands.projectCommands.*;

public class ProjectCommandFactory implements CommandFactory {
    @Override
    public Command getCommand(String command) {
        if (command.equalsIgnoreCase("create project")) {
            return new CreateProjectCommand();
        }
        else if (command.equalsIgnoreCase("show projects")) {
            return new ShowProjectsCommand();
        }
        else if (command.equalsIgnoreCase("add developer")) {
            return new AddDeveloperCommand();
        }
        else if (command.equalsIgnoreCase("delete developer")) {
            return new DeleteDeveloperCommand();
        }
        else if (command.equalsIgnoreCase("set status")) {
            return new SetStatusCommand();
        }
        else if (command.equalsIgnoreCase("cancel")) {
            return new CancelCommand();
        }
        else return new UnknownCommand();
    }
}
