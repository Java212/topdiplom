package ru.top.java212.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.top.java212.dto.UserRegistrationDTO;
import ru.top.java212.model.Person;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.repository.RoleRepository;
import ru.top.java212.repository.UserRepository;
import ru.top.java212.service.user.UserService;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,PersonRepository personRepository,PasswordEncoder encoder ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.personRepository = personRepository;
        this.bCryptPasswordEncoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }


    public User findUserById(Integer userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElseThrow(RuntimeException::new);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }


    public boolean deleteUser(Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public Boolean save(UserRegistrationDTO registrationDto) {
        User newUser = userRepository.findByLogin(registrationDto.getLogin());
        if(newUser != null){
            return false;
        }
        int roleId = 1;
        String roleText = registrationDto.getRole();
        if(roleText == null){
            roleId = 2;
        }
        newUser = new User(registrationDto.getLogin(),bCryptPasswordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findById(roleId).orElseThrow();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        newUser.setRoles(roles);
        User saveUser = userRepository.save(newUser);
        if(saveUser == null){
            return false;
        }
        Person newPerson = new Person(registrationDto.getName(), registrationDto.getPhone(),saveUser);
        if(personRepository.save(newPerson) == null){
            return false;
        }
        return  true;
    }

}
