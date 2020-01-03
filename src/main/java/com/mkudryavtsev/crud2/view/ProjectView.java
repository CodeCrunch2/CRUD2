package main.java.com.mkudryavtsev.crud2.view;

import main.java.com.mkudryavtsev.crud2.view.commands.CommandFactory;
import main.java.com.mkudryavtsev.crud2.view.commands.ProjectCommandFactory;
import main.java.com.mkudryavtsev.crud2.view.console.ConsoleUtils;

public class ProjectView {
    private static ProjectView projectView;
    public static boolean isICancelled = false;
    private CommandFactory projectCommandFactory = new ProjectCommandFactory();
    private ConsoleUtils consoleUtils = ConsoleUtils.getConsoleUtils();
    private ProjectView() {

    }
    public static ProjectView getProjectView() {
        if (projectView == null) {
            projectView = new ProjectView();
        }
        return projectView;
    }
    public void projectView() {
        isICancelled = false;
        while (!isICancelled) {
            System.out.println("Выберите одно из доступных действий:");
            System.out.println("create project, show projects, add developer, delete developer, set status");
            projectCommandFactory.getCommand(consoleUtils.getStringFromConsole()).execute();
        }
    }
}
