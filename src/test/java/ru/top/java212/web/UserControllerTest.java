package ru.top.java212.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ConcurrentModel;
import ru.top.java212.dao.RoleRepository;
import ru.top.java212.dao.UserInfoRepository;
import ru.top.java212.dao.UserRepository;
import ru.top.java212.dto.UserDto;
import ru.top.java212.service.UserService;

class UserControllerTest {

    @Test
    void test_create_user() {

        UserService userService = mock(UserService.class);
        when(userService.save(any())).thenReturn(true);
        UserController userController = new UserController(userService, mock(UserInfoRepository.class));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("redirect:?authModal", userController.createUser(model, new UserDto()));
        verify(userService).save(any());
    }


    @Test
    void test_create_user_2() {

        UserService userService = mock(UserService.class);
        when(userService.save(any())).thenReturn(false);
        UserController userController = new UserController(userService, mock(UserInfoRepository.class));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("redirect:?registrationModal", userController.createUser(model, new UserDto()));
        verify(userService).save(any());
    }

    @Test
    void test_delete_user() {

        UserInfoRepository userInfoRepository = mock(UserInfoRepository.class);
        doNothing().when(userInfoRepository).deleteById(any());
        UserInfoRepository userInfoRepository1 = mock(UserInfoRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        RoleRepository roleRepository = mock(RoleRepository.class);
        UserController userController = new UserController(
                new UserService(userInfoRepository1, userRepository, roleRepository, new BCryptPasswordEncoder()),
                userInfoRepository);
        assertEquals("redirect:/", userController.deleteUser(1, new ch.qos.logback.core.model.Model()));
        verify(userInfoRepository).deleteById(any());
    }


}

