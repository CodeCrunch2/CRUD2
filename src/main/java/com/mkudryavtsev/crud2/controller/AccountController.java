package main.java.com.mkudryavtsev.crud2.controller;

import main.java.com.mkudryavtsev.crud2.dto.AccountDto;
import main.java.com.mkudryavtsev.crud2.mapper.AccountMapper;
import main.java.com.mkudryavtsev.crud2.model.Account;
import main.java.com.mkudryavtsev.crud2.repository.AccountRepository;
import main.java.com.mkudryavtsev.crud2.repository.csv.CsvAccountRepositoryImpl;


import java.util.List;
import java.util.stream.Collectors;

public class AccountController {
    private static AccountController accountController;
    private final static int MINIMUM_NAME_SIZE = 3;
    private final static int MAXIMUM_NAME_SIZE = 10;
    private AccountRepository accountRepository = new CsvAccountRepositoryImpl();
    private AccountMapper accountMapper = new AccountMapper();
    private AccountController(){

    }
    public static AccountController getAccountController(){
        if (accountController == null) {
            accountController = new AccountController();
        }
        return accountController;
    }
    public AccountDto createAccount(String userName) {
        AccountDto accountDto;
        if (!validateUserName(userName)) {
            accountDto = accountMapper.exceptionMessageToAccountDto("Длина имени должна составлять от 3 до 10 символов");
            return accountDto;
        }
        if (isExist(userName)) {
            accountDto = accountMapper.exceptionMessageToAccountDto("Аккаунт с таким именем существует");
            return accountDto;
        }
        Account account = new Account(userName);
        account.setId(accountRepository.getNextID());
        accountRepository.save(account);
        return accountMapper.accountToDto(account);
    }
    public List<AccountDto> showAccounts(){
        List<AccountDto> accountDtoList;
        List<Account> accountList = accountRepository.getAll();
        if (accountList.isEmpty()) {
            return null;
        }
        accountDtoList = accountList.stream().map(a -> accountMapper.accountToDto(a)).collect(Collectors.toList());
        return accountDtoList;
    }
    public AccountDto changeUserName(String idString, String newUserName) {
        AccountDto accountDto;
        if (!validateUserName(newUserName)) {
            accountDto = accountMapper.exceptionMessageToAccountDto("Длина имени должна составлять от 3 до 10 символов");
            return accountDto;
        }
        if (isExist(newUserName)) {
            accountDto = accountMapper.exceptionMessageToAccountDto("Аккаунт с таким именем существует");
            return accountDto;
        }
        Account account = new Account(newUserName);
        account.setId(Long.parseLong(idString));
        accountRepository.update(account);
        accountDto = accountMapper.accountToDto(account);
        return accountDto;
    }
    public AccountDto deleteAccount(String idString) {
        Account account = new Account(null);
        account.setId(Long.parseLong(idString));
        accountRepository.delete(account);
        return accountMapper.accountToDto(account);
    }


    public boolean validateUserName(String userName) {
        return userName != null && userName.length() >= MINIMUM_NAME_SIZE && userName.length() <= MAXIMUM_NAME_SIZE;

    }
    public boolean isExist(String userName) {
        List<Account> accountList = accountRepository.getAll();
        if (!accountList.isEmpty()) {
            for (Account account : accountList) {
                if (account.getUserName().equals(userName)) {

                    return true;
                }
            }
        }
        return false;
    }

    public boolean isExist(Long id) {
        List<Account> accountList = accountRepository.getAll();
        if (!accountList.isEmpty()) {
            for (Account account : accountList) {
                if (new Long(account.getId()).equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

}
