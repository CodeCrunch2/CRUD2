package main.java.com.mkudryavtsev.crud2.repository.csv;

import main.java.com.mkudryavtsev.crud2.model.Account;
import main.java.com.mkudryavtsev.crud2.repository.AccountRepository;
import main.java.com.mkudryavtsev.crud2.view.MainView;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvAccountRepositoryImpl implements AccountRepository {
    private static String stringPath;
    static {
        if (MainView.isWindows()) stringPath = "src\\resources\\accounts.csv";
        else stringPath = "src/resources/accounts.csv";
    }
    private final String SEPARATOR = ",";
    private final Path path = Paths.get(stringPath);

    @Override
    public void save(Account account) {
        try (PrintWriter printWriter =  new PrintWriter(new FileWriter(path.toFile(), true))) {
            printWriter.println(account.getId() + "," + account.getUserName());
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }

    @Override
    public void delete(Account account) {
        List<Account> accountsList = getAll();
        try (PrintWriter printWriter =  new PrintWriter(path.toFile())) {
            accountsList.stream().filter(a -> (a.getId() != account.getId()))
                    .forEach(a -> printWriter.println(a.getId() + "," + a.getUserName()));
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }

    @Override
    public void update(Account account) {
        List<Account> accountsList = getAll();
        try (PrintWriter printWriter =  new PrintWriter(path.toFile())) {
            accountsList.stream().peek(a -> {
                if(a.getId() == account.getId()) a.setUserName(account.getUserName());
            }).forEach(a -> printWriter.println(a.getId() + "," + a.getUserName()));
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }

    @Override
    public List<Account> getAll() {
        List<Account> accountList = new ArrayList<>();
        if (Files.exists(path)) {
            try {
                Stream<String> lines = Files.lines(path);
                List<List<String>> values = lines
                        .map((line) -> Arrays.asList(line.split(SEPARATOR)))
                        .collect(Collectors.toList());

                accountList = values.stream().map(v -> {
                    Account account = new Account(v.get(1));
                    account.setId(Long.parseLong(v.get(0)));
                    return account;
                }).collect(Collectors.toList());

            }
            catch (IOException e) {
                System.out.println("Ошибка ввода-вывода");
            }

        } else System.out.println("Файл accounts.csv не найден");
        return accountList;
    }

    @Override
    public Long getNextID() {
        List<Account> accountsList = getAll();
        if (accountsList.isEmpty()) return 0L;
        else return accountsList.get(accountsList.size()-1).getId() + 1L;
    }

    @Override
    public Account getById(Long id) {
        List<Account> accountsList = getAll();
        Account account = null;
        if (!accountsList.isEmpty()) {
            for (Account a: accountsList) {
                if (a.getId() == id) {
                    account = a;
                }
            }
        }
        return account;

    }
}
