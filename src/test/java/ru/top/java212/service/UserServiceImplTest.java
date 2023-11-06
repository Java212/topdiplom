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
        when(userRepository.findByUserName("nonexistentUsername")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonexistentUsername"));
    }

    @Test
    public void testSaveNewUser() {
        UserDto userDto = createMockUserDto();
        Role role = createMockRole();
        when(userRepository.findByUserName(any())).thenReturn(null);
        when(roleRepository.findByName(any())).thenReturn(role);
        when(roleRepository.save(any())).thenReturn(role);
        when(userRepository.save(any())).thenReturn(createMockUser());
        assertTrue(userService.save(userDto));
        verify(userRepository, times(1)).findByUserName(any());
        verify(roleRepository, times(1)).findByName(any());
        verify(roleRepository, times(1)).save(any());
        verify(userRepository, times(1)).save(any());
        verify(profileRepository, times(1)).save(any());
    }

    @Test
    public void testSaveExistingUser() {
        UserDto userDto = createMockUserDto();
        Role role = createMockRole();
        when(userRepository.findByUserName(any())).thenReturn(createMockUser());
        assertFalse(userService.save(userDto));
        verify(userRepository, times(1)).findByUserName(any());
        verifyNoMoreInteractions(roleRepository, userRepository, profileRepository);
    }

    @Test
    public void testLoadUserByUsername() {
        User user = createMockUser();
        when(userRepository.findByUserName(any())).thenReturn(user);
        UserDetails userDetails = userService.loadUserByUsername("testUser");
        assertEquals(user, userDetails);
        verify(userRepository, times(1)).findByUserName(any());
        verifyNoMoreInteractions(roleRepository, userRepository, profileRepository);
    }

    private UserDto createMockUserDto() {
        UserDto userDto = new UserDto();
        userDto.setProfile(createMockProfile());
        userDto.setRole(createMockRole());
        return userDto;
    }

    private Profile createMockProfile() {
        User owner = createMockUser();
        Address address = createMockAddress();
        return new Profile("Test User", "test@example.com", "123456789", owner, address);
    }

    private Address createMockAddress() {
        return new Address();
    }

    private Role createMockRole() {
        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_USER");
        return role;
    }

    private User createMockUser() {
        User user = new User("testUser", "encodedPassword");
        user.setId(1);
        user.setRoles(Collections.singleton(createMockRole()));
        return user;
    }
}
