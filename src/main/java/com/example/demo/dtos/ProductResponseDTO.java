package com.example.demo.dtos;

import java.math.BigDecimal;

public record ProductResponseDTO(

        Long id,
        String name,
        BigDecimal price

) {
}
