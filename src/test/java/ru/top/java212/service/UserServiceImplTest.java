package ru.top.java212.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.top.java212.dto.UserDto;
import ru.top.java212.entity.Address;
import ru.top.java212.entity.Profile;
import ru.top.java212.entity.Role;
import ru.top.java212.entity.User;
import ru.top.java212.repository.ProfileRepository;
import ru.top.java212.repository.RoleRepository;
import ru.top.java212.repository.UserRepository;

import java.util.Collections;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    // Создаем заглушки (mock) для репозиториев и кодировщика паролей.
    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder bCryptPasswordEncoder;


    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // настройте моки при необходимости
        when(userRepository.findByUserName("nonexistentUsername")).thenReturn(null);

        // выполните ваш тест
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonexistentUsername"));
    }

    @Test
    public void testSaveNewUser() {
        // Создаем объекты DTO и роли для теста.
        UserDto userDto = createMockUserDto();
        Role role = createMockRole();

        // Создаем заглушку для пользователя из базы данных (пользователь с таким именем не существует).
        when(userRepository.findByUserName(any())).thenReturn(null);

        // Создаем заглушку для роли из базы данных (роль существует).
        when(roleRepository.findByName(any())).thenReturn(role);

        // Создаем заглушку для сохранения роли.
        when(roleRepository.save(any())).thenReturn(role);

        // Создаем заглушку для сохранения пользователя.
        when(userRepository.save(any())).thenReturn(createMockUser());

        // Вызываем метод save и проверяем, что возвращается true (успешное завершение операции).
        assertTrue(userService.save(userDto));

        // Проверяем, что методы были вызваны корректное количество раз.
        verify(userRepository, times(1)).findByUserName(any());
        verify(roleRepository, times(1)).findByName(any());
        verify(roleRepository, times(1)).save(any());
        verify(userRepository, times(1)).save(any());
        verify(profileRepository, times(1)).save(any());
    }

    @Test
    public void testSaveExistingUser() {
        // Создаем объекты DTO и роли для теста.
        UserDto userDto = createMockUserDto();
        Role role = createMockRole();

        // Создаем заглушку для пользователя из базы данных (пользователь с таким именем уже существует).
        when(userRepository.findByUserName(any())).thenReturn(createMockUser());

        // Вызываем метод save и проверяем, что возвращается false (пользователь существует).
        assertFalse(userService.save(userDto));

        // Проверяем, что метод findByUserName был вызван один раз.
        verify(userRepository, times(1)).findByUserName(any());

        // Проверяем, что остальные методы не были вызваны.
        verifyNoMoreInteractions(roleRepository, userRepository, profileRepository);
    }

    @Test
    public void testLoadUserByUsername() {
        // Создаем заглушку для пользователя из базы данных.
        User user = createMockUser();
        when(userRepository.findByUserName(any())).thenReturn(user);

        // Вызываем метод loadUserByUsername.
        UserDetails userDetails = userService.loadUserByUsername("testUser");

        // Проверяем, что полученный UserDetails соответствует ожидаемому пользователю.
        assertEquals(user, userDetails);

        // Проверяем, что метод findByUserName был вызван один раз.
        verify(userRepository, times(1)).findByUserName(any());

        // Проверяем, что остальные методы не были вызваны.
        verifyNoMoreInteractions(roleRepository, userRepository, profileRepository);
    }

    private UserDto createMockUserDto() {
        // Создаем объект DTO для теста.
        UserDto userDto = new UserDto();
        userDto.setProfile(createMockProfile());
        userDto.setRole(createMockRole());
        return userDto;
    }

    private Profile createMockProfile() {
        // Создаем объект профиля для теста.
        User owner = createMockUser();
        Address address = createMockAddress(); // Создаем объект Address
        return new Profile("Test User", "test@example.com", "123456789", owner, address);
    }

    private Address createMockAddress() {
        return new Address();
    }

    private Role createMockRole() {
        // Создаем объект роли для теста.
        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_USER");
        return role;
    }

    private User createMockUser() {
        // Создаем объект пользователя для теста.
        User user = new User("testUser", "encodedPassword");
        user.setId(1);
        user.setRoles(Collections.singleton(createMockRole()));
        return user;
    }
}
