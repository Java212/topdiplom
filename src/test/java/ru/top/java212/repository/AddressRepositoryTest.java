package ru.top.java212.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.Address;

@SpringBootTest
public class AddressRepositoryTest {
    @Autowired
    AddressRepository addressRepository;

    @Test
    public void test_that_context_is_ok(){
        Assertions.assertDoesNotThrow(() -> addressRepository.findAll());
    }
    @Test
    public void test_that_Address_is_saved(){
       addressRepository.save(new Address(1,"District1","Street1"));
       Address addressFromDB = addressRepository.findById(1).orElseThrow();
       Assertions.assertEquals("Street1",addressFromDB.getStreet());
    }

}
