package main.java.com.mkudryavtsev.crud2.view.commands.mainCommands;

import main.java.com.mkudryavtsev.crud2.view.DeveloperView;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;

public class DeveloperCommand implements Command {
    private DeveloperView developerView = DeveloperView.getDeveloperView();
    @Override
    public void execute() {
        developerView.developerView();
    }
}
