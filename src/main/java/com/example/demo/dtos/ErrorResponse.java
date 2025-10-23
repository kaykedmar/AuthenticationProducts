package com.example.demo.dtos;

import java.time.LocalDateTime;

// Formato padrão de resposta para erros
public record ErrorResponse(
        LocalDateTime timestamp, // Quando o erro ocorreu
        int status,              // Código HTTP (400, 404, 500, etc)
        String error,            // Texto curto do erro (ex: Bad Request, Conflict)
        String message,          // Explicação mais específica (ex: "Nome já cadastrado")
        String path              // Endpoint onde aconteceu o erro
) {}
