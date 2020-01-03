package main.java.com.mkudryavtsev.crud2.view.commands;

import main.java.com.mkudryavtsev.crud2.view.commands.mainCommands.*;

public class MainCommandFactory implements CommandFactory {
    @Override
    public Command getCommand(String command) {
        if (command.equalsIgnoreCase("account")) {
            return new AccountCommand();
        }
        else if(command.equalsIgnoreCase("developer")) {
            return new DeveloperCommand();
        }
        else if(command.equalsIgnoreCase("project")) {
            return new ProjectCommand();
        }
        else if (command.equalsIgnoreCase("exit")) {
            return new ExitCommand();
        }

        else return new UnknownCommand();
    }
}
