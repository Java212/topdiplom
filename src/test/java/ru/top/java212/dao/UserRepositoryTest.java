package ru.top.java212.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.top.java212.model.Address;
import ru.top.java212.model.User;


@SpringBootTest
@Disabled
public class UserRepositoryTest {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void test_save_user() {
        Address address = new Address();

        address.setDistrict("District");
        address.setStreet("street");
        address.setNumberOfHouse(1);
        address.setApartmentNumber(2);
        addressRepository.save(address);
        User user = new User();
        user.setId(1L);
        user.setUserName("TestUser");
        user.setPassword("password");
        user.setEmail("test-email@mail.ru");
        user.setAddress(address);
        userRepository.save(user);

        User actualUser = userRepository.findByUserName("TestUser");
        System.out.println(actualUser.getEmail());
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(user.getUserName(), actualUser.getUserName());
        Assertions.assertEquals(user.getPassword(), actualUser.getPassword());
        Assertions.assertEquals(user.getEmail(), actualUser.getEmail());
    }
}