package com.example.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequestDTO(

        @NotBlank
        String name,

        @Positive
        BigDecimal price
) {
}
