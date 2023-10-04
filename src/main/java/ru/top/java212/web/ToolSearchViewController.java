package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.model.Person;
import ru.top.java212.model.User;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.service.tools.ToolService;
import ru.top.java212.utils.PriceUtils;

@Controller
@RequestMapping("/renter/toolSearchView")
public class ToolSearchViewController {

    private final PersonRepository personRepository;
    private final ToolService toolService;


    @Autowired
    ToolSearchViewController(PersonRepository personRepository,ToolService toolService){
        this.personRepository = personRepository;
        this.toolService = toolService;
    }


    @GetMapping
    public ModelAndView showToolSearchView() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        Person personUser = personRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("renter/toolSearchView");
        mv.addObject("personName",personUser.getName());
        mv.addObject("tools", toolService.findAll());
        return  mv;
    }

    @PostMapping
    public ModelAndView findTool(@RequestParam(value =  "name") String name,
                                 @RequestParam(value = "priceMin") String priceMin,
                                 @RequestParam(value = "priceMax") String priceMax){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        Person personUser = personRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("renter/toolSearchView");
        mv.addObject("personName",personUser.getName());
        Double priceMinDouble = PriceUtils.getDoubleFromString(priceMin,0.0);
        Double priceMaxDouble = PriceUtils.getDoubleFromString(priceMax,1000000.0);

//        if(!name.equals("")){
//            mv.addObject("tools", toolService.findByName(name));
//        }else{
//            mv.addObject("tools", toolService.findAll());
//        }
        mv.addObject("tools",toolService.findByPriceBetween(priceMinDouble,priceMaxDouble));
        return mv;
    }
}
