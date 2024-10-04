package com.khaled.soabackend.role;

import com.khaled.soabackend.user.User;
import com.khaled.soabackend.user.UserRepository;
import com.khaled.soabackend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.roleMapper = new RoleMapper();
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Role createRole(RoleDto roleDto) {
        Role role = roleMapper.toRole(roleDto);
        return roleRepository.save(role);
    }

    public Optional<Role> getRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    public void deleteRoleById(Long roleId) {
         roleRepository.deleteById(roleId);
    }

    public List<User> getUsersByRoleId(Long roleId) {
        return userRepository.findByRoleId(roleId);
    }
}
