package ru.top.java212.service.tools;

import ru.top.java212.dto.ToolDTO;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import ru.top.java212.model.User;

import java.time.LocalDate;
import java.util.List;

public interface ToolService {
    Boolean save(ToolDTO tool, User user);
    List<Tool> findAllByUser(User user);
    void deleteById(int toolId);
    List<Tool> findAll();
    List<Tool> findByName(String name);
    public List<Tool> findByName(List<Tool> tools,String name);
    List<Tool> findByPriceBetween(Double priceMin, Double priceMax);
    List<Tool> findByPriceBetween(List<Tool> tools,Double priceMin, Double priceMax);
    Double findMaxPrice();
    List<Tool> findToolsByDates(LocalDate startDate, LocalDate stopDate);
    List<Tool> findToolsByDistrict(List<Tool> tools,String district);
}
