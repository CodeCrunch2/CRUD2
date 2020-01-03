package main.java.com.mkudryavtsev.crud2.view;


import main.java.com.mkudryavtsev.crud2.view.commands.CommandFactory;
import main.java.com.mkudryavtsev.crud2.view.commands.AccountCommandFactory;
import main.java.com.mkudryavtsev.crud2.view.console.ConsoleUtils;

public class AccountView {
    private static AccountView accountView;
    public static boolean isICancelled = false;
    private CommandFactory accountCommandFactory = new AccountCommandFactory();
    private ConsoleUtils consoleUtils = ConsoleUtils.getConsoleUtils();
    private AccountView() {
    }
    public static AccountView getAccountView() {
        if (accountView == null) {
            accountView = new AccountView();
        }
        return accountView;
    }
    public void accountView() {
        isICancelled = false;
        while (!isICancelled) {
            System.out.println("Выберите одно из доступных действий:");
            System.out.println("create account, show accounts, change username, delete account");
            accountCommandFactory.getCommand(consoleUtils.getStringFromConsole()).execute();
        }

    }

}
