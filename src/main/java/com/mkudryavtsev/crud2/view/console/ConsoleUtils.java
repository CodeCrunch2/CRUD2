package main.java.com.mkudryavtsev.crud2.view.console;

import java.util.Scanner;

public class ConsoleUtils {
    private static ConsoleUtils consoleUtils;
    private ConsoleUtils() {

    }
    public  static ConsoleUtils getConsoleUtils() {
        if (consoleUtils == null) {
            consoleUtils = new ConsoleUtils();
        }
        return consoleUtils;
    }
    private Scanner scanner = new Scanner(System.in);
    public String getStringFromConsole(){
        return scanner.nextLine();
    }

}
