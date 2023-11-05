package ru.top.java212.service;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.top.java212.repository.RoleRepository;
import ru.top.java212.repository.ProfileRepository;
import ru.top.java212.repository.UserRepository;
import ru.top.java212.dto.UserDto;
import ru.top.java212.entity.Role;
import ru.top.java212.entity.User;
import ru.top.java212.entity.Profile;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserDetailsService {

    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(ProfileRepository profileRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = passwordEncoder;
    }
    @Transactional
    public boolean save(UserDto userDto) {
        User userFromDb = userRepository.findByUserName(userDto.getProfile().getUser().getUserName());
        if (userFromDb != null) {
            return false;
        }
        userFromDb = new User(userDto.getProfile().getUser().getUserName(), bCryptPasswordEncoder.encode(userDto.getProfile().getUser().getPassword()));
        Role role = userDto.getRole();
        Role roleFromDb = roleRepository.findByName(role.getName());
        if (roleFromDb != null) {
            role = roleFromDb;
        }
        roleRepository.save(role);
        userFromDb.setRoles(Collections.singleton(role));
        User newUser = userRepository.save(userFromDb);
        profileRepository.save(new Profile(userDto.getProfile().getName(), userDto.getProfile().getEmail(),
                userDto.getProfile().getPhoneNumber(), newUser, userDto.getProfile().getAddress()));
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
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

}
