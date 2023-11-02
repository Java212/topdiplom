package ru.top.java212.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.top.java212.dao.RoleRepository;
import ru.top.java212.dao.UserInfoRepository;
import ru.top.java212.dao.UserRepository;
import ru.top.java212.dto.UserDto;
import ru.top.java212.model.Address;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;
import ru.top.java212.model.UserInfo;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserInfoRepository userInfoRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    void test_save() {
        when(userRepository.findByUserName(any())).thenReturn(new User("user", "user"));

        UserInfo userInfo = new UserInfo();
        userInfo.setUser(new User());

        UserDto userDto = new UserDto();
        userDto.setUserInfo(userInfo);
        assertFalse(userService.save(userDto));
        verify(userRepository).findByUserName(any());
    }


    @Test
    void test_save_2() {
        when(userInfoRepository.save(any())).thenReturn(new UserInfo());
        when(userRepository.save(any())).thenReturn(new User("user", "user"));
        when(userRepository.findByUserName(any())).thenReturn(null);
        when(roleRepository.save(any())).thenReturn(new Role());
        when(roleRepository.findByName(any())).thenReturn(new Role());
        when(passwordEncoder.encode(any())).thenReturn("secret");

        UserInfo userInfo = new UserInfo();
        userInfo.setUser(new User());

        UserDto userDto = new UserDto();
        userDto.setRole(new Role());
        userDto.setUserInfo(userInfo);
        assertTrue(userService.save(userDto));
        verify(userInfoRepository).save(any());
        verify(userRepository).save(any());
        verify(userRepository).findByUserName(any());
        verify(roleRepository).save(any());
        verify(roleRepository).findByName(any());
        verify(passwordEncoder).encode(any());
    }


    @Test
    void test_save_3() {
        when(userInfoRepository.save(any())).thenReturn(new UserInfo());
        when(userRepository.save(any())).thenReturn(new User("user", "user"));
        when(userRepository.findByUserName(any())).thenReturn(null);
        when(roleRepository.save(any())).thenThrow(new RuntimeException());
        when(roleRepository.findByName(any())).thenThrow(new RuntimeException());
        when(passwordEncoder.encode(any())).thenReturn("secret");

        UserInfo userInfo = new UserInfo();
        userInfo.setUser(new User());

        UserDto userDto = new UserDto();
        userDto.setRole(new Role());
        userDto.setUserInfo(userInfo);
        assertThrows(RuntimeException.class, () -> userService.save(userDto));
        verify(userRepository).findByUserName(any());
        verify(roleRepository).findByName(any());
        verify(passwordEncoder).encode(any());
    }


    @Test
    void test_save_4() {
        when(userInfoRepository.save(any())).thenReturn(new UserInfo());
        when(userRepository.save(any())).thenReturn(new User("user", "user"));
        when(userRepository.findByUserName(any())).thenReturn(null);
        when(roleRepository.save(any())).thenReturn(new Role());
        when(roleRepository.findByName(any())).thenReturn(null);
        when(passwordEncoder.encode(any())).thenReturn("secret");

        UserInfo userInfo = new UserInfo();
        userInfo.setUser(new User());

        UserDto userDto = new UserDto();
        userDto.setRole(new Role());
        userDto.setUserInfo(userInfo);
        assertTrue(userService.save(userDto));
        verify(userInfoRepository).save(any());
        verify(userRepository).save(any());
        verify(userRepository).findByUserName(any());
        verify(roleRepository).save(any());
        verify(roleRepository).findByName(any());
        verify(passwordEncoder).encode(any());
    }


    @Test
    void test_get_all() {
        when(userRepository.findAll()).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class, () -> userService.getAll());
        verify(userRepository).findAll();
    }


    @Test
    void test_find_by_id() {
        User user = new User("user", "user");

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        assertSame(user, userService.findById(1));
        verify(userRepository).findById(any());
    }




    @Test
    void test_find_by_id_2() {
        when(userRepository.findById(any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class, () -> userService.findById(1));
        verify(userRepository).findById(any());
    }


    @Test
    void test_load_user_by_username() throws UsernameNotFoundException {
        User user = new User("user", "user");

        when(userRepository.findByUserName(any())).thenReturn(user);
        assertSame(user, userService.loadUserByUsername("user"));
        verify(userRepository).findByUserName(any());
    }


    @Test
    void test_load_user_by_username_2() throws UsernameNotFoundException {
        when(userRepository.findByUserName(any())).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("user"));
        verify(userRepository).findByUserName(any());
    }


    @Test
    void test_load_user_by_username_3() throws UsernameNotFoundException {
        when(userRepository.findByUserName(any())).thenThrow(new UsernameNotFoundException("user not found"));
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("user"));
        verify(userRepository).findByUserName(any());
    }




    @Test
    void test_update_user_info() {
        when(userInfoRepository.save(any())).thenReturn(new UserInfo());
        User user = new User("user", "user");

        when(userInfoRepository.findByUser((User) any()))
                .thenReturn(new UserInfo("Name", "user@example.org", "6625550144", user, new Address()));

        Address address = new Address();
        address.setDistrict("District");

        UserInfo userInfo = new UserInfo();
        userInfo.setAddress(address);
        assertTrue(userService.updateUserInfo(userInfo));
        verify(userInfoRepository).save(any());
        verify(userInfoRepository).findByUser(any());
    }


    @Test
    void test_update_user_info_2() {
        when(userInfoRepository.save(any())).thenThrow(new UsernameNotFoundException("Msg"));
        User user = new User("user", "user");

        when(userInfoRepository.findByUser(any()))
                .thenReturn(new UserInfo("Name", "user@example.org", "6625550144", user, new Address()));

        Address address = new Address();
        address.setDistrict("District");

        UserInfo userInfo = new UserInfo();
        userInfo.setAddress(address);
        assertThrows(UsernameNotFoundException.class, () -> userService.updateUserInfo(userInfo));
        verify(userInfoRepository).save(any());
        verify(userInfoRepository).findByUser(any());
    }
}
