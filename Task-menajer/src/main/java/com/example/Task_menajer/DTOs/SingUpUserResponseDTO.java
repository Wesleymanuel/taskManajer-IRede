package com.example.Task_menajer.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record SingUpUserResponseDTO(
        UUID userId,
        @NotBlank(message = "o nome nao pode ser vazio")
        String userName,
        @NotBlank(message = "o email nao pode ser vazio")
        @Email(message = "o email deve estar na forma corret")
        String userEmail
){}
