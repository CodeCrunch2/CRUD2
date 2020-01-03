package main.java.com.mkudryavtsev.crud2.repository.csv;


import main.java.com.mkudryavtsev.crud2.model.Developer;
import main.java.com.mkudryavtsev.crud2.repository.DeveloperRepository;
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

public class CsvDeveloperRepositoryImpl implements DeveloperRepository {
    private static String stringPath;
    static {
        if (MainView.isWindows()) stringPath = "src\\resources\\developers.csv";
        else stringPath = "src/resources/developers.csv";
    }
    private final String SEPARATOR = ",";
    private final Path path = Paths.get(stringPath);
    CsvAccountRepositoryImpl csvAccountRepository = new CsvAccountRepositoryImpl();

    @Override
    public void save(Developer developer) {
        try (PrintWriter printWriter =  new PrintWriter(new FileWriter(path.toFile(), true))) {
            if (developer.getAccount() == null) {
                printWriter.println(developer.getId() + ",null");
            }
            else printWriter.println(developer.getId() + "," + developer.getAccount().getId());

        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }

    @Override
    public void delete(Developer developer) {
        List<Developer> developerList = getAll();
        try (PrintWriter printWriter =  new PrintWriter(path.toFile())) {
            developerList.stream().filter(d -> (d.getId() != developer.getId()))
                    .forEach(d -> {
                        if (d.getAccount() == null) printWriter.println(d.getId() + ",null");
                        else printWriter.println(d.getId() + "," + d.getAccount().getId());
                    });
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }

    @Override
    public void update(Developer developer) {
        List<Developer> developerList = getAll();
        try (PrintWriter printWriter =  new PrintWriter(path.toFile())) {
            developerList.stream().peek(d -> {
                if(d.getId() == developer.getId()) d.setAccount(developer.getAccount());
            }).forEach(d -> {
                if (d.getAccount() == null) printWriter.println(d.getId() + ",null");
                else printWriter.println(d.getId() + "," + d.getAccount().getId());
            });
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developerList = new ArrayList<>();
        if (Files.exists(path)) {
            try {
                Stream<String> lines = Files.lines(path);
                List<List<String>> values = lines
                        .map((line) -> Arrays.asList(line.split(SEPARATOR)))
                        .collect(Collectors.toList());

                developerList = values.stream().map(v -> {
                    Developer developer = new Developer(null);
                    if (!v.get(1).equalsIgnoreCase("null")) {
                        developer.setAccount(csvAccountRepository.getById(Long.parseLong(v.get(1))));
                    }
                    developer.setId(Long.parseLong(v.get(0)));
                    return developer;
                }).collect(Collectors.toList());

            }
            catch (IOException e) {
                System.out.println("Ошибка ввода-вывода");
            }

        } else System.out.println("Файл developers.csv не найден");
        return developerList;
    }

    @Override
    public Long getNextID() {
        List<Developer> developersList = getAll();
        if (developersList.isEmpty()) return 0L;
        else return developersList.get(developersList.size()-1).getId() + 1L;
    }

    @Override
    public Developer getById(Long id) {
        List<Developer> developerList = getAll();
        Developer developer = null;
        if (!developerList.isEmpty()) {
            for (Developer d: developerList) {
                if (d.getId() == id) {
                    developer = d;
                }
            }
        }
        return developer;
    }
}
