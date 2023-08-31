package ru.top.java212.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressTest {
    Address address =  new Address("district","street");

    @Test
    public void test_setId_and_getId(){
        address.setId(1);
        Assertions.assertEquals(1,address.getId());
    }
    @Test
    public void test_setDistrict_getDistrict(){
        address.setDistrict("New district");
        Assertions.assertEquals("New district",address.getDistrict());
    }
    @Test
    public void test_setStreet_getStreet(){
        address.setStreet("New street");
        Assertions.assertEquals("New street",address.getStreet());
    }

}
