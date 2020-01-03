package main.java.com.mkudryavtsev.crud2.dto;

public class DeveloperDto extends BaseDto {
    private AccountDto accountDto;

    public AccountDto getAccountDto() {
        return accountDto;
    }

    public void setAccountDto(AccountDto accountDto) {
        this.accountDto = accountDto;
    }
}
