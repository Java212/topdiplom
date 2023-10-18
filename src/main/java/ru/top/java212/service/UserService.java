package ru.top.java212.service;


import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.top.java212.dao.RoleRepository;
import ru.top.java212.dao.UserInfoRepository;
import ru.top.java212.dao.UserRepository;
import ru.top.java212.dto.UserDto;
import ru.top.java212.model.Address;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;
import ru.top.java212.model.UserInfo;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    public UserService(UserInfoRepository userInfoRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = passwordEncoder;
    }

    @Transactional
    public boolean save(UserDto userDto) {
        User userFromDb = userRepository.findByUserName(userDto.getUserInfo().getUser().getUserName());

        if (userFromDb != null) {
            return false;
        }
        userFromDb = new User(userDto.getUserInfo().getUser().getUserName(), bCryptPasswordEncoder.encode(userDto.getUserInfo().getUser().getPassword()));


        Role role = userDto.getRole();
        Role roleFromDb = roleRepository.findByName(role.getName());

        if (roleFromDb != null) {
            role = roleFromDb;
        }

        userFromDb.setRoles(Collections.singleton(role));
        User newUser = userRepository.save(userFromDb);
        userInfoRepository.save(new UserInfo(userDto.getUserInfo().getName(), userDto.getUserInfo().getEmail(),
                userDto.getUserInfo().getPhoneNumber(), newUser, userDto.getUserInfo().getAddress()));

        return true;
    }


    public List<User> getAll() {

        return (List<User>) userRepository.findAll();
    }


    public User findById(Integer userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElseThrow(RuntimeException::new);


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }

    public boolean updateUserInfo(UserInfo userInfo) {
        UserInfo userInfoFromDb = userInfoRepository.findByUser(userInfo.getUser());
        Address addressFromDb = userInfoFromDb.getAddress();
        addressFromDb.setDistrict(userInfo.getAddress().getDistrict());
        addressFromDb.setStreet(userInfo.getAddress().getStreet());
        addressFromDb.setNumberOfHouse(userInfo.getAddress().getNumberOfHouse());
        addressFromDb.setApartmentNumber(userInfo.getAddress().getApartmentNumber());
        userInfoFromDb.setName(userInfo.getName());
        userInfoFromDb.setEmail(userInfo.getEmail());
        userInfoFromDb.setPhoneNumber(userInfo.getPhoneNumber());
        userInfoFromDb.setAddress(addressFromDb);
        userInfoRepository.save(userInfoFromDb);
        return true;
    }
}
