package ru.top.java212.exeptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.net.BindException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ModelAndView badRequest(BindException exception) {
        ModelAndView mv = new ModelAndView("error-page");
        mv.addObject("message", "Некорректный ввод в поля формы!");
        return mv;
    }
}
