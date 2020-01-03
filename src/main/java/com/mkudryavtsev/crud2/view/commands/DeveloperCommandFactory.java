package main.java.com.mkudryavtsev.crud2.view.commands;


import main.java.com.mkudryavtsev.crud2.view.commands.developerCommands.*;
import main.java.com.mkudryavtsev.crud2.view.commands.mainCommands.UnknownCommand;

public class DeveloperCommandFactory implements CommandFactory {

    @Override
    public Command getCommand(String command) {
        if (command.equalsIgnoreCase("create developer")) {
            return new CreateDeveloperCommand();
        }
        else if (command.equalsIgnoreCase("show developers")) {
            return new ShowDevelopersCommand();
        }
        else if (command.equalsIgnoreCase("set account")) {
            return new SetAccountCommand();
        }
        else if (command.equalsIgnoreCase("delete developer")) {
            return new DeleteDeveloperCommand();
        }
        else if (command.equalsIgnoreCase("cancel")) {
            return new CancelCommand();
        }
        else return new UnknownCommand();
    }
}
