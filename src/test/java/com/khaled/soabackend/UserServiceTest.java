package com.khaled.soabackend;

import com.khaled.soabackend.role.Role;
import com.khaled.soabackend.role.RoleRepository;
import com.khaled.soabackend.user.User;
import com.khaled.soabackend.user.UserDto;
import com.khaled.soabackend.user.UserRepository;
import com.khaled.soabackend.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    private UserDto userDto;
    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userDto = new UserDto(1, "password", "password", "email","+216");
        user = new User(1, "username", "password", "email", "phone", new HashSet<>());
        role = new Role(1L, "ROLE_USER", new HashSet<>());
    }

    @Test
    void testCreateUser() {
        // Given
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        User createdUser = userService.createUser(userDto);

        // Then
        assertNotNull(createdUser);
        assertEquals(user.getUsername(), createdUser.getUsername());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testAddRoleToUser() {
        // Given
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        // When
        userService.addRoleToUser(1, 1L);

        // Then
        assertTrue(user.getRoles().contains(role));
        verify(userRepository).save(user);
    }

    @Test
    void testGetUserById() {
        // Given
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // When
        Optional<User> foundUser = userService.getUserById(1);

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals(user.getUsername(), foundUser.get().getUsername());
        verify(userRepository).findById(1);
    }

    @Test
    void testGetAllUsers() {
        // Given
        when(userRepository.findAll()).thenReturn(List.of(user));

        // When
        List<User> users = userService.getAllUsers();

        // Then
        assertEquals(1, users.size());
        verify(userRepository).findAll();
    }

    @Test
    void testUpdateUser() {
        // Given
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        // Ensure save returns the user
        when(userRepository.save(any(User.class))).thenReturn(user);
        // Create UserDto with expected data
        userDto = new UserDto(1, "updatedUsername", "newPassword", "email", "+216");
        // When
        User updatedUser = userService.updateUser(1, userDto);
        // Then
        assertNotNull(updatedUser); // Check that updatedUser is not null
        assertEquals("updatedUsername", updatedUser.getUsername()); // Validate username
        verify(userRepository).save(any(User.class)); // Verify save method was called
    }

    @Test
    void testDeleteUser() {
        // Given
        when(userRepository.existsById(1)).thenReturn(true);

        // When
        userService.deleteUser(1);

        // Then
        verify(userRepository).deleteById(1);
    }

    @Test
    void testDeleteUserNotFound() {
        // Given
        when(userRepository.existsById(1)).thenReturn(false);

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> userService.deleteUser(1));
        assertEquals("User not found", exception.getMessage());
    }
}
