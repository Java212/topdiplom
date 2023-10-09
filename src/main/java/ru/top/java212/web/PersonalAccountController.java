package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.UserInfoRepository;


import ru.top.java212.model.User;
import ru.top.java212.model.UserInfo;

@Controller
public class PersonalAccountController {
    @Autowired
    private UserInfoRepository userInfoRepository;


    @GetMapping("/personal-account")
    public ModelAndView showPersonalAccount() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User();
        UserInfo userInfo = userInfoRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("personal-account");
        mv.addObject("userName", userInfo.getName());

        return mv;
    }

    @PostMapping("/personal-account")
    public String personPost() {
        return "personal-account";
    }


}
