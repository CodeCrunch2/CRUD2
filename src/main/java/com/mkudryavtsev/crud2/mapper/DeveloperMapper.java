package main.java.com.mkudryavtsev.crud2.mapper;


import main.java.com.mkudryavtsev.crud2.dto.DeveloperDto;
import main.java.com.mkudryavtsev.crud2.model.Developer;

public class DeveloperMapper {
    private AccountMapper accountMapper = new AccountMapper();
    public DeveloperDto developerToDto(Developer developer) {
        if (developer == null) {
            return null;
        }
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setId(developer.getId());
        developerDto.setAccountDto(accountMapper.accountToDto(developer.getAccount()));

        return developerDto;
    }
    public DeveloperDto exceptionMessageToDeveloperDto(String message) {
        if(message == null){
            return null;
        }
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setErrorMessage(message);
        return developerDto;
    }
}
