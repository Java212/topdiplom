package ru.top.java212.service.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.top.java212.dto.ToolDTO;
import ru.top.java212.model.Address;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import ru.top.java212.repository.AddressRepository;
import ru.top.java212.repository.ToolRepository;

@Service
public class ToolServiceImpl implements ToolService{
    private AddressRepository addressRepository;
    private ToolRepository toolRepository;

    @Autowired
     ToolServiceImpl(AddressRepository addressRepository,ToolRepository toolRepository){
        this.addressRepository = addressRepository;
        this.toolRepository = toolRepository;
    }
    @Override
    public Boolean save(ToolDTO tool, Person person) {
        Double doublePrice = 0.0;
        Address newAddress =  addressRepository.save(new Address(tool.getDistrict(),tool.getStreet()));
        int id = newAddress.getId();
        if(newAddress == null){
            return false;
        }
        try{
           doublePrice = Double.parseDouble(tool.getPrice());
        } catch (NumberFormatException e){
            return false;
        }
        Tool newTool = new Tool(tool.getName(),person,newAddress,doublePrice);
        if(toolRepository.save(newTool) == null) {
            return false;
        }
        return true;
    }
}
