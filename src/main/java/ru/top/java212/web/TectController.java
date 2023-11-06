package ru.top.java212.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "API для тестирования ролей пользования")
public class TectController {

    @Operation(
            summary = "Проверка авторизированных пользователей",
            description = "Возвращает сообщение при успешной авторизации для пользователей с ролями ROLE_USER, ROLE_ADMIN"
    )
    @GetMapping("/secured")
    public String secured() {
        return ("Text visible for authenticated users");
    }

    @Operation(
            summary = "Проверка авторизированных администраторов",
            description = "Возвращает сообщение при успешной авторизации только для пользователей с ролью ROLE_ADMIN"
    )
    @GetMapping("/admin")
    public String admin() {
        return ("Text visible for admins");
    }
}
