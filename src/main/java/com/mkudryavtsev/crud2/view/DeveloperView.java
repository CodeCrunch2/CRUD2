package main.java.com.mkudryavtsev.crud2.view;

import main.java.com.mkudryavtsev.crud2.view.commands.CommandFactory;
import main.java.com.mkudryavtsev.crud2.view.commands.DeveloperCommandFactory;
import main.java.com.mkudryavtsev.crud2.view.console.ConsoleUtils;

public class DeveloperView {
    private static DeveloperView developerView;
    public static boolean isICancelled = false;
    private CommandFactory developerCommandFactory = new DeveloperCommandFactory();
    private ConsoleUtils consoleUtils = ConsoleUtils.getConsoleUtils();

    private DeveloperView() {

    }
    public static DeveloperView getDeveloperView() {
        if (developerView == null) {
            developerView = new DeveloperView();
        }
        return developerView;
    }
    public void developerView() {
        isICancelled = false;
        while (!isICancelled) {
            System.out.println("Выберите одно из доступных действий:");
            System.out.println("create developer, show developers, set account, delete developer");
            developerCommandFactory.getCommand(consoleUtils.getStringFromConsole()).execute();
        }
    }
}
