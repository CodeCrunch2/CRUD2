package main.java.com.mkudryavtsev.crud2.view;

import main.java.com.mkudryavtsev.crud2.view.commands.CommandFactory;
import main.java.com.mkudryavtsev.crud2.view.commands.MainCommandFactory;
import main.java.com.mkudryavtsev.crud2.view.console.ConsoleUtils;

public class MainView {
    public static boolean isInterrupted = false;
    private ConsoleUtils consoleUtils = ConsoleUtils.getConsoleUtils();
    private CommandFactory mainCommandFactory = new MainCommandFactory();

    public void execute() {
        mainView();
    }
    private void mainView() {
        while (!isInterrupted) {
            System.out.println("Выберите объект для дальнейшей работы: project, developer, account");
            System.out.println("Для выхода из программы введите exit");
            mainCommandFactory.getCommand(consoleUtils.getStringFromConsole()).execute();
        }
    }
    public static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win");

    }

}
