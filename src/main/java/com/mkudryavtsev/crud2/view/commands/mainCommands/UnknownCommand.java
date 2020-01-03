package main.java.com.mkudryavtsev.crud2.view.commands.mainCommands;

import main.java.com.mkudryavtsev.crud2.view.commands.Command;

public class UnknownCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Команда введена неверно. Для получения сведений о работе программы см. файл Readme\n");
    }
}
