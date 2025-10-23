package com.example.demo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 5)
        String username,

        @NotBlank
        @Size(min = 5)
        String password
) {
}
