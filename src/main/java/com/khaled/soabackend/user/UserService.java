package com.khaled.soabackend.user;

import com.khaled.soabackend.role.Role;
import com.khaled.soabackend.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // Create a new user
    public User createUser(UserDto userDto) {
        User user = new UserMapper().toUser(userDto);
        user.setRoles(new HashSet<>());
        return userRepository.save(user);
    }

    // Assign a role to a user
    public void addRoleToUser(Integer userId, Long roleId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            User user = optionalUser.get();
            Role role = optionalRole.get();
            user.getRoles().add(role);
            userRepository.save(user);
        } else  {
            throw new RuntimeException("Role or user not found");
        }
    }

    // Fetch a user by ID
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    // Fetch all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
