package com.example.Task_menajer.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SingUpUserRequestDTO(
        @NotBlank(message = "o nome nao pode ser vazio")
        String userName,
        @NotBlank(message = "o email nao pode ser vazio")
        @Email(message = "o email deve estar na forma corret")
        String userEmail,
        @NotBlank(message = "a senha nao pode ser vazia")
        @Size(min = 6, max = 70, message = "a senha deve possuir no minimo 6 e no maximo 70 caracteres")
        String userPassword
){}
