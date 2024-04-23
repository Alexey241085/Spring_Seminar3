package ru.gb.example3_sem3_hometask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.example3_sem3_hometask.domain.User;
import ru.gb.example3_sem3_hometask.repository.DbRepositiry;
import ru.gb.example3_sem3_hometask.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Попытка перенести репозиторий на DB h2
@Service
public class DataProcessingService {
    @Autowired
    private DbRepositiry dbRepositiry;



    public DbRepositiry getDbRepositiry(){
        return dbRepositiry;
    }

    public void addUserToDb(User user){
        dbRepositiry.save(user);
    }

// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



    @Autowired
    private UserRepository repository;

    public UserRepository getRepository() {
        return repository;
    }

    public void  addUserToList(User user)
    {
        repository.getUsers().add(user);
    }

    public List<User> sortUsersByAge(List<User> users) {

//        return repository.getUsers().stream().sorted(Comparator.comparing(User::getAge))
//                .collect(Collectors.toList());


        return users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    public List<User> filterUsersByAge(List<User> users, int age) {
        return users.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public double calculateAverageAge(List<User> users) {
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

}
