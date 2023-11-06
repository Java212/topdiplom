package ru.top.java212.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dto.RegisterDTO;
import ru.top.java212.service.UserService;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
@Tag(name = "API для регистрации")
public class RegisterController {

    private final UserService userService;

    @RequestMapping
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register");
        return mv;
    }

    @Operation(summary = "Добавить (зарегистрировать) нового пользователя")
    @PostMapping
    public void registerUser(@RequestBody RegisterDTO registerDTO) {
        userService.saveUser(registerDTO);
    }

    @Operation(summary = "Проверить существует ли пользователь с таким именем")
    @GetMapping("/exist/{username}")
    public boolean checkDoesUserExist(@PathVariable String username){
        return userService.checkDoesUserExist(username);
    }
}
