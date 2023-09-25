package ru.top.java212.service.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.top.java212.dto.ToolDTO;
import ru.top.java212.model.Address;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import ru.top.java212.model.User;
import ru.top.java212.repository.AddressRepository;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.repository.ToolRepository;

import java.util.List;

@Service
public class ToolServiceImpl implements ToolService{
    private AddressRepository addressRepository;
    private ToolRepository toolRepository;
    private PersonRepository personRepository;

    @Autowired
     ToolServiceImpl(AddressRepository addressRepository,ToolRepository toolRepository,PersonRepository personRepository){
        this.addressRepository = addressRepository;
        this.toolRepository = toolRepository;
        this.personRepository = personRepository;
    }
    @Override
    public Boolean save(ToolDTO tool, User user) {
        Double doublePrice = 0.0;
        Address newAddress =  addressRepository.save(new Address(tool.getDistrict(),tool.getStreet()));
        int id = newAddress.getId();
        if(newAddress == null){
            return false;
        }
        try{
            String toolPrice = tool.getPrice();
            doublePrice = Double.parseDouble(toolPrice);
        } catch (NumberFormatException | NullPointerException e){
            return false;
        }
        Person thisPerson = personRepository.findByUser(user);

        Tool newTool = new Tool(tool.getName(),thisPerson,newAddress,doublePrice);
        if(toolRepository.save(newTool) == null) {
            return false;
        }
        return true;
    }
    @Override
    public List<Tool> findAllByUser(User user){
        Person person = personRepository.findByUser(user);
        return toolRepository.findByPerson(person);
    }

}
