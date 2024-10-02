package com.khaled.soabackend.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.roleMapper = new RoleMapper();
    }

    // Create a new role
    public Role createRole(RoleDto roleDto) {
        Role role = roleMapper.toRole(roleDto);
        return roleRepository.save(role);
    }

    // Fetch a role by ID
    public Optional<Role> getRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    // Fetch all roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
