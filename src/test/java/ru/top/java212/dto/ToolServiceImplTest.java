package ru.top.java212.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Person;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.repository.ToolRepository;
import ru.top.java212.service.tools.ToolService;

@SpringBootTest
public class ToolServiceImplTest {

    @Autowired
    private ToolService toolService;
    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void  test(){
      if(personRepository.findById(1).isPresent()){
         Person person = personRepository.findById(1).get();
         ToolDTO toolDTO = new ToolDTO();
         toolDTO.setName("tool1");
         toolDTO.setDistrict("District");
         toolDTO.setStreet("Street");
         toolDTO.setPrice("1000.0");
         int size = toolRepository.findAll().size();
         toolService.save(toolDTO,person);
         Assertions.assertEquals(size+1,toolRepository.findAll().size());
      }
    }
}
