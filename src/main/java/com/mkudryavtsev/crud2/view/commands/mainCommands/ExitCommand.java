package main.java.com.mkudryavtsev.crud2.view.commands.mainCommands;

import main.java.com.mkudryavtsev.crud2.view.MainView;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        MainView.isInterrupted = true;
    }
}
