package main.java.com.mkudryavtsev.crud2.mapper;

import main.java.com.mkudryavtsev.crud2.dto.AccountDto;
import main.java.com.mkudryavtsev.crud2.model.Account;

public class AccountMapper {
    public AccountDto accountToDto(Account account) {
        if (account == null) {
            return null;
        }
        final AccountDto accountDto = new AccountDto();

        accountDto.setUserName(account.getUserName());
        accountDto.setId(account.getId());

        return accountDto;
    }

    public Account accountDtoToAccount(AccountDto accountDto) {
        if(accountDto == null) {
            return null;
        }
        Account account = new Account(accountDto.getUserName());
        account.setId(accountDto.getId());
        return account;
    }

    public AccountDto exceptionMessageToAccountDto(String message) {
        if(message == null){
            return null;
        }
        AccountDto accountDto = new AccountDto();
        accountDto.setErrorMessage(message);
        return accountDto;
    }
}
