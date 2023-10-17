package ru.top.java212.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.dto.ToolDTO;
import ru.top.java212.model.Address;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import ru.top.java212.model.User;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.repository.ToolRepository;
import ru.top.java212.repository.UserRepository;
import ru.top.java212.service.tools.ToolService;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class ToolServiceImplTest {

    @Autowired
    private ToolService toolService;
    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void  test_that_save_toolDTO_is_ok(){
         User thisUser = userRepository.findByLogin("user1");
         ToolDTO toolDTO = new ToolDTO();
         toolDTO.setName("tool1");
         toolDTO.setDistrict("District");
         toolDTO.setStreet("Street");
         toolDTO.setPrice("1000.0");
         int size = toolRepository.findAll().size();
         toolService.save(toolDTO,thisUser);
         Assertions.assertEquals(size+1,toolRepository.findAll().size());
    }
    @Test
    public void  test_that_save_is_false_when_price_is_not_correct(){
         User thisUser = userRepository.findByLogin("user1");
         ToolDTO toolDTO = new ToolDTO();
         toolDTO.setName("tool1");
         toolDTO.setDistrict("District");
         toolDTO.setStreet("Street");
         toolDTO.setPrice("100r.0");
         int size = toolRepository.findAll().size();
         Assertions.assertFalse(toolService.save(toolDTO,thisUser));
         Assertions.assertEquals(size,toolRepository.findAll().size());
    }
    @Test
    public void test_that_findAllByUser_get_list_when_user_exists(){
        User thisUser = userRepository.findByLogin("user2");
        ToolDTO toolDTO = new ToolDTO();
        toolDTO.setName("tool2");
        toolDTO.setDistrict("District2");
        toolDTO.setStreet("Street2");
        toolDTO.setPrice("2000.0");
        toolService.save(toolDTO,thisUser);
        List<Tool> tool = toolService.findAllByUser(thisUser);
        Assertions.assertEquals(1,tool.size());
    }
    @Test
    public void test_that_findAllByUser_get_list_of_dimension_0_when_user_does_not_exists(){
        User thisUser = userRepository.findByLogin("NotUser");
        List<Tool> tool = toolService.findAllByUser(thisUser);
        Assertions.assertEquals(0,tool.size());
    }
    @Test
    public void test_that_tool_is_deleted(){
        User thisUser = userRepository.findByLogin("user2");
        ToolDTO toolDTO = new ToolDTO();
        toolDTO.setName("tool3");
        toolDTO.setDistrict("District3");
        toolDTO.setStreet("Street3");
        toolDTO.setPrice("3000.0");
        toolService.save(toolDTO,thisUser);
        List<Tool> tools = toolService.findAllByUser(thisUser);
        int toolsSize = tools.size();
        Tool tool = tools.get(toolsSize-1);
        int toolId = tool.getId();
        toolService.deleteById(toolId);
        tools = toolService.findAllByUser(thisUser);
        Assertions.assertEquals(toolsSize-1,tools.size());
    }
    @Test
    public void test_that_findByName_get_list_by_name_from_list_of_all(){
        Person person = new Person("Person","+79865745647",new User());
        Address address = new Address("Districn","Street");
        Tool tool1 = new Tool("tool1","",person,address,100);
        Tool tool2 = new Tool("tool2","",person,address,200);
        Tool tool3 = new Tool("tool1","",person,address,500);
        Tool tool4 = new Tool("tool4","",person,address,600);
        List<Tool> tools = new LinkedList<>();
        tools.add(tool1);
        tools.add(tool2);
        tools.add(tool3);
        tools.add(tool4);
        List<Tool> newTools = toolService.findByName(tools,"tool1");
        Assertions.assertEquals(2,newTools.size());
    }

}
