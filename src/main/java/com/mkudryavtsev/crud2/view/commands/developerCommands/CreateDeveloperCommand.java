package main.java.com.mkudryavtsev.crud2.view.commands.developerCommands;

import main.java.com.mkudryavtsev.crud2.controller.DeveloperController;
import main.java.com.mkudryavtsev.crud2.dto.DeveloperDto;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;

public class CreateDeveloperCommand implements Command {
    private DeveloperController developerController = DeveloperController.getDeveloperController();
    @Override
    public void execute() {
        DeveloperDto developerDto = developerController.createDeveloper();
        if(developerDto.getErrorMessage() != null){
            System.out.println(developerDto.getErrorMessage());
        }
    }
}
