package ru.top.java212.service;

import ru.top.java212.model.Tool;

import java.util.List;

public interface ToolService {

    void saveTool(Tool tool);

    void deleteTool(Long id);
    Tool getToolsById(Long id);

    List<Tool> listTools(String title);


}
