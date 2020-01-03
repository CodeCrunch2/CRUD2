package main.java.com.mkudryavtsev.crud2.repository.csv;

import main.java.com.mkudryavtsev.crud2.model.Developer;
import main.java.com.mkudryavtsev.crud2.model.Project;
import main.java.com.mkudryavtsev.crud2.model.ProjectStatus;
import main.java.com.mkudryavtsev.crud2.repository.ProjectRepository;
import main.java.com.mkudryavtsev.crud2.view.MainView;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvProjectRepository implements ProjectRepository {
    private static String stringPath;
    static {
        if (MainView.isWindows()) stringPath = "src\\resources\\projects.csv";
        else stringPath = "src/resources/projects.csv";
    }
    private final String SEPARATOR = ",";
    private final Path path = Paths.get(stringPath);
    private CsvDeveloperRepositoryImpl developerRepository = new CsvDeveloperRepositoryImpl();

    @Override
    public void save(Project project) {
        try (PrintWriter printWriter =  new PrintWriter(new FileWriter(path.toFile(), true))) {
            printWriter.print(project.getId() + ",");
            if (project.getDevelopers() == null) {
               printWriter.print("null,");
            }
            else {
                project.getDevelopers().forEach(d -> printWriter.print(d.getId() + ","));
            }
            if (project.getProjectStatus() == null) {
                printWriter.println("null");
            }
            else {
                printWriter.println(project.getProjectStatus());
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }

    @Override
    public void delete(Project project) {
        List<Project> projectList = getAll();
        try (PrintWriter printWriter =  new PrintWriter(path.toFile())) {
            projectList.stream().filter(p -> (p.getId() != project.getId()))
                    .forEach(p -> {
                        printWriter.print(p.getId() + ",");
                        if (p.getDevelopers() == null) {
                            printWriter.print("null,");
                        }
                        else {
                            p.getDevelopers().forEach(d -> printWriter.print(d.getId() + ","));
                        }
                        if (p.getProjectStatus() == null) {
                            printWriter.println("null");
                        }
                        else {
                            printWriter.println(p.getProjectStatus());
                        }

                    });
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }

    @Override
    public void update(Project project) {
        List<Project> projectList = getAll();
        try (PrintWriter printWriter =  new PrintWriter(path.toFile())) {
            projectList.stream().peek(p -> {
                if(p.getId() == project.getId()) {
                    p.setDevelopers(project.getDevelopers());
                    p.setProjectStatus(project.getProjectStatus());
                }
            }).forEach(p -> {
                printWriter.print(p.getId() + ",");
                if (p.getDevelopers() == null) {
                    printWriter.print("null,");
                }
                else {
                    p.getDevelopers().forEach(d -> printWriter.print(d.getId() + ","));
                }
                if (p.getProjectStatus() == null) {
                    printWriter.println("null");
                }
                else {
                    printWriter.println(p.getProjectStatus());
                }
            });
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }

    @Override
    public List<Project> getAll() {
        List<Project> projectList = new ArrayList<>();
        if (Files.exists(path)) {
            try {
                Stream<String> lines = Files.lines(path);
                List<List<String>> values = lines
                        .map((line) -> Arrays.asList(line.split(SEPARATOR)))
                        .collect(Collectors.toList());

                projectList = values.stream().map(p -> {
                    Project project = new Project(null, null);
                    if (!p.get(p.size()-1).equalsIgnoreCase("null")) {
                        project.setProjectStatus(ProjectStatus.valueOf(p.get(p.size()-1)));
                    }
                    if (!p.get(1).equalsIgnoreCase("null")) {
                        Set<Developer> developerSet = new HashSet<>();
                        for (int i = 1; i < (p.size()-1); i++) {
                            Developer developer = developerRepository.getById(Long.parseLong(p.get(i)));
                            developerSet.add(developer);
                        }
                        project.setDevelopers(developerSet);
                    }
                    project.setId(Long.parseLong(p.get(0)));
                    return project;
                }).collect(Collectors.toList());

            }
            catch (IOException e) {
                System.out.println("Ошибка ввода-вывода");
            }

        } else System.out.println("Файл developers.csv не найден");
        return projectList;
    }

    @Override
    public Long getNextID() {
        List<Project> projectList = getAll();
        if (projectList.isEmpty()) return 0L;
        else return projectList.get(projectList.size()-1).getId() + 1L;
    }

    @Override
    public Project getById(Long id) {
        List<Project> projectList = getAll();
        Project project = null;
        if (!projectList.isEmpty()) {
            for (Project p: projectList) {
                if (p.getId() == id) {
                    project = p;
                }
            }
        }
        return project;
    }
}
