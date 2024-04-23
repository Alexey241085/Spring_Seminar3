package ru.gb.example3_sem3_hometask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import ru.gb.example3_sem3_hometask.domain.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {

    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

    @Autowired
    private DataProcessingService dataProcessingService;


    @Autowired
    private UserService userService;


//    добавление в IOC контейнер через конструктор
//    @Autowired
    private NotificationService notificationService;

    private RegistrationService(NotificationService notificationService){
        this.notificationService = notificationService;
    }

//   - создается пользователь из параметров метода
//   - созданный пользователь добавляется в репозиторий
//   - через notificationService выводится сообщение в консоль
    public void processRegistration(String name, int age, String email){
        User user = userService.createUser(name,age, email);
        dataProcessingService.getRepository().getUsers().add(user);
        notificationService.notifyUser(user);

    }

//    создается пользователь в DB из параметров метода
    public void processRegistrationDatabase(String name, int age, String email){
        User user = userService.createUser(name, age,email);
        dataProcessingService.getDbRepositiry().save(user);
        notificationService.notifyUser(user);
    }
}
