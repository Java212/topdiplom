package ru.top.java212.web;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import ru.top.java212.model.User;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.service.tools.ToolService;
import ru.top.java212.utils.PriceUtils;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

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
        return  mv;
    }

    @PostMapping
    public ModelAndView findTool(ModelAndView model,
                                 @RequestParam(value =  "name") String name,
                                 @RequestParam(value = "priceMin") String priceMin,
                                 @RequestParam(value = "priceMax") String priceMax,
                                 @RequestParam(value = "startDate")LocalDate startDate,
                                 @RequestParam(value = "stopDate") LocalDate stopDate){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        Person personUser = personRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("renter/toolSearchView");
        mv.addObject("personName",personUser.getName());
        Double priceMinDouble = PriceUtils.getDoubleFromString(priceMin,0.0);
        Double priceMaxDouble = PriceUtils.getDoubleFromString(priceMax,1000000.0);
        List<Tool> toolList = new LinkedList<>();


        if(!priceMax.equals("") || !priceMin.equals("")){
            toolList = toolService.findByPriceBetween(priceMinDouble,priceMaxDouble);
        }else{
            toolList = toolService.findAll();
        }
        if(!name.equals("")){
           toolList = toolService.findByName(toolList,name);
        }


        mv.addObject("tools",toolList);
        return mv;
    }
}
