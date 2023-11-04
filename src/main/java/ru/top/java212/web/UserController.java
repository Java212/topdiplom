package ru.top.java212.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dto.UserDTO;
import ru.top.java212.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewUser() {
        ModelAndView mv = new ModelAndView("user");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        mv.addObject("currentUser", userService.getUserDtoByName(currentPrincipalName));
        return mv;
    }

    @GetMapping("/current")
    public UserDTO getCurrentUser(Authentication authentication) {
        return userService.getUserDtoByName(authentication.getName());
    }
}
