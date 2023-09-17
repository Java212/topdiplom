package ru.top.java212.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.top.java212.dto.UserRegistrationDTO;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;
import ru.top.java212.repository.RoleRepository;
import ru.top.java212.repository.UserRepository;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder encoder ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByLogin(user.getLogin());
        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role( "Role_RENTER")));
        user.setPassword(user.getPassword());
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

    @Override
    public Boolean save(UserRegistrationDTO registrationDto) {
        User newUser = new User(registrationDto.getLogin(),bCryptPasswordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findById(2).orElseThrow();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        newUser.setRoles(roles);
        if(userRepository.save(newUser)!=null){
            return true;
        }
        return  false;
    }

}
