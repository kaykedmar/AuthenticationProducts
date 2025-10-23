package com.example.demo.dtos;

import com.example.demo.enums.Role;

import java.util.Set;

public record UserResponseDTO(
        Long id,
        String username,
        String email,
        Set<Role> roles
) {
}
