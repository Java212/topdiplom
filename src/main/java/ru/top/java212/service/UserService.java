package ru.top.java212.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.top.java212.dto.RegisterDTO;
import ru.top.java212.dto.UserDTO;
import ru.top.java212.model.User;
import ru.top.java212.model.UserRole;
import ru.top.java212.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(Integer userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    public User findUserByName(String username) {
        User user = userRepository.findUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public UserDTO getUserDtoByName(String username){
        return userToDto(findUserByName(username));
    }

    public List<User> allUsers() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    public boolean saveUser(RegisterDTO registerDTO) {
        if (checkDoesUserExist(registerDTO.getUsername())) {
            return false;
        }
        User user = new User();
        user.setName(registerDTO.getUsername());
        user.setRoles(Collections.singleton(new UserRole(2, UserRole.ROLE_USER)));
        user.setPassword(bCryptPasswordEncoder.encode(registerDTO.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public boolean checkDoesUserExist(String username) {
        User userFromDB = userRepository.findUserByName(username);
        return userFromDB != null;
    }

    public UserDTO userToDto (User user) {
        return new UserDTO(user.getId(), user.getName(), user.getFamilyId());
    }
}
