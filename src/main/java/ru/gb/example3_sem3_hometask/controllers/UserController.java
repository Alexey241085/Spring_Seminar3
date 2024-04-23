package ru.gb.example3_sem3_hometask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.example3_sem3_hometask.services.RegistrationService;
import ru.gb.example3_sem3_hometask.domain.User;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RegistrationService service;

    @GetMapping
    public List<User> userList() {
        return service.getDataProcessingService().getRepository().getUsers();
    }

    @GetMapping("/db")
    public List<User> userListDb() {
        return service.getDataProcessingService().getDbRepositiry().findAll();
    }

    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user)
    {
        service.getDataProcessingService().getDbRepositiry().findAll();
        return "User added from body!";
    }


//     обработчик извлекающий данные для создания пользователя из параметров HTTP запроса
    @PostMapping("/param")
    public String userAddFromParam(@RequestParam String name,
                                   @RequestParam int age,
                                   @RequestParam String email) {
        service.processRegistration(name, age,email);
        return "User added from body!";
    }


    @PostMapping("/paramdb")
    public String userAddFromParamDb(@RequestParam String name,
                                   @RequestParam int age,
                                   @RequestParam String email) {
        service.processRegistrationDatabase(name, age,email);
        return "User added from body!";
    }
}
