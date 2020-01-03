package main.java.com.mkudryavtsev.crud2.controller;

import main.java.com.mkudryavtsev.crud2.dto.DeveloperDto;
import main.java.com.mkudryavtsev.crud2.mapper.DeveloperMapper;
import main.java.com.mkudryavtsev.crud2.model.Account;
import main.java.com.mkudryavtsev.crud2.model.Developer;
import main.java.com.mkudryavtsev.crud2.repository.AccountRepository;
import main.java.com.mkudryavtsev.crud2.repository.DeveloperRepository;
import main.java.com.mkudryavtsev.crud2.repository.csv.CsvAccountRepositoryImpl;
import main.java.com.mkudryavtsev.crud2.repository.csv.CsvDeveloperRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class DeveloperController {
    private static DeveloperController developerController;
    private DeveloperRepository developerRepository = new CsvDeveloperRepositoryImpl();
    private AccountRepository accountRepository = new CsvAccountRepositoryImpl();
    private DeveloperMapper developerMapper = new DeveloperMapper();
    private DeveloperController(){

    }
    public static DeveloperController getDeveloperController(){
        if(developerController == null) {
            developerController = new DeveloperController();
        }
        return developerController;
    }
    public DeveloperDto createDeveloper(){
        Developer developer = new Developer(null);
        developer.setId(developerRepository.getNextID());
        developerRepository.save(developer);
        return developerMapper.developerToDto(developer);
    }
    public List<DeveloperDto> showDevelopers() {
        List<DeveloperDto> developerDtoList;
        List<Developer> developerList = developerRepository.getAll();
        if (developerList.isEmpty()) {
            return null;
        }
        developerDtoList = developerList.stream().map(d -> developerMapper.developerToDto(d)).collect(Collectors.toList());
        return developerDtoList;
    }

    public DeveloperDto setAccount(String idDeveloper, String idAccount) {
        Developer developer = developerRepository.getById(Long.parseLong(idDeveloper));
        Account account = accountRepository.getById(Long.parseLong(idAccount));
        developer.setAccount(account);
        developerRepository.update(developer);
        return developerMapper.developerToDto(developer);
    }

    public boolean isExist(Long id) {
        List<Developer> developerList = developerRepository.getAll();
        if (!developerList.isEmpty()) {
            for (Developer developer : developerList) {
                if (new Long(developer.getId()).equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }
    public DeveloperDto deleteDeveloper(String idString) {
        Developer developer = new Developer(null);
        developer.setId(Long.parseLong(idString));
        developerRepository.delete(developer);
        return developerMapper.developerToDto(developer);
    }



}
