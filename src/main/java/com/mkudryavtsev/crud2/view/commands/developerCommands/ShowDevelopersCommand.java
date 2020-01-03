package main.java.com.mkudryavtsev.crud2.view.commands.developerCommands;

import main.java.com.mkudryavtsev.crud2.controller.DeveloperController;
import main.java.com.mkudryavtsev.crud2.dto.DeveloperDto;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;

import java.util.List;

public class ShowDevelopersCommand implements Command {
    private DeveloperController developerController = DeveloperController.getDeveloperController();
    @Override
    public void execute() {
        List<DeveloperDto> developerDtoList = developerController.showDevelopers();
        if (developerDtoList.isEmpty()) {
            System.out.println("Список разработчиков пуст");
            return;
        }
        developerDtoList.forEach(d -> {
            if (d.getAccountDto() == null) {
                System.out.println("id: " + d.getId() + " account username: null");
            }
            else {
                System.out.println("id: " + d.getId() + " account username: " + d.getAccountDto().getUserName());
            }
        });

    }
}
