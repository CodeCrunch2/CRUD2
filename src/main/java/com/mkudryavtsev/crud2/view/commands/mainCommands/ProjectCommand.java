package main.java.com.mkudryavtsev.crud2.view.commands.mainCommands;


import main.java.com.mkudryavtsev.crud2.view.ProjectView;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;

public class ProjectCommand implements Command {
    private ProjectView projectView = ProjectView.getProjectView();
    @Override
    public void execute() {
        projectView.projectView();
    }
}
