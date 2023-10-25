package ru.top.java212.service.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.top.java212.dto.ToolDTO;
import ru.top.java212.model.*;
import ru.top.java212.repository.AddressRepository;
import ru.top.java212.repository.OrderRepository;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.repository.ToolRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ToolServiceImpl implements ToolService{
    private final AddressRepository addressRepository;
    private final ToolRepository toolRepository;
    private final PersonRepository personRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ToolServiceImpl(AddressRepository addressRepository, ToolRepository toolRepository, PersonRepository personRepository,OrderRepository orderRepository){
        this.addressRepository = addressRepository;
        this.toolRepository = toolRepository;
        this.personRepository = personRepository;
        this.orderRepository = orderRepository;
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

        Tool newTool = new Tool(tool.getName(),tool.getSpecifications(),thisPerson,newAddress,doublePrice);
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
    @Override
    public void deleteById(int toolId) {
        if(getCurrentOrdersByToolId(toolId).isEmpty()){
            toolRepository.deleteById(toolId);
        } else {
            throw new RuntimeException();
        }
    }

    private List<Order> getCurrentOrdersByToolId(int toolId){
        Tool tool = toolRepository.getReferenceById(toolId);
        return tool.getOrders().stream()
                               .filter(o -> (o.getStopped() == false) && (o.getCompleted() == false))
                               .collect(Collectors.toList());
    }

    @Override
    public List<Tool> findAll(){
       return toolRepository.findAll();
    }

    @Override
    public List<Tool> findByName(String name){
        return toolRepository.findByName(name);
    }

    @Override
    public List<Tool> findByName(List<Tool> tools,String name){
        return tools.stream().filter(t -> t.getName().equals(name))
                             .collect(Collectors.toList());
    }
    @Override
    public List<Tool> findByPriceBetween(Double priceMin, Double priceMax) {
        return toolRepository.findByPriceBetween(priceMin,priceMax);
    }

    @Override
    public List<Tool> findByPriceBetween(List<Tool> tools, Double priceMin, Double priceMax) {
        Double minPrice;
        Double maxPrice;
        if(priceMin>priceMax){
            maxPrice = priceMin;
            minPrice = priceMax;
        }else{
            minPrice = priceMin;
            maxPrice = priceMax;
        }
        return tools.stream().filter(t -> (t.getPrice() >= minPrice
                                        && t.getPrice() <= maxPrice)).collect(Collectors.toList());
    }

    @Override
    public Double findMaxPrice() {
        return toolRepository.findMaxPrice();
    }

    @Override
    public List<Tool> findToolsByDates(LocalDate startDate, LocalDate stopDate) {
        List<Order> ordersByDateBetween = orderRepository.findOrdersByDateBetween(startDate,stopDate);
        List<Order> stoppedOrders = orderRepository.findByStopped(true);
        ordersByDateBetween.removeAll(stoppedOrders);
        List<Tool> allTools = toolRepository.findAll();
        Set<Tool> orderTools = ordersByDateBetween.stream().map(Order::getTool).collect(Collectors.toSet());
        allTools.removeAll(orderTools);
        return allTools ;
    }

    @Override
    public List<Tool> findToolsByDistrict(List<Tool> tools,String district) {
        return tools.stream().filter(t -> t.getDistrict().equals(district))
                             .collect(Collectors.toList());
    }

    @Override
    public Tool getToolById(int id) {
        return toolRepository.getReferenceById(id);
    }

    @Override
    public List<Order> getOrdersByTools(List<Tool> tools) {
        return tools.stream().map(Tool::getOrders)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
    }

    @Override
    public  List<Order> getStoppedOrdersByTools(List<Tool> tool){
        return getOrdersByTools(tool).stream().filter(o -> o.getStopped() == true)
                                              .collect(Collectors.toList());
    }

    @Override
    public List<Order> getCurrentOrdersByTools(List<Tool> tools) {
        return getOrdersByTools(tools).stream()
                                      .filter(o -> (o.getStopped() == false) && (o.getCompleted() == false))
                                      .collect(Collectors.toList());
    }

    public Boolean toolIsFree(Tool tool,LocalDate startDate, LocalDate stopDate){
//        List<Order> toolOrders = tool.getOrders();
//        List<Order> myOrders = toolOrders.stream().filter(o -> (startDate.isBefore(o.getStartDate())
//                        && stopDate.isBefore(o.getStartDate()))
//                        || (startDate.isAfter(o.getStopDate())
//                        && stopDate.isAfter(o.getStopDate())))
//                .collect(Collectors.toList());
        List<Order> orders = orderRepository.findOrdersByDateBetween(startDate,stopDate);
        Set<Tool> orderTools = orders.stream().map(Order::getTool).collect(Collectors.toSet());
        if(orderTools.contains(tool)){
            return  false;
        }

        return true;
    }

}
