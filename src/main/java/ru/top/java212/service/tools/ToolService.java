package ru.top.java212.service.tools;

import ru.top.java212.dto.ToolDTO;
import ru.top.java212.model.Person;
import ru.top.java212.model.User;

public interface ToolService {
    Boolean save(ToolDTO tool, User user);
}
