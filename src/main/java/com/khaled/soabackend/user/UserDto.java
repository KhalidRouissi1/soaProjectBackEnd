package com.khaled.soabackend.user;

public record UserDto(
        Integer id,
        String username,
        String password,
        String email,
        Integer phone
) {
}
