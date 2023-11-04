package ru.top.java212.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class CustomErrorController implements ErrorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomErrorController.class);

    @RequestMapping("/error")
    public String handleError(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.error("Error occurred. Authenticated: {}, Principal: {}", auth.isAuthenticated(), auth.getPrincipal());
        if (auth instanceof AnonymousAuthenticationToken) {
            model.addAttribute("errorMessage", "Error occurred");
        } else {
            model.addAttribute("error", "Error occurred");
        }
        return "error";
    }
    public String getErrorPath() {
        return "/error";
    }
}

