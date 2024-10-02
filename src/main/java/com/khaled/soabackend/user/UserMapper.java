package com.khaled.soabackend.user;

public class UserMapper {

    User toUser(UserDto userDto) {
        return User.builder()
                .id(userDto.id())
                .username(userDto.username())
                .password(userDto.password())
                .email(userDto.email())
                .phone(userDto.phone())
                .build();
    }
}
