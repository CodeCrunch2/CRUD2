package main.java.com.mkudryavtsev.crud2.view.commands;

public interface CommandFactory {
    Command getCommand(String command);
}
