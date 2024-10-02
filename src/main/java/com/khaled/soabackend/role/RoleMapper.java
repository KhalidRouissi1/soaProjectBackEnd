package com.khaled.soabackend.role;

public class RoleMapper {

    public Role toRole(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.id());
        role.setName(roleDto.name());
        return role;
    }

    public RoleDto toRoleDto(Role role) {
        return new RoleDto(role.getId(), role.getName());
    }
}
