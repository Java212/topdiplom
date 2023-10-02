package ru.top.java212.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.top.java212.model.Tool;
import ru.top.java212.dao.ToolRepository;

import java.util.List;


@Service
@Slf4j
public class ToolServiceImpl implements ToolService {
    private final ToolRepository toolRepository;
    public ToolServiceImpl(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }
    @Override
    public List<Tool> listTools(String title) {
        if (title != null) return toolRepository.findByTitle(title);
        return toolRepository.findAll();
    }
    @Override
    public void saveTool(Tool tool) {
        toolRepository.save(tool);
    }
    @Override
    public void deleteTool(Long id) {
        toolRepository.deleteById(id);
    }
	@Override
    public Tool getToolsById(Long id) {
        return toolRepository.findById(id).orElse(null);
    }
}
