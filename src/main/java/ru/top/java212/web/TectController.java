package ru.top.java212.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TectController {

    @GetMapping("/secured")
    public String secured() {
        return ("Text visible for authenticated users");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("Text visible for admins");
    }
}
