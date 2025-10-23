package com.example.demo.mapper;

import com.example.demo.dtos.UserResponseDTO;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles()
        );
    }

    public User toEntity(UserResponseDTO dto){
        User user = new User();
        user.setUsername(dto.username());
        user.setRoles(dto.roles());
        user.setEmail(dto.email());

        return user;
    }

}
