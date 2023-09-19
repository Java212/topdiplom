package ru.top.java212.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.top.java212.dao.UserRepository;
import ru.top.java212.model.User;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listUsers(Model model) {
        // получить список пользователей из репозитория
        // и передать его в представление через модель
        return "user/list";
    }

    @GetMapping("/create")
    public String createUserForm() {
        // Отображение формы создания пользователя
        return "user/create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        // Создание пользователя и сохранение его в репозитории
        return "redirect:/users";
    }
}
